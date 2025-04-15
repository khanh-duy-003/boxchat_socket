package com.duypk.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {
    
    @MessageMapping("/send") // /app/chat.sendMessage
    @SendTo("/receive-mess/receive")             // gửi lại tới tất cả client
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        // Xử lý chatbot ở đây (ví dụ gọi AI)
        String botReply = "Xin chào, tôi là chatbot!"; // simple reply
        ChatMessage botMessage = new ChatMessage();
        botMessage.setSender(message.getSender());
        botMessage.setContent(message.getContent());
        botMessage.setType(ChatMessage.MessageType.CHAT);

        return botMessage;
    }
    
    @Autowired
    SimpMessagingTemplate  messagingTemplate;
    
    // Gửi từ client WebSocket (STOMP)
    @MessageMapping("/notify") // => client gửi tới /app/notify
    @SendTo("/receive-mess/notifications") // gửi tới tất cả client đang lắng nghe
    public ChatMessage receiveFromWebSocket(@Payload ChatMessage notification) {
        return notification;
    }

    // Gửi từ REST API (ví dụ dùng cho admin, hoặc backend xử lý logic gì đó)
    @PostMapping("/api/notify")
    public void sendFromRest(@RequestBody ChatMessage notification) {
        messagingTemplate.convertAndSend("/receive-mess/notifications", notification);
    }

}