package org.example.banksystemproject4springsecurity.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String phone_number;



    //          RELATIONS
    @OneToOne @MapsId @JsonIgnore private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer") private Set<Account> account;


}
