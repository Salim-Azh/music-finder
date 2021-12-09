package com.musicfinder.model;

import java.util.List;

import org.bson.types.ObjectId;

public class User {

    private ObjectId id;
    private String email;
    private String password;
    private List<Song> playlist;

    public User(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password cannot be null");
        }
        this.email = email;
        this.password = password;
    }

    public User(ObjectId id, String email, String password) {
        if (email == null || password == null || id == null) {
            throw new IllegalArgumentException("Id, email or password cannot be null");
        }
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(ObjectId id, String email, String password, List<Song> playlist) {
        if (email == null || password == null || id == null) {
            throw new IllegalArgumentException("Id, email or password cannot be null");
        }
        this.id = id;
        this.email = email;
        this.password = password;
        this.playlist = playlist;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        this.password = password;
    }

    public List<Song> getPlaylist() {
        return playlist;
    }
    public void setPlaylist(List<Song> playlList) {
        this.playlist = playlList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }
}
