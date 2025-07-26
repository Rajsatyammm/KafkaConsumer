package com.hcl.kafka_consumer.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Email
    @Column(unique = true)
    private String email;
    private String name;
    private Double salary;
    private boolean isProduced;
    private boolean isConsumed;
    private boolean isAckReceived;
}
