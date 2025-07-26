package com.hcl.kafka_consumer.producer;

import com.hcl.kafka_consumer.dto.EmployeeDto;
import com.hcl.kafka_consumer.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerImpl implements IKafkaProducer {

    private final KafkaTemplate<String, EmployeeDto> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<String, EmployeeDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceDataToKafka(String topic, EmployeeDto employeeDto) {
        kafkaTemplate.send(topic, employeeDto.getEmail(), employeeDto);
        log.info("Ack produced to kafka :: {}", employeeDto.getEmail());
        System.out.println("Ack produced to kafka :: " + employeeDto);
    }
}