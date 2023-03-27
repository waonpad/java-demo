package com.example.demo.db.migration.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.role.entity.Role;
import com.example.demo.role.repository.RoleRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class DBMigrateService {
  
  @Autowired
  private RoleRepository roleRepository;

  public void migrate() {
    List<Role> roles = new ArrayList<>();

    Arrays.asList("ROLE_USER", "ROLE_ADMIN").forEach(name -> {
      Role targetRole = roleRepository.findByName(name);
      if (targetRole == null) {
          Role role = new Role();
          role.setName(name);
          roles.add(role);
      }
    });
    
    if (roles.size() > 0) {
      roleRepository.saveAll(roles);
    }
  }
}
