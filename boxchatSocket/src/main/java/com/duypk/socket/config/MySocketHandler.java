package com.duypk.socket.config;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MySocketHandler extends TextWebSocketHandler {
	
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        System.out.println("Received: " + payload);

        // Trả lại client nội dung kèm chuỗi phản hồi
        session.sendMessage(new TextMessage("Server nhận được: " + payload));
    }

}
