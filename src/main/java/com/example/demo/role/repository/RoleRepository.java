package com.example.demo.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.example.demo.role.entity.Role;

// @Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByName(String roleName);
}