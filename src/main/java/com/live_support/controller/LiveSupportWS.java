package com.live_support.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.undo.StateEdit;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.live_support.model.ChatMessage;
import com.live_support.model.State;
import com.live_support.model.UserState;
import com.websocketchat.jedis.JedisHandleMessage;

@ServerEndpoint("/LiveSupportWS/{userName}")
public class LiveSupportWS {

// ConcurrentHashMap 可多執行緒做存取，但不能對同個物件做存取
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) {

		sessionsMap.put(userName, userSession);
		
		Set<String> userNames = sessionsMap.keySet();
		
		String sender=userName;
		String receiver ="james";

		if (userSession.isOpen()) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			
			userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
			System.out.println("cmHistory="+ gson.toJson(cmHistory));
			return;
		}

		
	}

	@OnMessage
	public void onMessage(Session userSession, String message,@PathParam("userName") String userName) {
		
		String sender=userName;
		String receiver ="james";		
		Session receiverSession = sessionsMap.get(receiver);
		
		if(receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message);		
		}
		
			System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession) {
		sessionsMap.remove(userSession);
		System.out.println("已關閉連線");

	}

}
