package com.peopleflow.service;

import com.peopleflow.core.EmployeeDto;
import com.peopleflow.model.AddEmployeeRequest;

public class EmployeeUtils {
    public static EmployeeDto convertToDto(AddEmployeeRequest request) {
        EmployeeDto dto = new EmployeeDto();
        dto.setAge(request.getAge());
        dto.setFirstName(request.getFirstName());
        dto.setLastName(request.getLastName());
        dto.setEmail(request.getEmail());
        dto.setSkillsDescription(request.getSkillsDescription());
        return dto;
    }
}
