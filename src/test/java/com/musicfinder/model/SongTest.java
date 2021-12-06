package com.musicfinder.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SongTest {

    Song song;
    Song emptySong;

    @Before
    void setUp(){
        emptySong = new Song();
        song = new Song(1, "Easy", "Troye", "Pop");
    }

    @Test
    void testNotEquals(){
        assertEquals(false, song.equals(emptySong));
    }

    @Test
    void testEquals(){
        assertEquals(true, song.equals(new Song(1, "Easy", "Troye", "Pop")));
    }
    
}
