package com.musicfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class ClientTest {
 
    Client client;

    @Before
    public void setUp(){
        client = new Client();
    }

    @Test
    public void testSearchWithoutParam(){
        client.search("");
        assertEquals(0, client.getFetchedSongs().size());
    }

    @Test
    public void testSearch(){
        client.search("Easy");
        assertNotEquals(0, client.getFetchedSongs().size());
    }
}
