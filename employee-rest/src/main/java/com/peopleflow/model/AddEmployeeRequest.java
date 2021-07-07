package com.peopleflow.model;

import lombok.Data;

@Data
public class AddEmployeeRequest {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String skillsDescription;
}
