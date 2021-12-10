package com.musicfinder.model;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

public class SongTest {

    private Song song;
    private Song emptySong;
    private ObjectId songOid;

    @Before
    public void setUp(){
        emptySong = new Song();
        songOid = new ObjectId();
        song = new Song(songOid, "Easy", "Troye", "Pop");
    }

    @Test
    public void testNotEquals(){
        assertEquals(false, song.equals(emptySong));
    }

    @Test
    public void testEquals(){
        assertEquals(true, song.equals(new Song(songOid, "Easy", "Troye", "Pop")));
    }
    
}
