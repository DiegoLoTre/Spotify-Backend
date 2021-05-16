package com.dlt.spotify.services.spotifyAPI;

import com.dlt.spotify.repositories.spotifyAPI.TokenRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpotifyPlaylistService extends SpotifyService {

    public SpotifyPlaylistService(TokenRepository tokenRepository) {
        super(tokenRepository);
    }

    public void addTrackToPlaylist(String playlistId, String uri) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(createHeaders(getAccessToken()));

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("playlist_id", playlistId);

        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("uris", uri);

        new RestTemplate().exchange(
                buildURL(
                        "https://api.spotify.com/v1/playlists/{playlist_id}/tracks",
                        urlParams,
                        queryParam
                ),
                HttpMethod.POST, request, Object.class
        );

    }
}
