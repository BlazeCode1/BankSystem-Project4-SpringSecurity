package org.example.banksystemproject4springsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    private Integer id;


    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private Double salary;


    @OneToOne @MapsId @JsonIgnore private User user;

}
