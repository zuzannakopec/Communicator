package com.example.backend.repository;

import com.example.backend.model.Chatroom;
import com.example.backend.model.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    List<Message> findAllById(Long id);
}
