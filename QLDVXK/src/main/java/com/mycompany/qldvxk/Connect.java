package com.mycompany.qldvxk;

import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Connect {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public Connect() {
        mongoClient = MongoClients.create("mongodb://localhost:27017"); // Thay đổi URL nếu cần
        database = mongoClient.getDatabase("QLDV"); // Thay đổi tên database
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }

    public void close() {
        mongoClient.close();
    }
}
