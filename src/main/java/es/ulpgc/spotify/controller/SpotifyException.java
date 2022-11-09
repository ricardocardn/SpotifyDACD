package es.ulpgc.spotify.controller;

public class SpotifyException extends Exception {
    public SpotifyException() {
    }

    public SpotifyException(String message) {
        super(message);
    }

    public SpotifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpotifyException(Throwable cause) {
        super(cause);
    }

    public SpotifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "SpotifyException{}";
    }


}
