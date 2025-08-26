package org.example.banksystemproject4springsecurity.Repository;

import org.example.banksystemproject4springsecurity.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {
    User findUsersByUsername(String username);

    User findUserById(Integer id);

    User findUsersById(Integer id);
}
