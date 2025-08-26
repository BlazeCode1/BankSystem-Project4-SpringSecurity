package org.example.banksystemproject4springsecurity.Service;

import lombok.RequiredArgsConstructor;
import org.example.banksystemproject4springsecurity.Api.ApiException;
import org.example.banksystemproject4springsecurity.Model.User;
import org.example.banksystemproject4springsecurity.Repository.AuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = authRepository.findUsersByUsername(username);
         if (user == null)
             throw new ApiException("User Not found");
         return user;
    }
}
