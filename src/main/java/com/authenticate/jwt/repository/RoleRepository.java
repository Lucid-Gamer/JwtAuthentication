package com.authenticate.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authenticate.jwt.entity.Role;
import com.authenticate.jwt.entity.RoleEnum;



public interface RoleRepository extends JpaRepository<Role,Integer> {
    
    public Optional<Role> findByRoleName(RoleEnum roleName);

}
