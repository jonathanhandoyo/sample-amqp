package com.jonathan.repository;

import com.jonathan.domain.Message;
import com.jonathan.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends ElasticsearchRepository<Message, String> {
}
