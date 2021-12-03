package com.musicfinder.persistence;

import com.mongodb.MongoException;

public interface IConnection {
    void connection() throws MongoException;
    void close() throws MongoException;
    
}
