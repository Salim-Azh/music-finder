package com.musicfinder.services.fetch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musicfinder.model.Song;

public class ItunesSongFetcher implements SongFetcher {

    /**
     * Store the last fetched songs.
     */
    private List<Song> fetchedSongs;

    public ItunesSongFetcher() {}

    public List<Song> getFetchedSongs() {
        return fetchedSongs;
    }

    public void setFetchedSongs(List<Song> fetchedSongs) {
        this.fetchedSongs = fetchedSongs;
    }

    /**
     * 
     * Search within the ITunes API.
     * 
     */
    @Override
    public List<Song> search(String term) {
        HttpURLConnection connection = null;
        List<Song> fetchedSongs = new ArrayList<Song>();

        term = term.replace(" ", "+"); //Case of mutliple words search

        try{
        URL url = new URL("https://itunes.apple.com/search?limit=10&media=music&term=" + term);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();

            String jsonString = response.toString();
            JsonObject jsonObj = new JsonParser().parse(jsonString).getAsJsonObject();
            Song[] songArray = gson.fromJson(jsonObj.get("results"), Song[].class);

            fetchedSongs = new ArrayList<>(Arrays.asList(songArray));
            
        } else {
            throw new Exception("Could not fetch songs.");
        }
    }catch(Exception e){
        e.printStackTrace();
    }finally {
        connection.disconnect();
    }

    setFetchedSongs(fetchedSongs);
    return fetchedSongs;
    
    }
}