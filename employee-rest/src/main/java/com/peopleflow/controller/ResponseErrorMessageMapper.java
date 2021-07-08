package com.peopleflow.controller;

import com.peopleflow.Exception.AddEmployeeException;
import com.peopleflow.Exception.EmployeeNotFoundException;
import com.peopleflow.Exception.UpdateEmployeeException;

import java.util.HashMap;
import java.util.Map;

public class ResponseErrorMessageMapper {
    private static final Map<Class<? extends RuntimeException>, String> map = new HashMap<>();

    static {
        map.put(AddEmployeeException.class, "Unable to add employee");
        map.put(UpdateEmployeeException.class, "Unable to update employee state");
        map.put(EmployeeNotFoundException.class, "Employee not found");

    }

    public static String getResponseErrorMessage(Class clazz) {
        if (clazz == null) {
            return null;
        }
        String message;
        message = map.get(clazz);
        return message == null ? "Internal error" : message;
    }
}
