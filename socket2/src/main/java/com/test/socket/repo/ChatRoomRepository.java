package com.test.socket.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.test.socket.mapper.ChatRoom;

@Repository
public class ChatRoomRepository {
	private Map<String, ChatRoom> chatRoomMap;
	
	/*
	 * 서버를 켤때 한번 실행하는 어노테이션
	 */
	@PostConstruct
	private void init() {
		/*
		 * 순서가 있는 Hash를 사용한다.
		 */
		chatRoomMap = new LinkedHashMap<>();
	}
	
	public List<ChatRoom> findAllRoom(){
		/*
		 * 채팅방 생성순서 최근 순으로 반환
		 */
		List chatRooms = new ArrayList<>(chatRoomMap.values());
		Collections.reverse(chatRooms);
		return chatRooms;
	}
	
	/*
	 * pk인 id로 채팅방 찾기
	 */
	public ChatRoom findRoomById(String id) {
		System.out.println(chatRoomMap.size());
		return chatRoomMap.get(id);
	}
	/*
	 * 채팅방을 생성하고 채팅방에 등록하기 (Hash Map)
	 */
	public ChatRoom createChatRoom(String name) {
		ChatRoom chatRoom = ChatRoom.create(name);
		chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
		return chatRoom;
	}
	
}
