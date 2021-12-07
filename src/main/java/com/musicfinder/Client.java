package com.musicfinder;

import java.util.List;

import com.musicfinder.model.Song;
import com.musicfinder.services.fetch.ItunesSongFetcher;
import com.musicfinder.services.fetch.SongFetcher;

public class Client {

    /**
     * 
     * Used to communicate with external APIs in order to fetch songs
     */
    private final SongFetcher songFetcher;
    
    public Client() {
        songFetcher = new ItunesSongFetcher();
    }

    /**
     * 
     * Retrieve the last fetched songs if any
     * @return a list of the last fetched songs, an empty list if no song has been fetched before
     */
    public List<Song> getFetchedSongs(){
        return songFetcher.getFetchedSongs();
    }

    /**
     * 
     * Search for songs within an external API and display the resulting data
     * @param term the term used for the research
     */
    public void search(String term){
        songFetcher.search(term);
        List<Song> fetchedSongs = getFetchedSongs();
        if(fetchedSongs.size() > 0){
            System.out.println("Results:\n");
            for (Song song : fetchedSongs) {
                System.out.println(song);
            }
            System.out.println();
        } else {
            System.out.println("Couldn't find \"" + term + "\"...");
        }
    }

    public void login(String email, String password){
        //TODO: Implement
    }
    
}
