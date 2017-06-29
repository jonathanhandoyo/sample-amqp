package com.jonathan.repository;

import com.jonathan.domain.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

    Stream<User> findAllByProvidersIn(User.Provider provider);
    Stream<User> findAllByName(String name);
}
