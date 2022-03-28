package com.example.restfuldemo.controller;

import com.example.restfuldemo.entity.Employee;
import com.example.restfuldemo.service.EmployeeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeCon {

    @Autowired
    private EmployeeSer employeeSer;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        try{
            List<Employee> eList = employeeSer.findEmployee();
            if(eList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id){
        Employee e = employeeSer.findById(id);
        if(e == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        try{
            Employee e = employeeSer.createEmployee(employee);
            return new ResponseEntity<>(e, HttpStatus.CREATED);
        }
        catch(Exception e){
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee){
        Employee e = employeeSer.findById(id);
        if(e != null){
            e.setName(employee.getName());
            Employee upEmployee = employeeSer.updateEmployee(e);
            return new ResponseEntity<>(upEmployee, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deletEmployeeById(@PathVariable long id){
        try{
            employeeSer.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<Employee> patchEmployee(@PathVariable long id, @RequestBody Employee employee){
        try{
            Employee e = employeeSer.updateEmployeeName(id, employee.getName());
            return new ResponseEntity<>(e, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
