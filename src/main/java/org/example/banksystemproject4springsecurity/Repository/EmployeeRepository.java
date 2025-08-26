package org.example.banksystemproject4springsecurity.Repository;

import org.example.banksystemproject4springsecurity.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findEmployeeById(Integer id);

}
