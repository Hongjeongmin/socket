package com.test.socket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.test.socket.utills.WebSocketChatHandler;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	@Autowired
	WebSocketChatHandler webSocketChatHandler;
	/*
	 * TCP 통신을 직접 핸들링하는 함수 localhost:8080/ws/chat 에서는 socke 통신을 한다.
	 */
	@Override	
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketChatHandler, "ws/chat").setAllowedOrigins("*");
	}
}
