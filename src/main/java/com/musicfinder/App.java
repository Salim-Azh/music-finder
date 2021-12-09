package com.musicfinder;

import com.musicfinder.model.Song;
import com.musicfinder.model.User;
import com.musicfinder.repository.UserRepositoryImpl;

import org.bson.types.ObjectId;

public class App {
    public static void main( String[] args ) {
        Client client = new Client();
        client.run();
    }
}
