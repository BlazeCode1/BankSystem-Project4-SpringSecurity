package org.example.banksystemproject4springsecurity.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.banksystemproject4springsecurity.Api.ApiResponse;
import org.example.banksystemproject4springsecurity.DTO.In.CustomerDTO;
import org.example.banksystemproject4springsecurity.DTO.In.EmployeeDTO;
import org.example.banksystemproject4springsecurity.Model.User;
import org.example.banksystemproject4springsecurity.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCustomer(){
        return ResponseEntity.status(200).body(customerService.getAllCustomer());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody CustomerDTO customerDTO){
        customerService.register(customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Registered Successfully"));
    }
    @PutMapping("/update/{customer_id}")
    public ResponseEntity<?> updateCustomer(@AuthenticationPrincipal User user, @PathVariable Integer customer_id, @Valid @RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(user.getId(),customer_id,customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Updated Successfully"));
    }


    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity<?> deleteCustomer(@AuthenticationPrincipal User user,@PathVariable Integer customer_id){
        customerService.deleteCustomer(user.getId(),customer_id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer Deleted Successfully"));
    }

}
