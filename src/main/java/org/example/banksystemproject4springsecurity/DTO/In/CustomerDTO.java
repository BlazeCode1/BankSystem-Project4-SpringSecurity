package org.example.banksystemproject4springsecurity.DTO.In;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
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

    @NotEmpty(message = "Customer Phone Number is Required!")
    @Pattern(regexp = "^05\\d{8}$")
    private String phone_number;

}
