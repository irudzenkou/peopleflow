package com.peopleflow.service;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.service.state.EmployeeStateProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@AllArgsConstructor
public class EmployeeListener {

    private EmployeeStateProcessor stateProcessor;

    @KafkaListener(topics = "${kafka.employee.topic}")
    public void employeeEvent(EmployeeDto employeeDto) {
        log.info("Start processing employee event. ID = {}", employeeDto.getId());
        stateProcessor.process(employeeDto);
    }
}
