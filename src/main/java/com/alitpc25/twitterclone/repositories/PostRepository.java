package com.alitpc25.twitterclone.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.alitpc25.twitterclone.models.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findAllByUserId(String userId);

	@Query("{user: { $in: ?0 } }")
	List<Post> findAllByUserIdOrderByCreatedDate(List<ObjectId> userIds);

}
