package com.peopleflow.config;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.dao.EmployeeRepository;
import com.peopleflow.service.EmployeeService;
import com.peopleflow.service.impl.KafkaEmployeeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class ApplicationConfig {

    @Value(value = "${kafka.employee.topic}")
    private String employeeTopic;

    @Bean
    public EmployeeService employeeService(KafkaTemplate<String, EmployeeDto> employeeKafkaTemplate,
                                           EmployeeRepository employeeRepository) {
        return new KafkaEmployeeService(employeeKafkaTemplate, employeeRepository, employeeTopic);
    }
}
