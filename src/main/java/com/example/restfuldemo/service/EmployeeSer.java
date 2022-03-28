package com.example.restfuldemo.service;

import com.example.restfuldemo.entity.Employee;

import java.util.List;

public interface EmployeeSer {
    List<Employee> findEmployee();
    Employee findById(Long id);
    Employee createEmployee(Employee employee);
    //void deleteEmployee();
    void deleteById(Long id);
    Employee updateEmployee(Employee employee);
    Employee updateEmployeeName(Long id, String name);
}
