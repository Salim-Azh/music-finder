package com.musicfinder.services.fetch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        itf.setFetchedSongs(null);
        itf.search("");
        assertEquals(0, itf.getFetchedSongs().size());
    }

    @Test
    void testSearch(){
        itf.setFetchedSongs(null);
        itf.search("Easy");
        assertNotEquals(0, itf.getFetchedSongs().size());
    }
}
