package com.musicfinder.persistence;

import com.mongodb.MongoException;

public interface IConnection {
    void close() throws MongoException;
    
}
