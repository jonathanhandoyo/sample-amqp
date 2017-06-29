package com.jonathan.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(indexName = "chat-bot", type = "conversations")
public class Conversation {

    @Id
    private String id;
    private User.Provider.Key platform;

    private Set<String> admins = new HashSet<>();
    private Set<String> participants = new HashSet<>();
}
