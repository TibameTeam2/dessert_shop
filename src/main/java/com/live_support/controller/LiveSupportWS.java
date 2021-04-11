package com.live_support.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.live_support.model.LiveSupportMessage;
import com.live_support.model.LiveSupportState;

import cn.hutool.core.convert.Convert;
import idv.david.websocketchat.jedis.JedisHandleMessage;


@ServerEndpoint("/LiveSupportWS/{uesrId}")
public class LiveSupportWS {

	// 一對一聊天:session存入Map(key,value),ConcurrentHashMap(並行):可同時存取。(Hashtable無法同時存取)
	public static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	// google的json轉換
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("uesrId") String userId, Session userSession) throws IOException {

		// Map<用户id，用户信息>
		sessionsMap.put(userId, userSession);

		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		LiveSupportState stateMessage = new LiveSupportState("open", userId, userNames);

		// 將java物件序列化為Json物件
		String stateMessageJson =gson.toJson(stateMessage); // 轉成json字串
		System.out.println(stateMessageJson);
		
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userId, userNames);
		System.out.println(text);

	}

	@OnMessage
	public void onMessage(Session userSession, String message) throws IOException {

		// 將Json物件反序列化為Java物件
		LiveSupportMessage liveSupportMessage = gson.fromJson(message, LiveSupportMessage.class);
		String sender = liveSupportMessage.getSender();
		String receiver = liveSupportMessage.getReceiver();
		Date chatTime =liveSupportMessage.getChatTime();
	

		if ("history".equals(liveSupportMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);

			// 將java物件序列化為Json物件
			String historyMsg = gson.toJson(historyData);
			LiveSupportMessage lsHistory = new LiveSupportMessage("history",sender, receiver,historyMsg,chatTime);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(lsHistory));
				System.out.println("history = " + gson.toJson(lsHistory));
				return;
			}
		}

		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
		}
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
		
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {

		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			LiveSupportState stateMessage = new LiveSupportState("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}

}
