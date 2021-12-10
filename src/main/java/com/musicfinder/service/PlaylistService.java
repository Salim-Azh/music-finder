package com.musicfinder.service;

import com.musicfinder.model.Playlist;
import com.musicfinder.model.Song;
import com.musicfinder.model.User;
import com.musicfinder.repository.UserRepository;

public class PlaylistService {

    private UserRepository userRepository;

    
    public PlaylistService(UserRepository userRepository) {
        if (userRepository == null) {
            throw new IllegalArgumentException("user respository cannot be null");
        }
        this.userRepository = userRepository;
    }


    public User saveSong(User user, Song song) throws Exception {
        if (user == null || song == null) {
           throw new IllegalArgumentException("user and song cannot be null");
        }
        return userRepository.addSongToPlaylist(user, song).orElseThrow(() -> new Exception("An issue occured while saving the the song to playlist"));
    }
    
}
