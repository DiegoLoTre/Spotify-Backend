package com.dlt.spotify.services.spotifyAPI;

import com.dlt.spotify.models.spotifyAPI.Token;
import com.dlt.spotify.repositories.spotifyAPI.TokenRepository;

import java.sql.Timestamp;
import java.util.List;

public class SpotifyService extends TokenHandler {

    private final TokenRepository tokenRepository;

    public SpotifyService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
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
