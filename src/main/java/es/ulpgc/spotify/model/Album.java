package es.ulpgc.spotify.model;

import java.util.List;

public class Album {
    private String id;
    private String name;
    private String type;
    private List<Artist> artists;
    private String artistId;
    private String releaseDate;
    private int totalTracks;

    public Album(String id, String name, String type, List<Artist> artists, int totalTracks) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.artists = artists;
        this.totalTracks = totalTracks;
    }

    public Album() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Artist> getArtist() {
        return artists;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getTotalTracks() {
        return totalTracks;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setArtist(List<Artist> artists) {
        this.artists = artists;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setTotalTracks(int totalTracks) {
        this.totalTracks = totalTracks;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                //", artists=" + artists.toString() +
                ", releaseDate='" + releaseDate + '\'' +
                ", totalTracks=" + totalTracks +
                '}';
    }
}
