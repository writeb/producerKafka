package com.example.receiver.api;

import com.example.receiver.model.Message;
import com.example.receiver.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private MessageRepository messageRepository;

    @PostMapping(consumes = "application/xml")
    public ResponseEntity<String> receiveMessage(@RequestBody Message message){
        messageRepository.save(message);
        kafkaTemplate.send("message.send", message.getSender(), message.getMessage());
        return ResponseEntity.ok("Message received");
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessages(@RequestParam(required = false) String sender){
        List<Message> messages;
        if (sender!=null){
            messages = messageRepository.findBySenderOrderByCreatedAtDesc(sender);
        } else {
            messages = messageRepository.findTop10ByOrderByCreatedAtDesc();
        }
        return ResponseEntity.ok(messages);

    }
}
