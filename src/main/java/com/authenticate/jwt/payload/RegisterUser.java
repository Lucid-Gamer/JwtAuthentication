package com.authenticate.jwt.payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterUser {
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private Set<String> role;
}
