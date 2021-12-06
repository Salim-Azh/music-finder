package com.musicfinder.services.fetch;

import org.junit.Before;
import org.junit.Test;

public class ItunesFetcherTest {
    @Before
    void setUp(){
        ItunesFetcher itf = new ItunesFetcher();
    }

    @Test
    void testSearchWithoutParam(){
        assertEquals(null, itf.search());
        assertEquals(null, itf.search(""));
    }

    @Test
    void testSearch(){
        Song test = new Song();
        assertEquals(test, itf.search("test"));
    }
}
