package com.notice.controller;

import java.io.IOException;
import java.util.Collection;
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

import com.member.model.MemberBean;

@ServerEndpoint("/NoticeWS/{memberAccount}")
public class NoticeWS {

	public static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();

	public static void sendCustomizeMessage(String memberAccount, String message) {

		Session receiverSession = sessionsMap.get(memberAccount);
		
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			
		}
		

	}

	@OnOpen
	public void onOpen(@PathParam("memberAccount") String memberAccount, Session userSession) {

		System.out.println("Notice連線已經開啟" + memberAccount);

		sessionsMap.put(memberAccount, userSession);
//		sendCustomizeMessage(memberAccount, memberAccount + " ~ Hello 歡迎登入");

	}

	@OnMessage
	public void onMessage(Session memberAccount, String message) {

	
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession) {
		sessionsMap.remove(userSession);
		System.out.println("連線已經關閉");

	}
}
