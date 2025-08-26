package org.example.banksystemproject4springsecurity.Repository;

import org.example.banksystemproject4springsecurity.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
