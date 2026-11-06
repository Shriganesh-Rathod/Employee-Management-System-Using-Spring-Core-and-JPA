package com.company.repository;

import com.company.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    //Here we are using the EntityManager to perform the CRUD operations on the Employee entity
    @PersistenceContext
    private EntityManager entityManager;

    //To save Employee
    @Override
    public void save(Employee employee) {
        entityManager.persist(employee);
    }
    //To Find Employee By ID
    @Override
    public Employee findById(Integer id){
        return entityManager.find(Employee.class, id);
    }

    //To Retrive All Employees
    @Override
    public List<Employee> findAll(){
        String jpql = "SELECT e FROM Employee e";

        TypedQuery<Employee> query = entityManager.createQuery(jpql,Employee.class);

        return query.getResultList();
    }

    //To Modify Employee
    @Override
    public Employee update(Employee employee){
        return entityManager.merge(employee);
    }

    @Override
    public Boolean delete (Integer id){
        Employee employee = entityManager.find(Employee.class,id);

        if(employee !=null){
            entityManager.remove(employee);
            return true;
        }
        return false;
    }

}
