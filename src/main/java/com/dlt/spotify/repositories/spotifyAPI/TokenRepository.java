package com.dlt.spotify.repositories.spotifyAPI;

import com.dlt.spotify.models.spotifyAPI.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
}
