package br.com.fiap.wtc.controller;

import br.com.fiap.wtc.dto.request.MessageRequest;
import br.com.fiap.wtc.dto.response.MessageResponse;
import br.com.fiap.wtc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponse> send(@RequestBody MessageRequest request) {
        return ResponseEntity.status(201).body(messageService.send(request));
    }

    @GetMapping("/conversation/{conversationId}")
    public ResponseEntity<List<MessageResponse>> getConversation(@PathVariable String conversationId) {
        return ResponseEntity.ok(messageService.getConversation(conversationId));
    }

    @GetMapping("/inbox/{userId}")
    public ResponseEntity<List<MessageResponse>> getInbox(@PathVariable String userId) {
        return ResponseEntity.ok(messageService.getInbox(userId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MessageResponse> updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(messageService.updateStatus(id, body.get("status")));
    }
}