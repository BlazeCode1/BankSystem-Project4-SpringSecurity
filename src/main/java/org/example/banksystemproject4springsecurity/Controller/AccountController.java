package org.example.banksystemproject4springsecurity.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.banksystemproject4springsecurity.Api.ApiResponse;
import org.example.banksystemproject4springsecurity.Model.Account;
import org.example.banksystemproject4springsecurity.Model.User;
import org.example.banksystemproject4springsecurity.Service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @GetMapping("/get")
    public ResponseEntity<?> getAccounts(){
        return ResponseEntity.status(200).body(accountService.getAllAccounts());
    }

    @GetMapping("/my-accounts")
    public ResponseEntity<?> getMyAccounts(@AuthenticationPrincipal User user){

        return ResponseEntity.status(200).body(accountService.getMyAccounts(user.getId()));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@AuthenticationPrincipal User user,@RequestBody @Valid Account account){
    accountService.createAccount(user.getId(), account);
    return ResponseEntity.status(200).body(new ApiResponse("Account Created!"));
    }


    @PutMapping("/update/{account_id}")
    public ResponseEntity<?> updateAccount
            (
                    @AuthenticationPrincipal User user,
                    @PathVariable Integer account_id,
                    @RequestBody @Valid Account account
            ){
        accountService.updateAccount(user.getId(),account_id, account);
        return ResponseEntity.status(200).body(new ApiResponse("Account Updated Successfully"));
    }


    @DeleteMapping("/delete/{account_id}")
    public ResponseEntity<?> deleteAccount(@AuthenticationPrincipal User user,@PathVariable Integer account_id){
        accountService.deleteAccount(user.getId(), account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account deleted Successfully"));
    }

    @PostMapping("/{account_id}/activate")
    public ResponseEntity<?> activateAccount(@AuthenticationPrincipal User user,@PathVariable Integer account_id){
        accountService.activateAccount(user.getId(), account_id);

        return ResponseEntity.status(200).body(new ApiResponse("Account Activated"));
    }


    @GetMapping("/{account_id}")
    public ResponseEntity<?> getAccount(@AuthenticationPrincipal User user,@PathVariable Integer account_id){
        return ResponseEntity.status(200).body(accountService.getAccount(user.getId(),account_id));
    }

    @PostMapping("/{account_id}/withdraw/{amount}")
    public ResponseEntity<?> withdraw(
            @AuthenticationPrincipal User user,
            @PathVariable Integer account_id,
            @PathVariable Double amount){
        accountService.withdraw(user.getId(), account_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Withdraw Successfully"));
    }

    @PostMapping("/{account_id}/deposit/{amount}")
    public ResponseEntity<?> deposit(
            @AuthenticationPrincipal User user,
            @PathVariable Integer account_id,
            @PathVariable Double amount
    ){
        accountService.deposit(user.getId(), account_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Deposited Successfully"));
    }

    @PostMapping("/transfer/{from_account_id}/{to_account_id}/{amount}")
    public ResponseEntity<?> transferTo(@AuthenticationPrincipal User user, @PathVariable Integer from_account_id,@PathVariable Integer to_account_id,@PathVariable Double amount){
        accountService.transferTo(user.getId(), from_account_id,to_account_id,amount);
    return ResponseEntity.status(200).body(new ApiResponse("Transfer Complete"));
    }


    @PutMapping("/block/{account_id}")
    public ResponseEntity<?> blockAccount(
            @AuthenticationPrincipal User user,
            @PathVariable Integer account_id )
    {
        accountService.blockAccount(user.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account Blocked!"));
    }


}
