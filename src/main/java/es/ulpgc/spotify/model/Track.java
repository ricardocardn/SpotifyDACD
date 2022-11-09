package es.ulpgc.spotify.model;

import java.util.List;

public class Track {
    private String id;
    private String name;
    private boolean explicit;
    private List<Artist> artists;
    private int duration;
    private String album;

    public Track(String id, String name, boolean explicit, List<Artist> artists, int duration, String album) {
        this.id = id;
        this.name = name;
        this.explicit = explicit;
        this.artists = artists;
        this.duration = duration;
        this.album = album;
    }

    public Track() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getExplicit() {
        return explicit;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public int getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", release_date='" + explicit + '\'' +
                ", artists=" + artists +
                ", duration=" + duration +
                ", album=" + album +
                '}';
    }
}
