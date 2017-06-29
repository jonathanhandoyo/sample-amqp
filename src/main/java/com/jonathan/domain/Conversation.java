package com.jonathan.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(indexName = "chat-bot", type = "conversations")
public class Conversation {

    @Id
    private String id;
    private User.Provider.Key platform;

    @Transient
    private Set<User> admins = new HashSet<>();
    private Set<String> adminIds = new HashSet<>();

    @Transient
    private Set<User> participants = new HashSet<>();
    private Set<String> participantIds = new HashSet<>();
}
