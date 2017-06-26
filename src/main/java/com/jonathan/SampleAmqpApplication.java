package com.jonathan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = {ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
public class SampleAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleAmqpApplication.class, args);

        log.info("\n" +
                "======================================\n" +
                "              (a Jonathan Production) \n" +
                "======================================\n" +
                " - Spring Boot                        \n" +
                " - AMQP - RabbitMQ                    \n" +
                " - ElasticSearch                      \n" +
                "======================================\n" +
                "");
    }
}
