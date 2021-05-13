package com.dlt.spotify.controller.api;

import com.dlt.spotify.models.Song;
import com.dlt.spotify.services.SongService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> createSong(@RequestBody Song song) {
        try {
            if (song.getSpotify_pc() != 0) songService.sort_song(song.getSpotify_pc());

            songService.saveSong(song);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Song>> getSongs() {
        try {
            List<Song> songs = songService.getSongs();
            return new ResponseEntity<>(songs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
