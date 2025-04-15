package com.duypk.socket.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    private String sender;
    private String content;
    private MessageType type;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
}