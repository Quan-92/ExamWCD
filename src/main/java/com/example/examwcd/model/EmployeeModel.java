package com.example.examwcd.model;

import com.example.examwcd.entity.Employee;

import java.util.List;

public interface EmployeeModel {
    boolean save(Employee obj);
    List<Employee> findAll();
}
