package com.duypk.socket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {
	
	@MessageMapping("/chat.sendMessage") // /app/chat.sendMessage
    @SendTo("/topic/public")             // gửi lại tới tất cả client
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        // Xử lý chatbot ở đây (ví dụ gọi AI)
        String botReply = "Xin chào, tôi là chatbot!"; // simple reply
        ChatMessage botMessage = new ChatMessage();
        botMessage.setSender("Bot");
        botMessage.setContent(botReply);
        botMessage.setType(ChatMessage.MessageType.CHAT);

        return botMessage;
    }

}
