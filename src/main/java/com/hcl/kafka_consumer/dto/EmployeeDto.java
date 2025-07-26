package com.hcl.kafka_consumer.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto implements Serializable {
    @Email(message = "Email not valid")
    private String email;
    private String name;
    private Double salary;
}
