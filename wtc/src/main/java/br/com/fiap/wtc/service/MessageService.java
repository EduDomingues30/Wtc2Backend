package br.com.fiap.wtc.service;

import br.com.fiap.wtc.dto.request.MessageRequest;
import br.com.fiap.wtc.dto.response.MessageResponse;
import br.com.fiap.wtc.exception.ResourceNotFoundException;
import br.com.fiap.wtc.model.Message;
import br.com.fiap.wtc.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public MessageResponse send(MessageRequest request) {
        Message message = new Message();
        message.setConversationId(request.getFromUserId() + "_" + request.getToUserId());
        message.setFromUserId(request.getFromUserId());
        message.setToUserId(request.getToUserId());
        message.setContent(request.getContent());
        message.setMessageType(request.getMessageType() != null ? request.getMessageType() : "TEXT");
        message.setStatus("SENT");
        message.setCreatedAt(LocalDateTime.now());
        return toResponse(messageRepository.save(message));
    }

    public List<MessageResponse> getConversation(String conversationId) {
        return messageRepository.findByConversationIdOrderByCreatedAtAsc(conversationId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<MessageResponse> getInbox(String userId) {
        return messageRepository.findByToUserIdOrFromUserId(userId, userId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public MessageResponse updateStatus(String id, String status) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensagem", id));
        message.setStatus(status);
        return toResponse(messageRepository.save(message));
    }

    private MessageResponse toResponse(Message message) {
        MessageResponse response = new MessageResponse();
        response.setId(message.getId());
        response.setConversationId(message.getConversationId());
        response.setFromUserId(message.getFromUserId());
        response.setToUserId(message.getToUserId());
        response.setContent(message.getContent());
        response.setMessageType(message.getMessageType());
        response.setStatus(message.getStatus());
        response.setCreatedAt(message.getCreatedAt());
        return response;
    }
}