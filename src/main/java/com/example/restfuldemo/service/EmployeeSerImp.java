package com.example.restfuldemo.service;

import com.example.restfuldemo.dao.EmployeeRepo;
import com.example.restfuldemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeSerImp implements EmployeeSer{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public List<Employee> findEmployee() {
        List<Employee> eList = new ArrayList<>();
        employeeRepo.findAll().forEach(eList :: add);
        return eList;
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(new Employee(employee.getId(), employee.getName()));
    }

    /*
    @Override
    public void deleteEmployee() {
        employeeRepo.deleteAll();
    }
    */

    @Override
    public void deleteById(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> eList = employeeRepo.findById((long) employee.getId());
        if(eList.isPresent()){
            return employeeRepo.save(eList.get());
        }else{
            throw new NullPointerException();
        }
    }

    @Override
    public Employee updateEmployeeName(Long id, String name) {
        Optional<Employee> eList = employeeRepo.findById(id);
        if(eList.isPresent()){
            Employee e = eList.get();
            e.setName(name);
            return employeeRepo.save(e);
        }else{
            throw new NullPointerException();
        }
    }
}
