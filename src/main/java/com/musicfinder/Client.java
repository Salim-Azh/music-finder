package com.musicfinder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.musicfinder.model.Song;
import com.musicfinder.model.User;
import com.musicfinder.service.UserService;
import com.musicfinder.services.fetch.ItunesSongFetcher;
import com.musicfinder.services.fetch.SongFetcher;

public class Client {

    /**
     * 
     * Used to communicate with external APIs in order to fetch songs
     */
    private final SongFetcher songFetcher;

    private UserService userService;

    public Client(UserService userService) {
        if (userService == null) {
            throw new IllegalArgumentException("User service cannot be null");
        }
        this.userService = userService;
        songFetcher = new ItunesSongFetcher();
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(email);
        return matcher.find();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
    
    public String register(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password cannot be null");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Registration failed : Invalid email");
        }
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Registration failed : password has to contain at least 6 characters");
        }
        try {
            userService.register(new User(email, password));
            return "Thanks for joining MusicFinder! You can now login!";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 
     * Retrieve the last fetched songs if any
     * @return a list of the last fetched songs, an empty list if no song has been fetched before
     */
    public List<Song> getFetchedSongs(){
        return songFetcher.getFetchedSongs();
    }

    /**
     * 
     * Search for songs within an external API and display the resulting data
     * @param term the term used for the research
     */
    public void search(String term){
        songFetcher.search(term);
        List<Song> fetchedSongs = getFetchedSongs();
        if(fetchedSongs.size() > 0){
            System.out.println("Results:\n");
            for (Song song : fetchedSongs) {
                System.out.println(song);
            }
            System.out.println();
        } else {
            System.out.println("Couldn't find \"" + term + "\"...");
        }
    }

    public void login(String email, String password){
        //TODO: Implement
    }
}
