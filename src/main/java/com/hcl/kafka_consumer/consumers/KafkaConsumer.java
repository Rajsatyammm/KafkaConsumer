package com.hcl.kafka_consumer.consumers;

import com.hcl.kafka_consumer.constants.KafkaTopic;
import com.hcl.kafka_consumer.dto.EmployeeDto;
import com.hcl.kafka_consumer.models.Employee;
import com.hcl.kafka_consumer.producer.IKafkaProducer;
import com.hcl.kafka_consumer.repositories.IEmployeeRepository;
import com.hcl.kafka_consumer.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    private final IEmployeeRepository employeeRepository;

    private final IKafkaProducer kafkaProducer;

    public KafkaConsumer(IEmployeeRepository employeeRepository, IKafkaProducer kafkaProducer) {
        this.employeeRepository = employeeRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @KafkaListener(topics = KafkaTopic.PRODUCE_MESSAGE_TOPIC)
    public void consume(ConsumerRecord<String, EmployeeDto> record) {
        log.info("Received data from kafka: ");
        log.info("Key :: {}", record.key());
        log.info("Value :: {}", record.value().toString());
        EmployeeDto employeeDto = record.value();
        Employee employee = employeeRepository.findByEmail(employeeDto.getEmail()).orElse(null);
        if (employee != null) {
            log.info("Sending Ack to kafka topic :: {}", KafkaTopic.SEND_ACKNOWLEDGEMENT);
            employee.setConsumed(true);
            employeeRepository.save(employee);
            kafkaProducer.produceDataToKafka(KafkaTopic.SEND_ACKNOWLEDGEMENT, Mapper.toDto(employee));
        } else {
            log.error("Something wrong happen, data does not exist in DB with email :: {}", record.value().getEmail());
        }
    }

}
