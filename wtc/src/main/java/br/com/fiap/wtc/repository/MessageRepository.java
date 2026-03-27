package br.com.fiap.wtc.repository;

import br.com.fiap.wtc.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByConversationIdOrderByCreatedAtAsc(String conversationId);
    List<Message> findByToUserIdOrFromUserId(String toUserId, String fromUserId);
}