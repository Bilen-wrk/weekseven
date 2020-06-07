package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
  //onetomany relationship with user and role
     Set<Role> findAllByUsername(String username);
     //Bcuz one user might have multiple roles so we must return a set of the role class
     //select * from Role where username =?
}
