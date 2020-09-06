package com.test.socket.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.socket.mapper.ChatMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {
	private final ObjectMapper objectMapper;
	private final RedisTemplate redisTemplate;
	private final SimpMessageSendingOperations messageSendingOperations;

	/*
	 * Redis에서 메시지가 pub되면 대기하고 있던 onMessage가 해당 메시지를 받아 처리한다.
	 */

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
		
		try {
			ChatMessage roomMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
			messageSendingOperations.convertAndSend("/sub/chat/room/"+roomMessage.getRoomId(),roomMessage);
		
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		

	}
}
