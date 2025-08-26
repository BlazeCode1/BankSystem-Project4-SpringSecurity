package org.example.banksystemproject4springsecurity.Service;


import lombok.RequiredArgsConstructor;
import org.example.banksystemproject4springsecurity.Api.ApiException;
import org.example.banksystemproject4springsecurity.Model.Account;
import org.example.banksystemproject4springsecurity.Model.User;
import org.example.banksystemproject4springsecurity.Repository.AccountRepository;
import org.example.banksystemproject4springsecurity.Repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AuthRepository authRepository;
    private final AccountRepository accountRepository;

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public void createAccount(Integer customer_id,Account account){
        User user = authRepository.findUserById(customer_id);
        if(user == null)
            throw new ApiException("Customer Not Found");
        account.setIsActive(false);
        account.setCustomer(user.getCustomer());
        accountRepository.save(account);
    }

    public void updateAccount(Integer customer_id,Integer account_id,Account account){
        User user = authRepository.findUserById(customer_id);
       if(user == null)
           throw new ApiException("User Not Found");
       Account oldAccount = accountRepository.findAccountById(account_id);
       if(!oldAccount.getCustomer().equals(user.getCustomer())&& !user.getRole().equalsIgnoreCase("ADMIN"))
           throw new ApiException("Un authorized!");

       oldAccount.setAccount_number(account.getAccount_number());
       oldAccount.setBalance(account.getBalance());
       accountRepository.save(oldAccount);
    }

    public void deleteAccount(Integer user_id,Integer account_id){
        User user = authRepository.findUserById(user_id);
        if(user == null)
            throw new ApiException("Not Authorized!");
        Account account = accountRepository.findAccountById(account_id);
        if(account == null)
            throw new ApiException("Not Authorized!");
        if(!account.getCustomer().equals(user.getCustomer()))
            throw new ApiException("Un Authorized!");
    }

    public List<Account> getMyAccounts(Integer customer_id){
        User user = authRepository.findUserById(customer_id);
        if(user == null)
            throw new ApiException("User Not Found");
        return accountRepository.getAccountByCustomer_User(user);
    }

    public Account getAccount(Integer customer_id,Integer account_id){
        User user = authRepository.findUserById(customer_id);
        if(user == null)
            throw new ApiException("User Not Found");
        Account account = accountRepository.findAccountById(account_id);
        if (!account.getCustomer().equals(user.getCustomer()))
            throw new ApiException("Un Authorized");

        return account;
    }

    public void activateAccount(Integer user_id,Integer account_id){
        User user = authRepository.findUserById(user_id);
        if(user == null)
            throw new ApiException("Un Authorized!");

        if(!user.getRole().equalsIgnoreCase("ADMIN") && !user.getRole().equalsIgnoreCase("EMPLOYEE"))
            throw new ApiException("Un Authorized!");

        Account account = accountRepository.findAccountById(account_id);
        if(account == null)
            throw new ApiException("Account Not Found");
        account.setIsActive(true);
        accountRepository.save(account);
    }

    public void deposit(Integer customer_id,Integer account_id,Double balance){
        User user = authRepository.findUserById(customer_id);
        if(user == null)
            throw new ApiException("User Not Found");
        Account account = accountRepository.findAccountById(account_id);
        if(account == null)
            throw new ApiException("Account Not Found");
        if(!account.getIsActive()) throw new ApiException("Account Not Activated");

        if(!account.getCustomer().equals(user.getCustomer()))
            throw new ApiException("Not Authorized");
        if (balance == null || balance <= 0) throw new ApiException("Deposit must be greater than 0");

        account.setBalance(account.getBalance() + balance);
        accountRepository.save(account);
    }


    public void withdraw(Integer customer_id,Integer account_id,Double withdraw){
        User user = authRepository.findUserById(customer_id);
        if(user == null)
            throw new ApiException("User Not Found");
        Account account = accountRepository.findAccountById(account_id);
        if(account == null)
            throw new ApiException("Account Not Found");
        if(!account.getIsActive()) throw new ApiException("Account Not Activated");
        if(!account.getCustomer().equals(user.getCustomer()))
            throw new ApiException("Not Authorized");

        if (withdraw == null || withdraw <= 0) throw new ApiException("Withdrawal must be greater than 0");
        if (account.getBalance() < withdraw) throw new ApiException("Insufficient funds");

        account.setBalance(account.getBalance() - withdraw);
        accountRepository.save(account);
    }


    public void transferTo(Integer user_id,Integer from_account_id,Integer to_account_id,Double amount){
        User user= authRepository.findUserById(user_id);
        if(user == null)
            throw new ApiException("User Not Found");

        Account from_account = accountRepository.findAccountById(from_account_id);
        if(from_account == null) throw new ApiException("Account Not Found");
        if(!from_account.getCustomer().equals(user.getCustomer())) throw new ApiException("Not Authorized");
        if (!from_account.getIsActive()) throw new ApiException("Account Not Active");



        Account to_account = accountRepository.findAccountById(to_account_id);
        if (to_account == null) throw new ApiException("to Account Not Found");
        if (!to_account.getIsActive()) throw new ApiException("Account to be transferred to is inactive");
        if( amount == null || amount <= 0) throw new ApiException("Valid Amount is required!");
        if(from_account.getBalance() < amount) throw new ApiException("Insufficient Balance");

        from_account.setBalance(from_account.getBalance() - amount);
        to_account.setBalance(to_account.getBalance() + amount);
        accountRepository.save(from_account);
        accountRepository.save(to_account);

    }



    public void blockAccount(Integer user_id,Integer account_id){
        User user = authRepository.findUserById(user_id);
        if(user == null)throw new ApiException("User not found");
        if(!user.getRole().equalsIgnoreCase("ADMIN") && !user.getRole().equalsIgnoreCase("EMPLOYEE"))
            throw new ApiException("Not Authorized!");

        Account account = accountRepository.findAccountById(account_id);
        if (account == null) throw new ApiException("Account not found");
        account.setIsActive(false);
        accountRepository.save(account);
    }
}
