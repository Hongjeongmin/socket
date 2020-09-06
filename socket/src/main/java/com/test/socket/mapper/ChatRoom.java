package com.test.socket.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import com.test.socket.service.ChatService;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoom {
	private String roomId;
	private String name;
	private Set<WebSocketSession> ssesions = new HashSet<>();

	@Builder
	public ChatRoom(String roomId, String name) {
		super();
		this.roomId = roomId;
		this.name = name;
	}
	
	public <T> void sendMessage(T message, ChatService chatService) {
		ssesions.parallelStream().forEach(session -> chatService.sendMessage(session,message));
	}

	public void handleActions(WebSocketSession session,ChatMessage chatMessage
			,ChatService chatService) {
		
		if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
			ssesions.add(session);
			chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
		}
		sendMessage(chatMessage, chatService);
	}

}
