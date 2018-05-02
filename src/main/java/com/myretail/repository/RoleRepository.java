package com.myretail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myretail.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

}
