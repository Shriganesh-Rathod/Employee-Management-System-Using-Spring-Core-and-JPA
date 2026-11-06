
package com.company.service;

import com.company.entity.Employee;
import com.company.notification.NotificationService;
import com.company.repository.EmployeeRepository;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final NotificationService emailNotificationService;
    private final ObjectProvider<NotificationService> smsNotificationProvider;

    // Constructor injection
    @Autowired
    public EmployeeService(
            EmployeeRepository employeeRepository,
            NotificationService emailNotificationService,
            @Qualifier("smsNotificationService")
            ObjectProvider<NotificationService> smsNotificationProvider) {

        this.employeeRepository = employeeRepository;
        this.emailNotificationService = emailNotificationService;
        this.smsNotificationProvider = smsNotificationProvider;
    }

    // Register employee
    @Transactional
    public void registerEmployee(Employee employee) {

        validateEmployee(employee);
        employeeRepository.save(employee);

        emailNotificationService.sendNotification(
                "Employee registered successfully: " + employee.getName()
        );
    }

    // Retrieve employee by ID
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    // Retrieve all employees
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Update employee
    @Transactional
    public Employee updateEmployee(Employee employee) {

        validateEmployee(employee);

        Employee updatedEmployee = employeeRepository.update(employee);

        emailNotificationService.sendNotification(
                "Employee updated successfully: " + updatedEmployee.getName()
        );

        return updatedEmployee;
    }

    // Delete employee
    @Transactional
    public boolean deleteEmployee(Integer id) {

        boolean isDeleted = employeeRepository.delete(id);

        if (isDeleted) {
            emailNotificationService.sendNotification(
                    "Employee deleted successfully with ID: " + id
            );
        }

        return isDeleted;
    }

    // Send SMS notification on demand
    public void sendSmsNotification(String message) {

        NotificationService smsNotificationService =
                smsNotificationProvider.getObject();

        smsNotificationService.sendNotification(message);
    }

    // Validate employee data
    private void validateEmployee(Employee employee) {

        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        if (employee.getName() == null || employee.getName().isBlank()) {
            throw new IllegalArgumentException("Employee name is required");
        }

        if (employee.getEmailAddress() == null
                || employee.getEmailAddress().isBlank()) {
            throw new IllegalArgumentException("Employee email is required");
        }

        if (employee.getDepartments() == null
                || employee.getDepartments().isBlank()) {
            throw new IllegalArgumentException("Employee department is required");
        }

        if (employee.getSalary() == null
                || employee.getSalary() <= 0.0) {
            throw new IllegalArgumentException(
                    "Employee salary must be greater than zero"
            );
        }
    }
}
