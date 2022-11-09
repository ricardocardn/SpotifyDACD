package es.ulpgc.spotify.controller;

import es.ulpgc.spotify.controller.AlbumDeserializer;
import es.ulpgc.spotify.controller.ArtistDeserialiezer;
import es.ulpgc.spotify.controller.SpotifyException;
import es.ulpgc.spotify.controller.TrackDeserializer;
import es.ulpgc.spotify.downloader.DataBaseConnection;
import es.ulpgc.spotify.downloader.SpotifyAccessor;
import es.ulpgc.spotify.view.MainScreen;
import es.ulpgc.spotify.model.Album;
import es.ulpgc.spotify.model.Artist;
import es.ulpgc.spotify.model.Track;

import javax.xml.crypto.Data;
import java.util.*;

public class Main {

    public static Map<String, String> artistId = Map.of("Mora", "0Q8NcsJwoCbZOHHW63su5S",
            "Quevedo", "52iwsT98xCoGgiGntTiR7K",
            "James Arthur", "4IWBUUAFIplrNtaOHcJPRM",
            "Morat", "5C4PDR4LnhZTbVnKWXuDKD",
            "Feid", "2LRoIwlKmHjgvigdNGBHNo");
    public static int cont = 5;

    public static DataBaseConnection dataBaseConnection = new DataBaseConnection("/Users/ricardocardenes/Desktop/java-projects/Spotify_first/src/spotify.db");

    public static void main(String[] args) throws Exception {
        dataBaseConnection.connect();
        System.out.println(Main.dataBaseConnection.getArtistAlbums(Main.artistId.get("Mora")).toString());

        MainScreen ms = new MainScreen();
        ms.setVisible(true);
        ms.setResizable(false);

        SpotifyAccessor sp = new SpotifyAccessor();
        DataCollector dataCollector = new DataCollector(sp);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("TimerTask");
                if (artistId.size() != cont) {
                    try {
                        System.out.println("TimerTaskCondition");
                        dataCollector.collectData();
                        cont++;
                    } catch (Exception ex) {}
                }
            }
        };

        timer.schedule(timerTask, 0);
    }
}