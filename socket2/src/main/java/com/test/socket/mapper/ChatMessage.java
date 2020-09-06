package com.test.socket.mapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ChatMessage {
	/*
	 * ENTER : 입장 ,  TALK : 퇴장
	 */
	
	public enum MessageType{
		ENTER,TALK
	}
	
	private MessageType type;
	private String roomId;
	private String sender;
	private String message;
}
