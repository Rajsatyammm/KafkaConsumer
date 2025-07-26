package com.hcl.kafka_consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.kafka_consumer.constants.AppConstants;
import com.hcl.kafka_consumer.dto.EmployeeDto;
import com.hcl.kafka_consumer.exceptions.UserAlreadyExists;
import com.hcl.kafka_consumer.exceptions.UserNotFoundException;
import com.hcl.kafka_consumer.models.Employee;
import com.hcl.kafka_consumer.repositories.IEmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    private final ObjectMapper objectMapper;

    public EmployeeServiceImpl(IEmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {
        if(employeeRepository.existsByEmail(employeeDto.getEmail()))
            throw new UserAlreadyExists(AppConstants.USER_ALREADY_EXISTS);
        return employeeRepository.save(objectMapper.convertValue(employeeDto, Employee.class));
    }

    @Override
    public Employee deleteEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findByEmail(employeeDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(AppConstants.USER_NOT_FOUND));
        employeeRepository.delete(objectMapper.convertValue(employeeDto, Employee.class));
        return employee;
    }

    @Override
    public Employee deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(AppConstants.USER_NOT_FOUND));
        employeeRepository.delete(employee);
        return employee;
    }
}
