package es.ulpgc.spotify.controller;

import es.ulpgc.spotify.model.Album;
import es.ulpgc.spotify.model.Artist;
import com.google.gson.*;

import java.util.*;

public class AlbumDeserializer {
    public AlbumDeserializer() {}

    public List<Album> albumJsonDeserializer(String json, String id) {
        JsonObject album = new Gson().fromJson(json, JsonObject.class);
        JsonArray items = album.get("items").getAsJsonArray();

        List<Album> albums = new ArrayList<>();

        for (JsonElement item : items) {
            Album alb = new Album();
            alb.setName(item.getAsJsonObject().get("name").getAsString());

            JsonArray artists = item.getAsJsonObject().get("artists").getAsJsonArray();
            List<Artist> albumArtists = new ArrayList<>();

            for (int i = 0; i < artists.size(); i++) {
                Artist artist = new Artist();
                //System.out.println(artists.get(i));
                artist.setId(artists.get(i).getAsJsonObject().get("id").getAsString());
                artist.setName(artists.get(i).getAsJsonObject().get("name").getAsString());

                albumArtists.add(artist);
            }

            alb.setArtist(albumArtists);
            alb.setType(item.getAsJsonObject().get("album_type").getAsString());
            alb.setId(item.getAsJsonObject().get("id").getAsString());
            alb.setReleaseDate(item.getAsJsonObject().get("release_date").getAsString());
            alb.setTotalTracks(item.getAsJsonObject().get("total_tracks").getAsInt());
            alb.setArtistId(id);

            //System.out.println(alb.toString());

            albums.add(alb);
        }

        return albums;
    }
}
