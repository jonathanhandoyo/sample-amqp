package com.jonathan.domain;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "chat-bot", type = "messages")
public class Message {

    public Long timestamp;

    @Transient
    public Conversation conversation;
    public String conversationId;

    @Transient
    public User originator;
    public String originatorId;

    public String contentType;
    public String contentText;
    public byte[] contentBinary;
}
