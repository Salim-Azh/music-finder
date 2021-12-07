package com.musicfinder;

import java.util.List;

import com.musicfinder.model.Song;
import com.musicfinder.services.fetch.ItunesSongFetcher;
import com.musicfinder.services.fetch.SongFetcher;

public class Client {

    private SongFetcher songFetcher;
    
    public Client() {
        songFetcher = new ItunesSongFetcher();
    }

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

    public List<Song> getFetchedSongs(){
        return songFetcher.getFetchedSongs();
    }

    
}
