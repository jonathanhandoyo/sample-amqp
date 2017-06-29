package com.jonathan.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(indexName = "chat-bot", type = "users", refreshInterval = "-1")
public class User {

    @Id
    private String id;
    private String name;

    @Field(type = FieldType.Nested)
    private Set<Provider> providers = new HashSet<>();

    @Data
    public static class Provider {
        private Key key;
        private String value;

        public enum Key {
            FACEBOOK,
            SMS,
            WECHAT,
            WHATSAPP
        }
    }
}
