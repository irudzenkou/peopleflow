package com.peopleflow.controller;

import com.peopleflow.exception.AddEmployeeException;
import com.peopleflow.exception.UpdateEmployeeException;
import com.peopleflow.core.EmployeeDto;
import com.peopleflow.core.EmployeeState;
import com.peopleflow.service.EmployeeService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void addNewEmployeeTest() throws Exception {
        String id = "11";
        when(employeeService.addEmployee(any(EmployeeDto.class))).thenReturn(id);

        mvc.perform(post("/employees").content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id", Is.is(id)));

        verify(employeeService).addEmployee(any(EmployeeDto.class));
    }

    @Test
    public void changeStatusToInCheck() throws Exception {
        String id = "11";
        EmployeeState state = EmployeeState.APPROVED;

        mvc.perform(post("/employees/{id}/check", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(employeeService).check(eq(id));
    }

    @Test
    public void changeStatusToApproved() throws Exception {
        String id = "11";
        EmployeeState state = EmployeeState.APPROVED;

        mvc.perform(post("/employees/{id}/approve", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(employeeService).approve(eq(id));
    }

    @Test
    public void changeStatusToActive() throws Exception {
        String id = "11";
        EmployeeState state = EmployeeState.APPROVED;

        mvc.perform(post("/employees/{id}/activate", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(employeeService).activate(eq(id));
    }

    @Test
    public void addNewEmployeeError() throws Exception {
        when(employeeService.addEmployee(any(EmployeeDto.class))).thenThrow(AddEmployeeException.class);
        String errorMessage = ResponseErrorMessageMapper.getResponseErrorMessage(AddEmployeeException.class);

        mvc.perform(post("/employees").content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMessage", Is.is(errorMessage)));
    }

    @Test
    public void changeStatusToInCheckError() throws Exception {
        doThrow(UpdateEmployeeException.class).when(employeeService).check(any(String.class));
        String errorMessage = ResponseErrorMessageMapper.getResponseErrorMessage(UpdateEmployeeException.class);

        mvc.perform(post("/employees/{id}/check", "11").content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMessage", Is.is(errorMessage)));
    }

    @Test
    public void changeStatusToApprovedError() throws Exception {
        doThrow(UpdateEmployeeException.class).when(employeeService).approve(any(String.class));
        String errorMessage = ResponseErrorMessageMapper.getResponseErrorMessage(UpdateEmployeeException.class);

        mvc.perform(post("/employees/{id}/approve", "11").content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMessage", Is.is(errorMessage)));
    }

    @Test
    public void changeStatusToActivatedError() throws Exception {
        doThrow(UpdateEmployeeException.class).when(employeeService).activate(any(String.class));
        String errorMessage = ResponseErrorMessageMapper.getResponseErrorMessage(UpdateEmployeeException.class);

        mvc.perform(post("/employees/{id}/activate", "11").content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMessage", Is.is(errorMessage)));
    }
}
