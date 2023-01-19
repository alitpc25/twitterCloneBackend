package com.alitpc25.twitterclone.repositories;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.alitpc25.twitterclone.models.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long>  {

	User findByUsername(String username);

	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);

	User findByEmail(String email);
	
	List<User> findByFriendsUsername(String username);
}
