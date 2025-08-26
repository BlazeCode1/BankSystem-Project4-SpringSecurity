package org.example.banksystemproject4springsecurity.Service;

import lombok.RequiredArgsConstructor;
import org.example.banksystemproject4springsecurity.Api.ApiException;
import org.example.banksystemproject4springsecurity.DTO.In.CustomerDTO;
import org.example.banksystemproject4springsecurity.Model.Customer;
import org.example.banksystemproject4springsecurity.Model.User;
import org.example.banksystemproject4springsecurity.Repository.AuthRepository;
import org.example.banksystemproject4springsecurity.Repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;


    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }


    public void register(CustomerDTO customerDTO){
        User user = new User();
        Customer customer = new Customer();



        //normal credentials assigning user
        user.setUsername(customerDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(customerDTO.getPassword()));
        user.setRole("CUSTOMER");
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());

        //customer information
        customer.setPhone_number(customerDTO.getPhone_number());


        customer.setUser(user);
        user.setCustomer(customer);

        customerRepository.save(customer);
        authRepository.save(user);
    }


    public void updateCustomer(Integer current_User_Id, Integer customer_id, CustomerDTO customerDTO){
        User user = authRepository.findUsersById(customer_id);
        if(!current_User_Id.equals(customer_id) && !user.getRole().equalsIgnoreCase("admin"))
            throw new ApiException("Not authorized");

        if(user == null)
            throw new ApiException("User not found");
        user.setName(customerDTO.getName());
        user.setUsername(customerDTO.getUsername());
        user.setEmail(customerDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        user.getCustomer().setPhone_number(customerDTO.getPhone_number());
        authRepository.save(user);
    }

    public void deleteCustomer(Integer current_user_id,Integer customer_id){
        if(!current_user_id.equals(customer_id))
            throw new ApiException("Not authorized");
        User user = authRepository.findUserById(customer_id);
        if(user == null)
            throw new ApiException("Customer Not Found");
        authRepository.delete(user);
    }
}
