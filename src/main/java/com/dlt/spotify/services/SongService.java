package com.dlt.spotify.services;

import com.dlt.spotify.models.Song;
import com.dlt.spotify.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void saveSong(Song song) {
        songRepository.save(song);
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

}
