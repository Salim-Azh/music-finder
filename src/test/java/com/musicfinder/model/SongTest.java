package com.musicfinder.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SongTest {

    private Song song;
    private Song emptySong;

    @Before
    public void setUp(){
        emptySong = new Song();
        song = new Song(1, "Easy", "Troye", "Pop");
    }

    @Test
    public void testNotEquals(){
        assertEquals(false, song.equals(emptySong));
    }

    @Test
    public void testEquals(){
        assertEquals(true, song.equals(new Song(1, "Easy", "Troye", "Pop")));
    }
    
}
