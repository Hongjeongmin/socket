package com.test.socket.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.socket.mapper.ChatRoom;
import com.test.socket.mapper.LoginInfo;
import com.test.socket.repo.ChatRoomRepository;
import com.test.socket.service.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/chat")
@Controller
public class ChatRoomController {
	private final ChatRoomRepository chatRoomRepository;

	private final JwtTokenProvider jwtTokenProvider;

	@GetMapping("/room")
	public String rooms() {
		return "/chat/room";
	}

	@GetMapping("/rooms")
	@ResponseBody
	public List<ChatRoom> room() {
		return chatRoomRepository.findAllRoom();
	}

	@PostMapping("/room")
	@ResponseBody
	public ChatRoom createRoom(@RequestParam String name) {
		return chatRoomRepository.createChatRoom(name);
	}

	@GetMapping("/room/enter/{roomId}")
	public String roomDetail(Model model, @PathVariable String roomId) {
		model.addAttribute("roomId", roomId);
		System.out.println(">>>>>>>>>>>>");
		return "/chat/roomdetail";
	}

	@GetMapping("/room/{roomId}")
	@ResponseBody
	public ChatRoom roomInfo(@PathVariable String roomId) {
		return chatRoomRepository.findRoomById(roomId);
	}

	@GetMapping("/user")
	@ResponseBody
	public LoginInfo getUserInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		String token =jwtTokenProvider.generateToken(name);
		System.out.println();
		return LoginInfo.builder().name(name).token(token).build();
	}

}
