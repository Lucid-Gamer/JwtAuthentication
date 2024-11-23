package com.authenticate.jwt.service;

import com.authenticate.jwt.payload.RegisterUser;
import com.authenticate.jwt.payload.UserDTO;

public interface UserService {
    
     public UserDTO registerUser(RegisterUser registerUser) ;

}
