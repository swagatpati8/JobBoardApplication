package com.fullstackapp.joblisting.repository;

import com.fullstackapp.joblisting.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Repository
@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepository {

    private static final String CLUSTER0 = "Cluster0";
    public static final String JOB_POST = "JobPost";
    public static final String $_SEARCH = "$search";

    private final MongoClient client;
    private final MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase(CLUSTER0);
        MongoCollection<Document> collection = database.getCollection(JOB_POST);
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document($_SEARCH,
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 5L)));
        result.forEach(doc -> posts.add(converter.read(Post.class,doc)));

        return posts;
    }
}
