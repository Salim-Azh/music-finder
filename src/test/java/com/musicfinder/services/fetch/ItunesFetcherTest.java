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
        assertEquals(0, itf.search("").size());
    }

    @Test
    void testSearch(){
        assertNotEquals(0, itf.search("Easy").size());
    }
}
