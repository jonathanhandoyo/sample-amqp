package com.jonathan.repository;

import com.jonathan.domain.Conversation;
import com.jonathan.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ConversationRepository extends ElasticsearchRepository<Conversation, String> {

    Stream<Conversation> findAllByPlatformAndParticipantsIn(User.Provider.Key platform, String participantId);
}
