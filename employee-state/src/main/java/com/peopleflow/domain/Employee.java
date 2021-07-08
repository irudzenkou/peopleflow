package com.peopleflow.domain;

import com.peopleflow.core.EmployeeState;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    String id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "age")
    Integer age;

    @Column(name = "email")
    String email;

    @Column(name = "skills_description")
    String skillsDescription;

    @Column(name = "state")
    EmployeeState state;
}
