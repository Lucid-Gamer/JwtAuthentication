package com.authenticate.jwt.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authenticate.jwt.entity.Role;
import com.authenticate.jwt.entity.RoleEnum;
import com.authenticate.jwt.entity.User;
import com.authenticate.jwt.payload.RegisterUser;
import com.authenticate.jwt.payload.UserDTO;
import com.authenticate.jwt.repository.RoleRepository;
import com.authenticate.jwt.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final Integer userValidity = 2;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
    @Transactional
    @SuppressWarnings("deprecation")
    public UserDTO registerUser(RegisterUser registerUser) {
       
        User user = new User();
        Date today = new Date();
        Date validUpto = new Date(today.getYear()+userValidity, today.getMonth(), today.getDate());
        try {
            
            user.setFirstName(registerUser.getFirstName());
            user.setLastName(registerUser.getLastName());
            user.setUsername(registerUser.getUsername());
            user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
            user.setCreatedOn(today);
            user.setAccountBlocked(false);
            user.setCredentialLocked(false);
            user.setIsActive(true);
            user.setValidUpto(validUpto);
            
            Set<Role> roles = new HashSet<>();

            for(String roleName: registerUser.getRole()) {
                Role role = new Role();
                role = roleRepository.findByRoleName(getRoleEnum(roleName)).orElseThrow(() -> new ResourceNotFoundException("Role Not found with role name: "+roleName));
                roles.add(role);
            }

            user.setRoles(roles);
            user = userRepository.save(user);
            return UserDTO.builder()
                    .username(user.getUsername())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .role(registerUser.getRole())
                    .build();

        } catch (Exception e) {
            logger.error("An error occurred::::::::::::::::::::::::", e);
        }
        return null;
    }

    public RoleEnum getRoleEnum(String role) {
        
        switch (role) {
            case "admin":
                return RoleEnum.ROLE_ADMIN;
            case "user": 
                return RoleEnum.ROLE_USER;
            case "moderator":
                return RoleEnum.ROLE_MODERATOR;
            default:
                return RoleEnum.ROLE_USER;
        }
    }
}
