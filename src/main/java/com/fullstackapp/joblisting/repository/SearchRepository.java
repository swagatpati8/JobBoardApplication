package com.fullstackapp.joblisting.repository;

import com.fullstackapp.joblisting.model.Post;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
