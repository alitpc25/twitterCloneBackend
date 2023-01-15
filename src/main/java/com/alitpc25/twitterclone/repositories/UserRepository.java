package com.alitpc25.twitterclone.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.alitpc25.twitterclone.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>  {
}
