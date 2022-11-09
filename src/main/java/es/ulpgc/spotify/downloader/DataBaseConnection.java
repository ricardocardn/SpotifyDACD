package es.ulpgc.spotify.downloader;

import es.ulpgc.spotify.model.Album;
import es.ulpgc.spotify.model.Artist;
import es.ulpgc.spotify.model.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {
    private String dbPath;
    private Connection conn;

    /*
    * Class Constructor
    * @param dbPath: String with the local url of the database
    */
    public DataBaseConnection(String dbPath) {
        this.dbPath = dbPath;
        this.conn = null;
    }

    /*
    * Method that establishes a connection with the database
    * given as class params
    */
    public boolean connect() {
        String dbPath = "jdbc:sqlite:" + this.dbPath;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbPath);
            System.out.println("DataBase connection has been properly established");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (conn != null) {
                    this.conn = conn;
                    return true;
                }

            } catch (Exception ex) {
                System.out.printf(ex.getMessage());
            }
        }

        return false;
    }

    /*
     * Method that closed the opened connection with the database
     * given as class params
     */
    public boolean disconnect() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("DataBase connection closed");
                return true;

            } catch (Exception ex) {
                System.out.printf(ex.getMessage());
            }
        }

        return false;
    }

    /*
    * Method that creates a database in local src
    * @param fileName: name of the database*/
    public boolean createDB(String fileName) {
        if (conn == null) this.connect();
        String dbPath = "jdbc:sqlite:/Users/ricardocardenes/Desktop/java-projects/Spotify_first/src/" + fileName;
        try (Connection conn = DriverManager.getConnection(dbPath)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name ir " + meta.getDriverName());
                System.out.println(String.format("The data base %s has been created", fileName));

                this.conn = conn;
                this.dbPath = "/Users/ricardocardenes/Desktop/java-projects/Spotify_first/src/" + fileName;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        return true;
    }

    public boolean createArtistTable() {
        String dbPath = "jdbc:sqlite:" + this.dbPath;
        String sql = "CREATE TABLE IF NOT EXISTS artists (\n"
                + "ArtistId text PRIMARY KEY,\n"
                + "ArtistName text NOT NULL,\n"
                + "ArtistGenre text NOT NULL,\n"
                + "ArtistPopularity integer NOT NULL\n"
                + ");";

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.execute("DROP TABLE IF EXISTS artists");
                stmt.execute(sql);
                System.out.println("ARTISTS TABLE CREATED");

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }

        return true;
    }

    public boolean createAlbumTable() {
        String dbPath = "jdbc:sqlite:" + this.dbPath;
        String sql = "CREATE TABLE IF NOT EXISTS albums (\n"
                + "AlbumId text PRIMARY KEY,\n"
                + "AlbumName text NOT NULL,\n"
                + "AlbumType text NOT NULL,\n"
                + "Artists text NOT NULL,\n"
                + "TotalTracks integer,\n"
                + "ArtistId text NOT NULL\n"
                //+ "FOREIGN KEY(ArtistId) REFERENCES artists(ArtistId)\n"
                + ");";

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.execute("DROP TABLE IF EXISTS albums");
                stmt.execute(sql);
                System.out.println("ALBUM TABLE CREATED");

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }

        return true;
    }

    public boolean createTrackTable() {
        String dbPath = "jdbc:sqlite:" + this.dbPath;
        String sql = "CREATE TABLE IF NOT EXISTS tracks (\n"
                + "TrackId text PRIMARY KEY,\n"
                + "TrackName text NOT NULL,\n"
                //+ "FOREIGN KEY(AlbumId) REFERENCES albums(AlbumId),\n"
                + "AlbumId text NOT NULL,\n"
                + "TrackExplicit boolean NOT NULL,\n"
                + "TrackArtists text NOT NULL,\n"
                + "TrackDuration integer\n"
                + ");";

        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.execute("DROP TABLE IF EXISTS tracks");
                stmt.execute(sql);
                System.out.println("TRACKS TABLE CREATED");

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }

        return true;
    }

    public boolean insertIntoArtists(Artist artist) {
        String sql = "INSERT INTO artists(ArtistId,ArtistName,ArtistGenre,ArtistPopularity) VALUES(?,?,?,?)";
        try {
            if (conn == null) connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, artist.getId());
            pstmt.setString(2, artist.getName());
            pstmt.setString(3, artist.getGenres().toString());
            pstmt.setString(4, Integer.toString(artist.getPopularity()));
            pstmt.executeUpdate();
            //System.out.println("Artist properly inserted");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean insertIntoAlbums(Album album) {
        String sql = "INSERT INTO albums(AlbumId,AlbumName,AlbumType,Artists,TotalTracks,ArtistId) VALUES(?,?,?,?,?,?)";
        try {
            if (conn == null) connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, album.getId());
            pstmt.setString(2, album.getName());
            pstmt.setString(3, album.getType());
            pstmt.setString(4, album.getArtist().toString());
            pstmt.setInt(5, album.getTotalTracks());
            pstmt.setString(6, album.getArtistId());
            pstmt.executeUpdate();
            //System.out.println("Album properly inserted");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public boolean insertIntoTracks(Track track) {

        String sql = "INSERT INTO tracks(TrackId,TrackName,AlbumId,TrackExplicit,TrackArtists,TrackDuration) VALUES(?,?,?,?,?,?)";
        try {
            if (conn == null) connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, track.getId());
            pstmt.setString(2, track.getName());
            pstmt.setString(3, track.getAlbum());
            pstmt.setBoolean(4, track.getExplicit());
            pstmt.setString(5, track.getArtists().toString());
            pstmt.setInt(6, track.getDuration());
            pstmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public List<Album> getArtistAlbums(String id) throws SQLException {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM albums";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String idNew = rs.getString(6);
            if (rs.getString(6).equals(id)) {
                Album album = new Album();
                album.setId(rs.getString(1));
                album.setName(rs.getString(2));
                album.setType(rs.getString(3));
                album.setTotalTracks(rs.getInt(5));
                album.setArtistId(rs.getString(6));

                albums.add(album);
            }
        }

        System.out.println(albums.toString());
        System.out.println("HOLA");
        return albums;
    }

    public String selectAll(String table, String id) {
        String sql = String.format("SELECT * FROM %s", table);

        try {
            if (conn == null) connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            StringBuilder stringBuilder = new StringBuilder();
            String separator = "\n-------------------------------------------------\n";

            System.out.println(rs.getString(1));
            while (rs.next()) {
                System.out.println(rs.getString(1));
                if (rs.getString(1).equals(id)) {
                    if (table.equals("artists") && rs.getString(1).equals(id)) {
                        System.out.println("OK");
                        return String.format(
                                "ArtistId: %s,\nArtistName: %s,\nArtistGenres: %s,\nArtistPopularity: %s",
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4)
                        );
                    }
                }

                if (table.equals("albums")) {
                    if (rs.getString(6).equals(id)) {
                        String album = String.format(
                                "AlbumId: %s,\nAlbumName: %s,\nAlbumType: %s,\nArtists: %s,\nTotalTracks: %d,\nArtistId: %s",
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getInt(5),
                                rs.getString(6)
                        );

                        stringBuilder.append(album);
                        stringBuilder.append(separator);
                    }
                }

                if (table.equals("tracks")) {
                    if (rs.getString(3).equals(id)) {
                        String track = String.format(
                                "TrackId: %s,\nTrackName: %s,\nAlbumId: %s,\nTrackExplicit: %s,\nTrackArtists: %d,\nTrackDuration: %s",
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getBoolean(4),
                                rs.getInt(5),
                                rs.getInt(6)
                        );

                        stringBuilder.append(track);
                        stringBuilder.append(separator);
                    }
                }
            }

            return stringBuilder.toString();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return "";
    }

}
