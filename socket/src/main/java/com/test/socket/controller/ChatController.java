package com.test.socket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.socket.mapper.ChatRoom;
import com.test.socket.service.ChatService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
	
	private final ChatService chatService;
	
	@PostMapping
	public ChatRoom createRoom(@RequestParam("name") String name) {
		return chatService.createRoom(name);
	}
	
	@GetMapping
	public List<ChatRoom> findAllRoom(){
		return chatService.findAllRoom();
	}

}
