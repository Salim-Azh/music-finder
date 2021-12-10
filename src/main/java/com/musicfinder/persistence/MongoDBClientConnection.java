package com.musicfinder.persistence;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import io.github.cdimascio.dotenv.Dotenv;

public class MongoDBClientConnection implements IConnection {

    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;
    private static MongoDBClientConnection instance;

    private MongoDBClientConnection() {
        Dotenv dotenv = Dotenv.load();
        ConnectionString connectionString = new ConnectionString(dotenv.get("DB_URI"));
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        mongoClient = MongoClients.create(settings);
        mongoDatabase = mongoClient.getDatabase("musicfinder");
    }

    public static MongoDBClientConnection getInstance() {
        if (instance == null) {
            instance = new MongoDBClientConnection();
        }
        return instance;
    }

    public MongoCollection<Document> getUsersCollection() {
        return mongoDatabase.getCollection("users");
    }

    public MongoCollection<Document> getSongsCollection() {
        return mongoDatabase.getCollection("songs");
    }

    @Override
    public void close() throws MongoException {
        if (this.mongoClient != null) {
            this.mongoClient.close();
        }
    }

}
