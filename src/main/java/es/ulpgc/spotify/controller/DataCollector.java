package es.ulpgc.spotify.controller;

import es.ulpgc.spotify.downloader.SpotifyAccessor;
import es.ulpgc.spotify.model.Album;
import es.ulpgc.spotify.model.Artist;
import es.ulpgc.spotify.model.Track;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataCollector {
    private SpotifyAccessor sp;

    public DataCollector(SpotifyAccessor sp) {
        this.sp = sp;
    }

    public void collectData() throws Exception {
        Main.dataBaseConnection.createArtistTable();
        Main.dataBaseConnection.createAlbumTable();
        Main.dataBaseConnection.createTrackTable();

        ArtistDeserialiezer des = new ArtistDeserialiezer();
        AlbumDeserializer ades = new AlbumDeserializer();
        TrackDeserializer tdes = new TrackDeserializer();

        for (String artistKey : Main.artistId.keySet()) {
            String json = sp.get("/artists/" + Main.artistId.get(artistKey), new HashMap<>());
            Artist artist = des.deserialize(json);

            Main.dataBaseConnection.insertIntoArtists(artist);

            String artistAlbums = sp.get("/artists/" + Main.artistId.get(artistKey) + "/albums", Map.of());
            List<Album> alb = ades.albumJsonDeserializer(artistAlbums, Main.artistId.get(artistKey));

            for (Album album : alb) {
                Main.dataBaseConnection.insertIntoAlbums(album);
                String tracksJson = sp.get("/albums/" + album.getId() + "/tracks", Map.of());

                List<Track> tracks = tdes.albumTracksJsonDeserializer(tracksJson, album.getId());
                for (Track track : tracks) {
                    Main.dataBaseConnection.insertIntoTracks(track);
                }
            }
        }
    }
}
