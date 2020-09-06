package com.test.socket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
 * stomp를 사용하기 위해 @EnalbeWebSocketMessageBroker을 선언
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	/*
	 * pub/sub 메세징을 구현하기 위한 설정 
	 * 1. pub : 메세지 발행
	 * 2. sub : 메세지 구독
	 */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }
	/*
	 * stomp Websocket의 연결 endpoint는 /ws-stomp로 설정
	 * 개발 서버 접속 주소 : ws://localhost:8080/ws-stomp
	 */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp").setAllowedOrigins("*")
                .withSockJS();
    }
	
	

}
