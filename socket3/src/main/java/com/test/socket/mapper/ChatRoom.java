package com.test.socket.mapper;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * pub / sub 방식을 이용하면 구독자 관리가 알아서 되므로 웹소켓 세션 관리가 필요 없어집니다.
 */
@Getter
@Setter
@ToString
public class ChatRoom implements Serializable {
	private static final long serialVersionUID = 6494678977089006639L;

	private String roomId;
	private String name;

	public static ChatRoom create(String name) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.roomId = UUID.randomUUID().toString();
		chatRoom.name = name;
		return chatRoom;
	}
}
