package com.live_support.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.live_support.model.LiveSupportMessage;
import com.websocketchat.jedis.JedisHandleMessage;

@ServerEndpoint("/LiveSupportWS/{userId}")
public class LiveSupportWS {

	private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userId") String userId, Session userSession) {

		// 當前websocket的用戶
		sessionMap.put(userId, userSession);
		Set<String> usersId = sessionMap.keySet();

		System.out
				.println(String.format("Session ID = %s;userId= %s%nusers: %s", userSession.getId(), userId, usersId));
	}

	@OnMessage
	public void OnMessage(Session userSession, String message) {

		LiveSupportMessage liveSuportMessage = gson.fromJson(message, LiveSupportMessage.class);

		String sender = liveSuportMessage.getSender();
		String receiver = liveSuportMessage.getReceiver();
		Date chatTime = liveSuportMessage.getChatTime();

		if ("history".equals(liveSuportMessage.getType())) {

			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMesage = gson.toJson(historyData);

			LiveSupportMessage liveSupportHistory = new LiveSupportMessage("history", sender, receiver, message,
					chatTime);

			// 傳送訊息
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(liveSupportHistory));
				System.out.println("history" + gson.toJson(liveSupportHistory));
				return;
			}

			// 接收訊息
			Session receiverSession = sessionMap.get(receiver);
			if (receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(gson.toJson(message));
				userSession.getAsyncRemote().sendText(gson.toJson(message));
				JedisHandleMessage.saveChatMessage(sender, receiver, message);
			}

			System.out.println("Message received: " + message);
		}

	}

	@OnError
	public void OnError(Session userSession, Throwable e) {
		System.out.println("發生錯誤" + e.toString());
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {

		String userIdClose = null;
		Set<String> usersId = sessionMap.keySet();
		for (String uesrId : usersId) {
			if (sessionMap.get(uesrId).equals(userSession)) {
				userIdClose = uesrId;
				sessionMap.remove(uesrId);
			}

		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), usersId);
		System.out.println(text);
	}
}
