package com.test.socket.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.socket.mapper.ChatRoom;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatService {
	private final ObjectMapper objectMapper;
	private Map<String, ChatRoom> chatRooms;

	/*
	 * Was 실행시에만 init 메서드 실행
	 */
	@PostConstruct
	private void init() {
		// LinkedHashMap을 이용해서 순서가 있는 keys 값으로 조회한다.
		chatRooms = new LinkedHashMap();
	}
	/*
	 * chatRooms 의 keys값들을 반환한다.
	 */
	public List<ChatRoom> findAllRoom() {

		return new ArrayList<>(chatRooms.values());
	}
	/*
	 * cahtRoom의 roomId 값으로 찾기
	 */
	public ChatRoom findRoomById(String roomId) {
		return chatRooms.get(roomId);
	}
	
	public ChatRoom createRoom(String name) {
	/*
	 * UUID에서 랜덤으로 된값을 roomId 로 지정한다.
	 */
	String randomId =  UUID.randomUUID().toString();
	/*
	 * chatRoom create
	 */
	ChatRoom chatRoom = ChatRoom.builder()
			.roomId(randomId)
			.name(name)
			.build();
	/*
	 * chatRooms에 chatRoom 등록 HashMap
	 */
	chatRooms.put(randomId, chatRoom);
	return chatRoom;

	}

	public <T> void sendMessage(WebSocketSession session, T message) {
		try {
			session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
