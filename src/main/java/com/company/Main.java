package com.company;

import com.company.config.AppConfig;
import com.company.entity.Employee;
import com.company.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // To get EmployeeService bean from Spring container
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 1 to Register Employee");
            System.out.println("Enter 2 to Get Employee By ID");
            System.out.println("Enter 3 to Get All Employees");
            System.out.println("Enter 4 to Update Employee");
            System.out.println("Enter 5 to Delete Employee");
            System.out.println("Enter 0 to Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    Employee employee = new Employee();

                    System.out.println("Enter Employee Name:");
                    employee.setName(scanner.nextLine());

                    System.out.println("Enter Employee Email:");
                    employee.setEmailAddress(scanner.nextLine());

                    System.out.println("Enter Employee Department:");
                    employee.setDepartments(scanner.nextLine());

                    System.out.println("Enter Employee Salary:");
                    employee.setSalary(Double.parseDouble(scanner.nextLine()));

                    employeeService.registerEmployee(employee);
                }
                case 2 -> {
                    System.out.println("Enter Employee ID:");
                    int id = Integer.parseInt(scanner.nextLine());
                    Employee foundEmployee = employeeService.getEmployeeById(id);

                    if (foundEmployee != null) {
                        System.out.println(foundEmployee);
                    } else {
                        System.out.println("Employee not found.");
                    }
                }
                case 3 -> System.out.println(employeeService.getAllEmployees());
                case 4 -> {
                    System.out.println("Enter Employee ID:");
                    int id = Integer.parseInt(scanner.nextLine());
                    Employee foundEmployee = employeeService.getEmployeeById(id);

                    if (foundEmployee == null) {
                        System.out.println("Employee not found.");
                    } else {
                        System.out.println("Enter Employee Name:");
                        foundEmployee.setName(scanner.nextLine());

                        System.out.println("Enter Employee Email:");
                        foundEmployee.setEmailAddress(scanner.nextLine());

                        System.out.println("Enter Employee Department:");
                        foundEmployee.setDepartments(scanner.nextLine());

                        employeeService.updateEmployee(foundEmployee);
                        System.out.println("Employee updated successfully.");
                    }
                }
                case 5 -> {
                    System.out.println("Enter Employee ID:");
                    int id = Integer.parseInt(scanner.nextLine());
                    employeeService.deleteEmployee(id);
                    System.out.println("Employee deleted successfully.");
                }
                case 0 -> {
                    context.close();
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

    }
}