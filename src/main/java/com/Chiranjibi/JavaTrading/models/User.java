package com.Chiranjibi.JavaTrading.models;

import com.Chiranjibi.JavaTrading.domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private String email;

    //when we fetch user from client side password should be ignored
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //password is only writable

    private String password;

    @Embedded
    private TwoFactorAuth twoFactorAuth=new TwoFactorAuth();


    private USER_ROLE role= USER_ROLE.ROLE_CUSTOMER ;//default =customer


}
