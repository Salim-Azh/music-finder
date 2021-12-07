package com.musicfinder.model;

public class Song {
    private int id;
    private String trackName;
    private String artistName;
    private String genre;
    
    public Song() {
    }

    public Song(int id, String trackName, String artistName, String genre) {
        this.id = id;
        this.trackName = trackName;
        this.artistName = artistName;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gettrackName() {
        return trackName;
    }

    public void settrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artistName == null) ? 0 : artistName.hashCode());
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + id;
        result = prime * result + ((trackName == null) ? 0 : trackName.hashCode());
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
        Song other = (Song) obj;
        if (artistName == null) {
            if (other.artistName != null)
                return false;
        } else if (!artistName.equals(other.artistName))
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (id != other.id)
            return false;
        if (trackName == null) {
            if (other.trackName != null)
                return false;
        } else if (!trackName.equals(other.trackName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Song [artistName=" + artistName + ", trackName=" + trackName + "]";
    }

    
}
