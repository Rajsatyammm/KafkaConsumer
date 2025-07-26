package com.hcl.kafka_consumer.producer;

import com.hcl.kafka_consumer.dto.EmployeeDto;

public interface IKafkaProducer {
    void produceDataToKafka(String topic, EmployeeDto employeeDto);
}
