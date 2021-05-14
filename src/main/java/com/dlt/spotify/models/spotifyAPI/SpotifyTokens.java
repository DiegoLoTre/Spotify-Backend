package com.dlt.spotify.models.spotifyAPI;

import lombok.Getter;
import lombok.Setter;

public class SpotifyTokens {

    @Getter
    @Setter
    private String access_token;

    @Getter
    @Setter
    private int expires_in;
}
