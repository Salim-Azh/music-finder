package com.musicfinder.services.fetch;

import java.util.List;

import com.musicfinder.model.Song;

/**
 * 
 * Allows to communicate with an external API in order to fetch data about songs.
 */
public interface SongFetcher {
    /**
     * Search for songs data within an external API
     * @param term term to search with
     * @return a list of matching songs if any. If not, an empty list.
     */
    List<Song> search(String term);

    /**
     * Retrieve the last fetched songs.
     * @return a list of the last fetched songs if any. If not, an empty list.
     */
    List<Song> getFetchedSongs();
}
