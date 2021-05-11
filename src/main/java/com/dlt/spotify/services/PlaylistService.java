package com.dlt.spotify.services;

import com.dlt.spotify.models.Playlist;
import com.dlt.spotify.repositories.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void savePlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    public List<Playlist> getPlaylists() {
        return playlistRepository.findAll();
    }

}
