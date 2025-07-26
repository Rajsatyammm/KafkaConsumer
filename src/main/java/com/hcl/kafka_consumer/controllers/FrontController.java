package com.hcl.kafka_consumer.controllers;


import com.hcl.kafka_consumer.constants.AppConstants;
import com.hcl.kafka_consumer.dto.EmployeeDto;
import com.hcl.kafka_consumer.models.Employee;
import com.hcl.kafka_consumer.response.ApiResponse;
import com.hcl.kafka_consumer.service.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class FrontController {

    private final IEmployeeService employeeService;

    public FrontController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveData(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.saveEmployee(employeeDto);
        ApiResponse response = ApiResponse.builder()
            .data(employee)
            .message(AppConstants.SUCCESS)
            .status(HttpStatus.CREATED)
            .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
