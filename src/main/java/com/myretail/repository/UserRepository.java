package com.myretail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myretail.domain.User;


public interface UserRepository extends MongoRepository<User, String> {
	public User findByUsername(String username);
}
