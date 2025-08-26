package org.example.banksystemproject4springsecurity.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username Is Required!")
    @Size(min = 4,max = 20,message = "Size must be username >= 4 , username < 10")
    @Column(nullable = false,unique = true)
    private String username;

    @NotEmpty(message = "User Password is Required!")
    @Size(min = 6)
    @Column(nullable = false)
    private String password;

    @NotEmpty(message = "Name Is Required!")
    @Size(min = 2,max = 20,message = "Name Length is >= 2 , <=20")
    @Column(nullable = false,length = 20)
    private String name;

    @Email(message = "Invalid User Email")
    @Column(nullable = false,unique = true)
    private String email;


    private String role;


    // Relations
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn private Employee employee;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn private Customer customer;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
