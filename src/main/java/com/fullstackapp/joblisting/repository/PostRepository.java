package com.fullstackapp.joblisting.repository;

import com.fullstackapp.joblisting.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
