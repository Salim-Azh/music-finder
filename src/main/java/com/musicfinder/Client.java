package com.musicfinder;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.musicfinder.model.Playlist;
import com.musicfinder.model.Song;
import com.musicfinder.model.User;
import com.musicfinder.repository.UserRepositoryImpl;
import com.musicfinder.service.ItunesSongFetcher;
import com.musicfinder.service.PlaylistService;
import com.musicfinder.service.SongFetcher;
import com.musicfinder.service.UserService;

public class Client {

    /**
     * 
     * Used to communicate with external APIs in order to fetch songs
     */
    private final SongFetcher songFetcher;

    private User connectedUser;

    private UserService userService;

    private PlaylistService playlistService;

    public Client(){
        userService = new UserService(new UserRepositoryImpl());
        songFetcher = new ItunesSongFetcher();
    }

    public Client(UserService userService, PlaylistService playlistService) {
        if (userService == null) {
            throw new IllegalArgumentException("User service cannot be null");
        }
        if (playlistService == null) {
            throw new IllegalArgumentException("Playlist service cannot be null");
        }
        this.userService = userService;
        this.playlistService = playlistService;
        songFetcher = new ItunesSongFetcher();
    }

    public User getConnectedUser(){
        return connectedUser;
    }

    public void setConnectedUser(User user){
        connectedUser = user;
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
        if(!fetchedSongs.isEmpty()){
            System.out.println("Results:\n");
            for (Song song : fetchedSongs) {
                System.out.println(song);
            }
            System.out.println();
        } else {
            System.out.println("Couldn't find \"" + term + "\"...");
        }
    }

    public String login(String email, String password){
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password cannot be null");
        }

        try{
            User connected = userService.login(email, password);
            setConnectedUser(connected);
            return "Successfully logged in!";
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public void run(){
        Scanner sc = new Scanner(System.in);
        while(true){
            while(connectedUser == null){
                //User has to log in or register

                System.out.println("\n\nWelcome to MusicFinder!\n"
                    +"To log into the system, enter <login email password>\n"
                    +"To register, enter <register email password>\n\n");
                String choice = sc.nextLine();
                String[] splitChoice = choice.split(" ");
                
                System.out.println("\n\n");
                if(splitChoice.length == 3){
                    if(splitChoice[0].equals("login")){
                        System.out.println(login(splitChoice[1], splitChoice[2]));
                    } else if(splitChoice[0].equals("register")){
                        System.out.println(register(splitChoice[1], splitChoice[2]));
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
            }

            System.out.println("Welcome, " + connectedUser.getEmail() + "!\n");
            System.out.println(
                "Enter <search followed by the name of a song> or its singer to search for songs."
                +"Once you've made a research, enter <add followed by the index of a song> to add it to your playlist."
                +"Enter <playlist> to see your playlist."
                );
            String[] choice = sc.nextLine().split(" ");
            if(choice.length > 0){
                if(choice[0].equals("search")){
                    search(choice[1]);
                } else if(choice[0].equals("add")){
                    saveSong(Integer.valueOf(choice[1]));
                } else if(choice[0].equals("playlist")){
                    
                } else {
                    System.out.println("Enter a valid instruction please.");
                }
            }
            
        }
    }

    public String displayPlaylist(){
        if(connectedUser == null){
            return "Error, unauthorized." ;
        } else if (connectedUser.getPlaylist() == null || isConnectedUserPlaylistEmpty()){
            return "Your playlist is empty...";
        } else {
            return "Here's your playlist: " + connectedUser.getPlaylist().toString();
        }
    }

    public String saveSong(Integer index) {
        if (index == null || index < 0 || (index >= getFetchedSongs().size() && !getFetchedSongs().isEmpty() )) {
            throw new IllegalArgumentException("No such song exist the index shoud be between 0 and 9");
        }
        if (getFetchedSongs().isEmpty()) {
            throw new IllegalArgumentException("No results from search you must search for a song before saving it to playlist");
        }
        User user;
        try {
            user = playlistService.saveSong(connectedUser, getFetchedSongs().get(index));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        setConnectedUser(user);
        return "Song saved to playlist";
    }

    public boolean isConnectedUserPlaylistEmpty() {
        if(connectedUser != null){
            return connectedUser.getPlaylist().isEmpty();
        }
        return true;
    }
}
