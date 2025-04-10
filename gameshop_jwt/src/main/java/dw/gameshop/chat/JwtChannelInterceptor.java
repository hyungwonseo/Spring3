package dw.gameshop.chat;

import dw.gameshop.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtChannelInterceptor implements ChannelInterceptor {
    @Autowired
    TokenProvider tokenProvider;
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String token = accessor.getFirstNativeHeader("Authorization");
            System.out.println("Token: " + token);
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                // 여기서 JWT 검증 → 성공 시 사용자 정보를 Principal로 설정
                Authentication auth = tokenProvider.getAuthentication(token);
                accessor.setUser(auth);
                System.out.println("User: " + auth.getName());
            } else {
                throw new IllegalArgumentException("JWT token missing");
            }
        }
        return message;
    }
}
