package com.musicfinder.services.fetch;

import java.util.ArrayList;

import com.musicfinder.model.Song;

public interface SongFetcher {
    ArrayList<Song> search(String term);
}
