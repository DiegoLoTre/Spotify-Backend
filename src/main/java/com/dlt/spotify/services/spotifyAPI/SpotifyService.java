package com.dlt.spotify.services.spotifyAPI;

import com.dlt.spotify.models.spotifyAPI.Token;
import com.dlt.spotify.repositories.spotifyAPI.TokenRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class SpotifyService extends TokenHandler {

    private final TokenRepository tokenRepository;

    public SpotifyService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    HttpHeaders createHeaders(String access_token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + access_token);
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return httpHeaders;
    }

    String buildURL(String url, Map<String, String> urlParams, Map<String, String> queryParam) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        if (null != queryParam) {
            for (Map.Entry<String, String> entry : queryParam.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        if (null == urlParams)
            return builder.toUriString();
        else
            return builder.buildAndExpand(urlParams).toUriString();
    }

    String getAccessToken() {
        Token token = getCurrentToken();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        if (token.getExpire_at().before(now)) {
            token = updateAccessToken(token);
            tokenRepository.save(token);
        }

        return token.getAccess_token();
    }

    private Token getCurrentToken() {
        List<Token> tokens = tokenRepository.findAll();
        return tokens.iterator().next();
    }
}
