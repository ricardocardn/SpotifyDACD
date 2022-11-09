package es.ulpgc.spotify.controller;

import es.ulpgc.spotify.model.Artist;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtistDeserialiezer {
    public ArtistDeserialiezer() {}

    public Artist deserialize(String artist) {

        Artist artistFinal = new Artist();

        // GETTING ARTIST INFORMATION
        Pattern pattern = Pattern.compile("\"name\" : \"([A-Za-z\\s]*)\"");
        Pattern idPattern = Pattern.compile("\"id\" : \"([A-Za-z0-9]*)\"");
        Pattern genresPattern = Pattern.compile("\"genres\" : \\[(.*)\\s]");
        Pattern popPattern = Pattern.compile("\"popularity\" : (\\d*)");

        Map<String, Pattern> patterns = new HashMap<>();
        patterns.put("id", idPattern);
        patterns.put("name", pattern);
        patterns.put("genres", genresPattern);
        patterns.put("popularity", popPattern);

        for (String p: patterns.keySet()) {
            Matcher mat = patterns.get(p).matcher(artist);
            if (mat.find()) System.out.println("");
            artistFinal.set(p, mat.group(1));
        }

        return artistFinal;
    }
}
