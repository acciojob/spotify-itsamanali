package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class SpotifyRepository {
    public HashMap<Artist, List<Album>> artistAlbumMap;
    public HashMap<Album, List<Song>> albumSongMap;
    public HashMap<Playlist, List<Song>> playlistSongMap;
    public HashMap<Playlist, List<User>> playlistListenerMap;
    public HashMap<User, Playlist> creatorPlaylistMap;
    public HashMap<User, List<Playlist>> userPlaylistMap;
    public HashMap<Song, List<User>> songLikeMap;

    public List<User> users;
    public List<Song> songs;
    public List<Playlist> playlists;
    public List<Album> albums;
    public List<Artist> artists;

    public SpotifyRepository(){
        //To avoid hitting apis multiple times, initialize all the hashmaps here with some dummy data
        artistAlbumMap = new HashMap<>();
        albumSongMap = new HashMap<>();
        playlistSongMap = new HashMap<>();
        playlistListenerMap = new HashMap<>();
        creatorPlaylistMap = new HashMap<>();
        userPlaylistMap = new HashMap<>();
        songLikeMap = new HashMap<>();

        users = new ArrayList<>();
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        albums = new ArrayList<>();
        artists = new ArrayList<>();
    }

    public User createUser(String name, String mobile) {
       User user =new User(name,mobile);
        users.add(user);
        return user;
    }

    public Artist createArtist(String name) {
        Artist artist = new Artist(name);
        artists.add(artist);
        return artist;
    }

    public Album createAlbum(String title, String artistName) {
        Artist artist =new Artist(artistName);
        if(!artists.contains(artist)){
            artists.add(new Artist(artist);
        }
        Album album = new Album(title);
        albums.add(album);
        List<Album> albums1= new ArrayList<>();
        if(artistAlbumMap.containsKey(artist)){
           albums1 = artistAlbumMap.get(artist);
        }
        albums1.add(album);
        artistAlbumMap.put(artist,albums1);
        return album;
    }

    public Song createSong(String title, String albumName, int length) throws Exception{
        Album album = new Album(albumName);
        if(!albums.contains(album)){
            throw new Exception("Album does not exist");
        }
        Song song = new Song(title,length);
        songs.add(song);
        List<Song> songList = new ArrayList<>();
        if(albumSongMap.containsKey(album)){
            songList=albumSongMap.get(album);
        }
        songList.add(song);
        albumSongMap.put(album,songList);
        return song;

    }

    public Playlist createPlaylistOnLength(String mobile, String title, int length) throws Exception {
       User user = new User();
        boolean run =false;
        for(User user1:users){
            if(user1.getMobile().equals(mobile)){
                run=true;
                user=user1;
            }

        }

        if(run==false) throw new Exception("User does not exist");

        Playlist playlist= new Playlist(title);
        List<Song> songList = new ArrayList<>();
        if(playlistSongMap.containsKey(playlist)){
            songList=playlistSongMap.get(playlist);
        }
        for(Song song:songs){
            if(song.getLength()==length){
                songList.add(song);
            }
        }
        List<User> userList = new ArrayList<>();
        if(playlistListenerMap.containsKey(playlist))
            userList=playlistListenerMap.get(playlist);
        userList.add(user);
        playlistListenerMap.put(playlist,userList);
        playlistSongMap.put(playlist,songList);
        return playlist;
    }

    public Playlist createPlaylistOnName(String mobile, String title, List<String> songTitles) throws Exception {

    }

    public Playlist findPlaylist(String mobile, String playlistTitle) throws Exception {

    }

    public Song likeSong(String mobile, String songTitle) throws Exception {

    }

    public String mostPopularArtist() {
    }

    public String mostPopularSong() {
    }
}
