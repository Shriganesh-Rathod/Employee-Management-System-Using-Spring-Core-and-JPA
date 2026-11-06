package com.company.repository;

import com.company.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    //To Register an Employee We are using the Save() it will Perform persist()
    void save(Employee employee);

    //Here we are finding employee using ID Using find() operation
    Employee findById(Integer id);

    //Here we are Getting all the employees using findAll() operation
    List<Employee> findAll();

    //Here we are Modifying// Updating Existing Employee using Update() operation
    Employee update(Employee employee);

    //Here we are Deleting Employee using Delete() which performs find()+remove() operation
    Boolean delete(Integer id);

}
