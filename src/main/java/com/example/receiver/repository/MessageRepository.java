package com.example.receiver.repository;

import com.example.receiver.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findTop10ByOrderByCreatedAtDesc();
    List<Message> findBySenderOrderByCreatedAtDesc(String sender);
}
