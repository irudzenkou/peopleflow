package com.peopleflow.service.state;

import com.peopleflow.core.EmployeeDto;

public interface EmployeeStateProcessor {

    void process(EmployeeDto dto) throws Exception;
}
