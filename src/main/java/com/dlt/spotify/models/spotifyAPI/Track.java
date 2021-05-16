package com.dlt.spotify.models.spotifyAPI;

import com.dlt.spotify.models.Song;
import lombok.Getter;
import lombok.Setter;

public class Track {

    @Getter
    @Setter
    private String id, uri;

    public Track() {

    }

    public Track(Song song) {
        this.id = song.getSpotify_url().replace("https://open.spotify.com/track/","");
    }
}
