package com.duypk.socket.config;

import java.text.ParseException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.duypk.socket.dto.SocketloginDto;
import com.duypk.socket.service.AccountLoginService;
import com.nimbusds.jwt.SignedJWT;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Autowired
	private AccountLoginService accountLoginService;
	
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/receive-mess"); // nơi client sẽ nhận message
        config.setApplicationDestinationPrefixes("/send-mess"); // nơi client gửi message
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/botchat-mess")
        .setAllowedOriginPatterns("*") // hoặc origin frontend của bạn
        .withSockJS();
    }
    
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(new ChannelInterceptor() {
        @Override
        public Message<?> preSend(Message<?> message, MessageChannel channel) {
            StompHeaderAccessor accessor =
                    MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

            assert accessor != null;
            if (StompCommand.CONNECT.equals(accessor.getCommand())) {

                String authorizationHeader = accessor.getFirstNativeHeader("Authorization");
                assert authorizationHeader != null;
                String token = authorizationHeader.substring(7);
                String username = null;
                SignedJWT signJWT;
				try {
					signJWT = SignedJWT.parse(token);
					username = signJWT.getJWTClaimsSet().getSubject();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
				SocketloginDto userDetails = accountLoginService.loadUserByUsername(username);
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                accessor.setUser(usernamePasswordAuthenticationToken);
            }

            return message;
        }

    });
}

}