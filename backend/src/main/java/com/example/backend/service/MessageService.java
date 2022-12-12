package com.example.backend.service;


import com.example.backend.model.Chatroom;
import com.example.backend.model.Message;
import com.example.backend.repository.MessageRepository;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<Message> findMessageHistory(Long chatroomId) {
        return this.messageRepository.findAllByChatroomId(chatroomId);
    }

    public void updateMessageHistory(String text, Chatroom chatroom) {
        Message message = new Message();
        message.setText(text);
        message.setChatroom(chatroom);
        this.messageRepository.save(message);
    }

}
