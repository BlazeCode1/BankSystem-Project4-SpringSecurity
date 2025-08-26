package org.example.banksystemproject4springsecurity.Service;

import lombok.RequiredArgsConstructor;
import org.example.banksystemproject4springsecurity.Api.ApiException;
import org.example.banksystemproject4springsecurity.DTO.In.EmployeeDTO;
import org.example.banksystemproject4springsecurity.Model.Employee;
import org.example.banksystemproject4springsecurity.Model.User;
import org.example.banksystemproject4springsecurity.Repository.AuthRepository;
import org.example.banksystemproject4springsecurity.Repository.EmployeeRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final AuthRepository authRepository;
    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }



    public void register(EmployeeDTO employeeDTO){
        User user = new User();
        Employee employee = new Employee();


        //setting normal user credentials
        user.setUsername(employeeDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(employeeDTO.getPassword()));
        user.setEmail(employeeDTO.getEmail());
        user.setName(employeeDTO.getName());
        user.setRole("EMPLOYEE");

        //employee Information
        employee.setSalary(employeeDTO.getSalary());
        employee.setPosition(employeeDTO.getPosition());

        //setting each other up to not get ``attempted to assign id from null one-to-one property`` error
        user.setEmployee(employee);
        employee.setUser(user);


        employeeRepository.save(employee);
        authRepository.save(user);
    }


    public void updateEmployee(Integer current_User_Id, Integer employee_id, EmployeeDTO employeeDTO){
        if(!current_User_Id.equals(employee_id))
            throw new ApiException("Not authorized");
        User user = authRepository.findUsersById(employee_id);
        if(user == null)
            throw new ApiException("User not found");
        user.setName(employeeDTO.getName());
        user.setUsername(employeeDTO.getUsername());
        user.setEmail(employeeDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
        user.getEmployee().setSalary(employeeDTO.getSalary());
        user.getEmployee().setPosition(employeeDTO.getPosition());
        authRepository.save(user);
    }

    public void deleteEmployee(Integer current_user_id,Integer employee_id){
        if(!current_user_id.equals(employee_id))
            throw new ApiException("Not authorized");
        User user = authRepository.findUserById(employee_id);
        if(user == null)
            throw new ApiException("Employee Not Found");
        authRepository.delete(user);
    }


}
