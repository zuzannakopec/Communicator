
package com.example.backend.config;

import com.example.backend.handler.ChatroomHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    protected final ChatroomHandler chatroomHandler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(this.chatroomHandler, "/chat");
    }

    public WebSocketConfig(final ChatroomHandler chatroomHandler) {
        this.chatroomHandler = chatroomHandler;
    }
}
