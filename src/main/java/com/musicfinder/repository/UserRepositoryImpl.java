package com.musicfinder.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.push;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.musicfinder.model.Playlist;
import com.musicfinder.model.Song;
import com.musicfinder.model.User;
import com.musicfinder.persistence.MongoDBClientConnection;

import org.bson.Document;
import org.bson.types.ObjectId;

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
        Document doc = new Document(EMAIL, user.getEmail())
            .append("password", user.getPassword())
            .append("playlist", new ArrayList<DBObject>());

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

        final Class<? extends List> docClass = new ArrayList<Document>().getClass();

        Document userDoc = usersCollection.find(eq(EMAIL, email)).first();

        User optValue;
        if (userDoc != null) {
           
            optValue = new User(
                userDoc.getObjectId("_id"),
                userDoc.get(EMAIL).toString(),
                userDoc.get("password").toString()
            );

            List<Document> playlistDoc = userDoc.get("playlist", docClass);

            Playlist pl = new Playlist();
            if (playlistDoc != null) {
                for (Document songDoc : playlistDoc) {
                    Song song = new Song(
                        songDoc.getObjectId("_id"),
                        songDoc.get("trackName").toString(),
                        songDoc.get("artistName").toString(),
                        songDoc.get("genre").toString());
                    pl.add(song);
                }
                optValue.setPlaylist(pl);
            }

            optionalUser = Optional.of(optValue);
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

    @Override
    public Optional<User> addSongToPlaylist(User user, Song song) {
        if (user == null || song == null) {
            throw new IllegalArgumentException("user and song cannot be null");
        }
        MongoCollection<Document> usersCollection = MongoDBClientConnection.getInstance().getUsersCollection();
        Document doc = new Document("_id", new ObjectId())
            .append("trackName", song.gettrackName())
            .append("artistName", song.getArtistName())
            .append("genre", song.getGenre());

        usersCollection.findOneAndUpdate(eq(EMAIL, user.getEmail()), push("playlist", doc));

        return getUserByEmail(user.getEmail());
    }

}
