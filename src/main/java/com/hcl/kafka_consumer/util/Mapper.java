package com.hcl.kafka_consumer.util;

import com.hcl.kafka_consumer.dto.EmployeeDto;
import com.hcl.kafka_consumer.models.Employee;

public class Mapper {
    public static Employee toModel(EmployeeDto employeeDto) {
        return Employee.builder()
                .email(employeeDto.getEmail())
                .name(employeeDto.getName())
                .salary(employeeDto.getSalary())
                .build();
    }

    public static EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .email(employee.getEmail())
                .name(employee.getName())
                .salary(employee.getSalary())
                .build();
    }
}
