/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qldvxk;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
/**
 *
 * @author LOI
 */
public class UserManager {
    private Connect connect;

    public UserManager() {
        connect = new Connect();
    }

    public boolean authenticate(String username, String password) {
        MongoCollection<Document> collection = connect.getCollection("Admin");
        Document query = new Document("username", username).append("password", password);
        MongoCursor<Document> cursor = collection.find(query).iterator();
        boolean authenticated = cursor.hasNext();
        cursor.close();
        return authenticated;
    }

    public boolean register(String username, String password) {
        MongoCollection<Document> collection = connect.getCollection("Admin");
        Document existingUser = collection.find(new Document("username", username)).first();

        if (existingUser != null) {
            return false; // User already exists
        }

        Document newUser = new Document("username", username).append("password", password);
        collection.insertOne(newUser);
        return true; // Registration successful
    }
    public void close() {
        connect.close();
    }
}
