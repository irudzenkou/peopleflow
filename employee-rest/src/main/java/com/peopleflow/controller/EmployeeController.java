package com.peopleflow.controller;

import com.peopleflow.Exception.EmployeeNotFoundException;
import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import com.peopleflow.model.AddEmployeeRequest;
import com.peopleflow.model.AddEmployeeResponse;
import com.peopleflow.service.EmployeeService;
import com.peopleflow.service.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employees")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public AddEmployeeResponse addEmployee(@RequestBody AddEmployeeRequest request) {
        EmployeeDto dto = EmployeeUtils.convertToDto(request);
        String id = employeeService.addEmployee(dto);
        return new AddEmployeeResponse(id);
    }

    @PostMapping("/employees/{id}/check")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void check(@PathVariable String id) {
        employeeService.check(id);
    }

    @PostMapping("/employees/{id}/approve")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void approve(@PathVariable String id) {
        employeeService.approve(id);
    }

    @PostMapping("/employees/{id}/activate")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void activate(@PathVariable String id) {
        employeeService.activate(id);
    }

    @GetMapping("/employees/{id}/state")
    @ResponseStatus(code = HttpStatus.OK)
    public EmployeeState state(@PathVariable String id) {
        EmployeeState state = employeeService.getStateById(id);
        if (state == null) {
            throw new EmployeeNotFoundException(id);
        }
        return state;
    }
}
