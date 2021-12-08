package com.musicfinder.repository;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.musicfinder.model.User;
import com.musicfinder.persistence.MongoDBClientConnection;

import org.bson.Document;

public class UserRepositoryImpl implements UserRepository {

    private static final String EMAIL = "email";

    @Override
    public Optional<User> save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user cannot be null");
        }
        if (getUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("An account already exists for this email");
        }
        Document doc = new Document(EMAIL, user.getEmail()).append("password", user.getPassword()).append("playlist",
                new Document());

        MongoCollection<Document> usersCollection = MongoDBClientConnection.getInstance().getUsersCollection();
        usersCollection.insertOne(doc);

        return getUserByEmail(user.getEmail());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }
        Optional<User> optionalUser = Optional.empty();

        MongoCollection<Document> usersCollection = MongoDBClientConnection.getInstance().getUsersCollection();

        Document userDoc = usersCollection.find(eq(EMAIL, email)).first();

        if (userDoc != null) {
            optionalUser = Optional.of(new User(userDoc.getObjectId("_id"), userDoc.get(EMAIL).toString(),
                    userDoc.get("password").toString()));
        }

        return optionalUser;
    }

    @Override
    public void deleteAll() {
        MongoCollection<Document> usersCollection = MongoDBClientConnection.getInstance().getUsersCollection();
        // usersCollection.deleteMany(new Document());
        BasicDBObject document = new BasicDBObject();

        // Delete All documents from collection Using blank BasicDBObject
        usersCollection.deleteMany(document);

    }
}
