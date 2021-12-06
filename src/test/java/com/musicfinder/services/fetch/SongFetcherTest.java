package com.musicfinder.services.fetch;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SongFetcherTest {
    @Before
    void setUp(){
        SongFetcher sf = new SongFetcher();
    }

    @Test
    //@DisplayName("Ensure handling of no parameter or empty string")
    void testSearchWithoutParam(){
        assertEquals(null, sf.search());
        assertEquals(null, sf.search(""));
    }

    @Test
    //@DisplayName("Ensure simple search is working")
    void testSearch(){
        Song test = new Song();
        assertEquals(test, sf.search("test"));
    }
}
