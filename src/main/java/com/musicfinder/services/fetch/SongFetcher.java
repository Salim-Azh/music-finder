package com.musicfinder.services.fetch;

import java.util.List;

import com.musicfinder.model.Song;

public interface SongFetcher {
    List<Song> search(String term);
    List<Song> getFetchedSongs();
}
