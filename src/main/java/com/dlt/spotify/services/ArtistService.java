package com.dlt.spotify.services;

import com.dlt.spotify.models.Artist;
import com.dlt.spotify.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void saveArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

}
