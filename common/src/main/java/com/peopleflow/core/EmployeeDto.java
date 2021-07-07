package com.peopleflow.core;

import lombok.Data;

@Data
public class EmployeeDto {
    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String skillsDescription;
    private EmployeeState state;
}
