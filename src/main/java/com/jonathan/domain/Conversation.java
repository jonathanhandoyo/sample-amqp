package com.jonathan.domain;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Set;

@Data
@Document(indexName = "chat-bot", type = "conversations")
public class Conversation {

    @Transient
    public Set<User> admins;
    public Set<String> adminIds;

    @Transient
    public Set<User> participants;
    public Set<String> participantIds;
}
