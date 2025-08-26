package org.example.banksystemproject4springsecurity.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.banksystemproject4springsecurity.Api.ApiResponse;
import org.example.banksystemproject4springsecurity.DTO.In.EmployeeDTO;
import org.example.banksystemproject4springsecurity.Model.User;
import org.example.banksystemproject4springsecurity.Service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllEmployees(){
        return ResponseEntity.status(200).body(employeeService.getEmployees());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody EmployeeDTO employeeDTO){
        employeeService.register(employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Registered Successfully"));
    }

    @PutMapping("/update/{employee_id}")
    public ResponseEntity<?> updateEmployee(@AuthenticationPrincipal User user,@PathVariable Integer employee_id,@Valid @RequestBody EmployeeDTO employeeDTO){
        employeeService.updateEmployee(user.getId(),employee_id,employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Updated Successfully"));
    }


    @DeleteMapping("/delete/{employee_id}")
    public ResponseEntity<?> deleteEmployee(@AuthenticationPrincipal User user,@PathVariable Integer employee_id){
        employeeService.deleteEmployee(user.getId(),employee_id);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Deleted Successfully"));
    }

}
