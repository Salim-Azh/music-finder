package com.musicfinder.services.fetch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musicfinder.model.Song;

public class ItunesSongFetcher implements SongFetcher {
    public static void main(String[] args) {
        ItunesSongFetcher isf = new ItunesSongFetcher();
        isf.search("");
    }

    @Override
    public ArrayList<Song> search(String term) {
        HttpURLConnection connection = null;
        
        try{
        String url = "https://itunes.apple.com/search?term=\"Troye Sivan\"&limit=10&media=music";
        URL obj = new URL("https://itunes.apple.com/search?term=Troye+Sivan&limit=10&media=music");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
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
            
            //System.out.println(response.toString());
            for (Song song : songArray) {
                System.out.println(song);
            }
        } else {
            System.out.println("GET request did not work");
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    return null;
    
}
}