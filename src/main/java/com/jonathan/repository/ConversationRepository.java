package com.jonathan.repository;

import com.jonathan.domain.Conversation;
import com.jonathan.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends ElasticsearchRepository<Conversation, String> {
}
