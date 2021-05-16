package com.dlt.spotify.services.spotifyAPI;

import com.dlt.spotify.models.spotifyAPI.Track;
import com.dlt.spotify.repositories.spotifyAPI.TokenRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpotifyTrackService extends SpotifyService {

    public SpotifyTrackService(TokenRepository tokenRepository) {
        super(tokenRepository);
    }

    public Track getTrackById(String id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(createHeaders(getAccessToken()));

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", id);

        ResponseEntity<Track> response = restTemplate.exchange(
                buildURL(
                        "https://api.spotify.com/v1/tracks/{id}",
                        urlParams,
                        null
                ),
                HttpMethod.GET, request, Track.class
        );

        return response.getBody();
    }

    public void saveTrack(String id) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(createHeaders(getAccessToken()));

        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("ids", id);

        new RestTemplate().exchange(
                buildURL(
                        "https://api.spotify.com/v1/me/tracks",
                        null,
                        queryParam
                ),
                HttpMethod.PUT, request, Object.class
        );
    }
}
