package com.dlt.spotify.services.spotifyAPI;

import com.dlt.spotify.models.spotifyAPI.SpotifyTokens;
import com.dlt.spotify.models.spotifyAPI.Token;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TokenHandler {

    private MultiValueMap<String, String> createBody(String refresh_token) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refresh_token);
        map.add("redirect_uri", "http://localhost");

        return map;
    }

    private HttpHeaders createHeaders(String plainCreds) {
        HttpHeaders httpHeaders = new HttpHeaders();
        byte[] encodedAuth = Base64.encodeBase64(plainCreds.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        httpHeaders.set("Authorization", authHeader);

        return httpHeaders;
    }

    public Token updateAccessToken(Token token) {
        String url = "https://accounts.spotify.com/api/token";
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(
                createBody(token.getRefresh_token()),
                createHeaders(token.getHeaderContent())
        );

        ResponseEntity<SpotifyTokens> response = restTemplate.postForEntity(url, request, SpotifyTokens.class);

        token.setAccess_token(Objects.requireNonNull(response.getBody()).getAccess_token());
        token.setExpire_at(response.getBody().getExpires_in());
        return token;
    }
}
