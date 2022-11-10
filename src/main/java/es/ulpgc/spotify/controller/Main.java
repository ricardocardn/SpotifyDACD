package es.ulpgc.spotify.controller;

import es.ulpgc.spotify.controller.downloader.DataBaseConnection;
import es.ulpgc.spotify.controller.downloader.SpotifyAccessor;
import es.ulpgc.spotify.view.MainScreen;

import java.util.*;

public class Main extends Thread {

    public static Map<String, String> artistId;
    public static int cont = 5;
    public static DataCollector dataCollector;

    public static DataBaseConnection dataBaseConnection = new DataBaseConnection("C:\\Users\\carde\\Desktop\\Spotify_first\\src\\spotify.db");

    public static void main(String[] args) throws Exception {
        artistId = new HashMap<>();

        artistId.put("Mora", "0Q8NcsJwoCbZOHHW63su5S");
        artistId.put("Quevedo", "52iwsT98xCoGgiGntTiR7K");
        artistId.put("James Arthur", "4IWBUUAFIplrNtaOHcJPRM");
        artistId.put("Ed Sheeran", "6eUKZXaKkcviH0Ku9w2n3V");
        artistId.put("Feid", "2LRoIwlKmHjgvigdNGBHNo");

        dataBaseConnection.connect();

        dataCollector = new DataCollector(new SpotifyAccessor());
        //dataCollector.collectData();

        System.out.println(Main.dataBaseConnection.getArtistAlbums(Main.artistId.get("Mora")).toString());

        MainScreen ms = new MainScreen();
        ms.setVisible(true);
        ms.setResizable(false);
    }

    public static void addArtist(String name, String id) {
        artistId.put(name, id);
        try {
            dataCollector.collectArtistData(name);
        } catch (Exception e) {}
    }
}