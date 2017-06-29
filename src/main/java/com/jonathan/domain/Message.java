package com.jonathan.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "chat-bot", type = "messages")
public class Message {

    @Id
    private String id;
    private Long timestamp;

    private String conversation;
    private String originator;

    private String contentType;
    private String contentText;
    private byte[] contentBinary;
}
