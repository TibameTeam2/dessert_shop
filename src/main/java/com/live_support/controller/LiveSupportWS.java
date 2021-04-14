package com.live_support.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import com.live_support.model.ChatData;
import com.live_support.model.HistroyService;
import com.live_support.model.LiveSupportMessage;
import com.live_support.model.LiveSupportState;
import com.websocketchat.jedis.JedisHandleMessage;

@ServerEndpoint("/LiveSupportWS/{userName}") // userName代表使用者
public class LiveSupportWS {

	// ConcurrentHashMap 可多執行緒做存取，但不能對同個物件做存取
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	// 同json(google的)
	Gson gson = new Gson();

	// 實作介面
	private HistroyService histroyService; // = new HistroyServiceImpl();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {

		// 儲存使用者連線 (紀錄尚有連線的使用者)
		sessionsMap.put(userName, userSession);
		// Set:不可有重複值，無順序性(List:有順序性，可重複)，存取使用者與該使用者的連線。

		// 使用者建立連線後，伺服器紀錄該使用者的連線，將各自的史訊息推播給各自的使用者

		if (userSession.isOpen()) {
			List<ChatData> chatdatas = histroyService.findHistoryByUser(userName);
			userSession.getAsyncRemote().sendText(gson.toJson(chatdatas));
		}

//		
//		LiveSupportState stateMessage = new LiveSupportState("open", userName, userNames);
//		String stateMessageJson = ;
//		
//		Collection<Session> sessions = sessionsMap.values();
//		for (Session session : sessions) {
//			if (session.isOpen()) {
//				session.getAsyncRemote().sendText(stateMessageJson);
//			}
//		}
//
//		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
//				userName, userNames);
//		System.out.println(text);
	}

	public static void main(String[] args) {

		Gson gson = new Gson();

//		private String type; // 分辨為聊天還是歷史訊息
//		private String sender "sender":"from";
//		private String receiver "receiver":"to";
//		private String message "message":"123";
//		private Date chatTime "chatTime":1618407364050;

		String message = "{\"type\":\"1\",\"sender\":\"from\",\"receiver\":\"to\",\"message\":\"123\",\"chatTime\":\"2012-04-14T21:39:00+0800\"}";
//		LiveSupportMessage chatMessage = gson.fromJson(message, LiveSupportMessage.class);

//		System.out.println(chatMessage);
		System.out.println(message);
		
		
//		
//		{
//		  type:"1",
//		  sender:"from",
//		  receiver:to,
//		  message:{123},
//		  chatTime:["2012-04-14"]
//		}

	}

	@OnMessage
	public void onMessage(@PathParam("userName") String userName, Session userSession, String message) {
		//從前端(使用者)發送的訊息，伺服器接受到後，要轉發給客服
		//1. 收訊息(message)
		//2. 確認客服是否在線上
		//  2.1  在，轉發
		//  2.2 不在，回「客服不在線上，請稍後再嘗試」
		
		// 取得發送對象的連線
		// 確認連線存在後，發送訊息 null
//		if (sessionsMap.get("to").isOpen()) {
//			List<ChatData> chatdatas = histroyService.findHistoryByUser(userName);
//			userSession.getAsyncRemote().sendText(message);
//		} else{
//		sessionsMap.remove("to")
//		}
		
		// 取得使用者傳遞的資訊
		// fromJson(反序列化):將json物件轉換為java物件
//		LiveSupportMessage chatMessage = gson.fromJson(message, LiveSupportMessage.class);
//		String sender = chatMessage.getSender();
//		String receiver = chatMessage.getReceiver();
//
//		if ("history".equals(chatMessage.getType())) {
//			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
//			String historyMsg = gson.toJson(historyData);
//			LiveSupportMessage cmHistory = new LiveSupportMessage("history", sender, receiver, historyMsg, new Date());
//
//			if (userSession != null && userSession.isOpen()) {
//				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
//				System.out.println("history = " + gson.toJson(cmHistory));
//				return;
//			}
//		}
//
//		Session receiverSession = sessionsMap.get(receiver);
//		if (receiverSession != null && receiverSession.isOpen()) {
//			receiverSession.getAsyncRemote().sendText(message);
//			userSession.getAsyncRemote().sendText(message);
//			JedisHandleMessage.saveChatMessage(sender, receiver, message);
//		}
//		System.out.println("Message received: " + message);
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


		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}

}
