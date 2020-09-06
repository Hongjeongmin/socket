package com.test.socket.utills;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.socket.mapper.ChatMessage;
import com.test.socket.mapper.ChatRoom;
import com.test.socket.service.ChatService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class WebSocketChatHandler extends TextWebSocketHandler{
	
	private final ObjectMapper objectMapper;
	private final ChatService chatService;
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		/*
		 * this is client message
		 */
		String payload = message.getPayload();
		ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
		ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
		room.handleActions(session, chatMessage, chatService);
	}
}
