package com.hcl.kafka_consumer.config;//package com.hcl.kafka_producer.config;

import com.hcl.kafka_consumer.constants.KafkaTopic;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic getAdmin() {
        return TopicBuilder.name(KafkaTopic.SEND_ACKNOWLEDGEMENT)
            .build();
    }
}
