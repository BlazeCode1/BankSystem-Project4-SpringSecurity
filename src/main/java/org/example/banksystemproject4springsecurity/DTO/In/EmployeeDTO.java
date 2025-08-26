package org.example.banksystemproject4springsecurity.DTO.In;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    @NotEmpty(message = "Username Is Required!")
    @Size(min = 4,max = 10,message = "Size must be username >= 4 , username < 10")
    private String username;

    @NotEmpty(message = "User Password is Required!")
    @Size(min = 6)
    private String password;

    @NotEmpty(message = "Name Is Required!")
    @Size(min = 2,max = 20,message = "Name Length is >= 2 , <=20")
    private String name;

    @Email(message = "Invalid User Email")
    private String email;

    @NotEmpty(message = "Employee Position is required!")
    @Column(nullable = false)
    private String position;

    @NotEmpty(message = "Employee Salary Is Required!")
    @Column(nullable = false)
    private Double salary;
}
