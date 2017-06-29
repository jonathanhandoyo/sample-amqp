package com.jonathan.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document(indexName = "chat-bot", type = "users")
public class User {

    @Id
    private String id;
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
