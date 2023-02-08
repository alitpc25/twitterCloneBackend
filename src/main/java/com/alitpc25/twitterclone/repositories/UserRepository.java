package com.alitpc25.twitterclone.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alitpc25.twitterclone.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>  {

	User findByUsername(String username);

	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);

	User findByEmail(String email);
	
	List<User> findByFollowingsUsername(String username);

	Page<User> findByUsernameContainingIgnoreCase(PageRequest of, String username);
}
