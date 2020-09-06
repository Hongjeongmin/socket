package com.test.socket.handler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import com.test.socket.service.JwtTokenProvider;
/*
 * Websocket 연결 시 요청 header의 jwt token 유효성을 검증하는 코드를 다음과 같이 추가합니다.
 * 유효하지 않은 jwt토큰이 세팅될 경우 websocket 연결을 하지 않고 예외 처리됩니다.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    // websocket을 통해 들어온 요청이 처리 되기전 실행된다.
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        // websocket 연결시 헤더의 jwt token 검증
        System.out.println("검");
        System.out.println(accessor.getCommand());
        System.out.println(StompCommand.CONNECT);
        if (StompCommand.CONNECT == accessor.getCommand()) {
        	System.out.println("??");
            jwtTokenProvider.validateToken(accessor.getFirstNativeHeader("token"));
        }
        return message;
    }
}
