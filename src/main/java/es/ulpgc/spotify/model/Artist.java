package es.ulpgc.spotify.model;

public class Artist {
    private String id;
    private String name;
    private int followers;
    private String genres;
    private int popularity;

    public Artist(String id, String name, int followers, String genres, int popularity) {
        this.id = id;
        this.name = name;
        this.followers = followers;
        this.genres = genres;
        this.popularity = popularity;
    }

    public Artist() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFollowers() {
        return followers;
    }

    public String getGenres() {
        return genres;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean set(String atr, Object val) {
        if (atr.equals("id")) this.id = (String) val;
        else if (atr.equals("name")) this.name = (String) val;
        else if (atr.equals("genres")) this.genres = (String) val;
        else if (atr.equals("popularity")) this.popularity = (int) Integer.parseInt((String) val);
        else return false;

        return true;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", followers=" + followers +
                ", genres='" + genres + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}
