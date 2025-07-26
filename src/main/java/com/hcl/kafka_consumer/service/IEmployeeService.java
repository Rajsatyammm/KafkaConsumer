package com.hcl.kafka_consumer.service;

import com.hcl.kafka_consumer.dto.EmployeeDto;
import com.hcl.kafka_consumer.models.Employee;

public interface IEmployeeService {
    Employee saveEmployee(EmployeeDto employeeDto);
    Employee deleteEmployee(EmployeeDto employeeDto);
    Employee deleteEmployee(Integer id);
}
