package com.musicfinder;

import com.mongodb.MongoClient;
import com.musicfinder.persistence.ConnectionMongoDB;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ConnectionMongoDB.getInstance().connection();
        MongoClient mc = ConnectionMongoDB.getInstance().getClient();
        System.out.println(mc.getCredential());
    }
}
