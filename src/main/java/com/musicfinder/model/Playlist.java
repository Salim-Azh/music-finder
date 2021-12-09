package com.musicfinder.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private List<Song> playlist;

    
    public Playlist() {
        this.playlist = new ArrayList<>();
    }

    public Playlist(List<Song> playlist) {
        this.playlist = playlist;
    }

    public List<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Song> playlist) {
        this.playlist = playlist;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((playlist == null) ? 0 : playlist.hashCode());
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
        Playlist other = (Playlist) obj;
        if (playlist == null) {
            if (other.playlist != null)
                return false;
        } else if (!playlist.equals(other.playlist))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Your Playlist : [playlist=\n\t" + playlist + "\n]";
    }

    public void add(Song song) {
        if (song != null) {
            playlist.add(song);
        }
    }

    public boolean isEmpty() {
        return playlist.isEmpty();
    }
    
}
