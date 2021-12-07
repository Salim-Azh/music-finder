package com.musicfinder.services.fetch;

import static org.junit.Assert.assertEquals;

import com.musicfinder.model.Song;

import org.junit.Before;
import org.junit.Test;

public class ItunesFetcherTest {

    ItunesSongFetcher itf;

    @Before
    void setUp(){
        itf = new ItunesSongFetcher();
    }

    @Test
    void testSearchWithoutParam(){
        assertEquals(0, itf.search("").size());
    }

    @Test
    void testSearch(){
        Song test = new Song();
        assertEquals(test, itf.search("test"));
    }
}
