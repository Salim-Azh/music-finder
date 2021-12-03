package com.musicfinder.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public class ConnectionMongoDB implements IConnection {

    private static ConnectionMongoDB connectionMongoDB;
    private MongoClient mongoClient;

    private ConnectionMongoDB(){
        if(connectionMongoDB == null){
            connectionMongoDB = this;   
        }
    }

    public static ConnectionMongoDB getInstance(){
        if(connectionMongoDB == null){
            connectionMongoDB = new ConnectionMongoDB();
        }
        return connectionMongoDB;
    }

    public MongoClient getClient(){
        return mongoClient;
    }

    @Override
    public void connection() throws MongoException {
        this.mongoClient = null;
        try{
            MongoClientURI uri = new MongoClientURI("mongodb://musicfinderapp:e6sFli96OtgYXljg@musicfindercluster1.3s6ps.mongodb.net/?retryWrites=true&w=majority");
            this.mongoClient = new MongoClient(uri);
            System.out.println("Successfully connected to the database!");
        } catch (Exception e){
            System.out.println("An error occured while trying to connect to the database.");
        }
    }

    @Override
    public void close() throws MongoException {
        if(this.mongoClient != null){
            this.mongoClient.close();
        }
    }
    
}
