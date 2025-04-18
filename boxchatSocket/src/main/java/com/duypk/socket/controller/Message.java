package com.duypk.socket.controller;

import lombok.Builder;

@Builder
public record Message(String to, String message, String from) {}
