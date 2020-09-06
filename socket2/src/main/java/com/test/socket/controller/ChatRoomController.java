package com.test.socket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.socket.mapper.ChatRoom;
import com.test.socket.repo.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/chat")
@Controller
public class ChatRoomController {
	private final ChatRoomRepository chatRoomRepo;
	
	/*
	 * 채팅 리스트 화면
	 */
	@RequestMapping(value="/room",method = RequestMethod.GET)
	public String rooms(Model model) {
		System.out.println("/chat/room GETcall");
		return "/chat/room";
	}
	
	/*
	 * 모든 채팅방 목록 반환
	 */
	@GetMapping("/rooms")
	@ResponseBody
	public List<ChatRoom> room(){
		System.out.println("/chat/rooms GETcall");
		return chatRoomRepo.findAllRoom();
	}
	
	/*
	 * 채팅방 생성
	 */
	@PostMapping("/room")
	@ResponseBody
	public ChatRoom createRoom(@RequestParam String name) {
		System.out.println("/chat/room POSTcall");
		return chatRoomRepo.createChatRoom(name);
	}
	
	/*
	 * 채팅방 입장 화면
	 */
	@GetMapping("/room/enter/{roomId}")
	public String roomDetail(Model model, @PathVariable String roomId) {
		System.out.println("/chat/room/enter/{roomId} GETcall");
		System.out.println(roomId);
		model.addAttribute("roomId",roomId);
		return "/chat/roomdetail";
	}
	/*
	 * 특정 채팅방 조회
	 */
	@GetMapping("/room/{roomId}")
	@ResponseBody
	public ChatRoom roomInfo(@PathVariable String roomId) {
		System.out.println("/chat/room/{roomId} GETcall");
		System.out.println(chatRoomRepo.findRoomById(roomId));
		return chatRoomRepo.findRoomById(roomId);
	}
}
