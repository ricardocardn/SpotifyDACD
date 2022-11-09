package es.ulpgc.spotify.controller;

import es.ulpgc.spotify.model.Artist;
import es.ulpgc.spotify.model.Track;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class TrackDeserializer {
    public TrackDeserializer() {}

    public List<Track> albumTracksJsonDeserializer(String json, String album) {
        List<Track> tracks = new ArrayList<>();
        System.out.println(json);

        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonArray items = jsonObject.get("items").getAsJsonArray();

        for (JsonElement item : items) {
            tracks.add(trackJsonDeserializer(item, album));
        }

        System.out.println(tracks.toString());

        return tracks;
    }

    private Track trackJsonDeserializer(JsonElement json, String album) {
        Track track = new Track();

        track.setId(json.getAsJsonObject().get("id").getAsString());
        track.setName(json.getAsJsonObject().get("name").getAsString());

        List<Artist> artists = new ArrayList<>();
        JsonArray artistsJson = json.getAsJsonObject().get("artists").getAsJsonArray();

        for (JsonElement art : artistsJson) {
            Artist artist = new Artist();
            artist.setId(art.getAsJsonObject().get("id").getAsString());
            artist.setName(art.getAsJsonObject().get("name").getAsString());

            artists.add(artist);
        }

        track.setArtists(artists);
        track.setDuration(json.getAsJsonObject().get("duration_ms").getAsInt());
        track.setExplicit(json.getAsJsonObject().get("explicit").getAsBoolean());
        track.setAlbum(album);
        return track;
    }
}
