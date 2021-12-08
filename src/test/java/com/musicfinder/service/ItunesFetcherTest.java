package com.musicfinder.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class ItunesFetcherTest {

    private ItunesSongFetcher itf;

    @Before
    public void setUp(){
        itf = new ItunesSongFetcher();
    }

    @Test
    public void testSearchWithoutParam(){
        itf.setFetchedSongs(null);
        itf.search("");
        assertEquals(0, itf.getFetchedSongs().size());
    }

    @Test
    public void testSearch(){
        itf.setFetchedSongs(null);
        itf.search("Easy");
        assertNotEquals(0, itf.getFetchedSongs().size());
    }
}
