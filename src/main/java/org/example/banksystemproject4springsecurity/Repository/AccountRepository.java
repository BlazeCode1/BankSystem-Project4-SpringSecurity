package org.example.banksystemproject4springsecurity.Repository;

import org.example.banksystemproject4springsecurity.Model.Account;
import org.example.banksystemproject4springsecurity.Model.Customer;
import org.example.banksystemproject4springsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountById(Integer id);

    List<Account> getAccountByCustomer_User(User customerUser);

}
