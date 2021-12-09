package com.musicfinder.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.musicfinder.BaseTestClass;
import com.musicfinder.model.Playlist;
import com.musicfinder.model.Song;
import com.musicfinder.model.User;
import com.musicfinder.repository.UserRepository;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

public class PlaylistServiceTest extends BaseTestClass  {

    PlaylistService playlistService;

    @Mock
    private UserRepository userRepository;

    //Test data
    private User user;
    private final Song song = new Song(new ObjectId(), "toto", "tata", "titi");


    @Before
    public void setup() {
        user = new User(new ObjectId(), "toto@gmail.com", "azeaze");
        playlistService = new PlaylistService(userRepository);
    }
    
    @Test
    public void saveSong() throws Exception {
        List<Song> songs = new ArrayList<>();
        songs.add(song);
        Playlist playlist = new Playlist(songs);
        user.setPlaylist(playlist);

        when(userRepository.addSongToPlaylist(any(User.class), any(Song.class))).thenReturn(Optional.of(user));
        User actual = playlistService.saveSong(user, song);

        verify(userRepository).addSongToPlaylist(any(User.class), any(Song.class));
        assertEquals(user,actual);

    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    
    @Test
    public void saveSongShouldFailWhenNullSong() throws Exception {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("user and song cannot be null");

        playlistService.saveSong(user, null);

        verify(userRepository, times(0)).addSongToPlaylist(any(User.class), any(Song.class));

    }

    @Test
    public void saveSongShouldFailWhenNullUser() throws Exception {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("user and song cannot be null");

        playlistService.saveSong(null, song);

        verify(userRepository, times(0)).addSongToPlaylist(any(User.class), any(Song.class));

    }


}
