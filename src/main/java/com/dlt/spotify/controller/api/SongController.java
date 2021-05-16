package com.dlt.spotify.controller.api;

import com.dlt.spotify.models.Person;
import com.dlt.spotify.models.Playlist;
import com.dlt.spotify.models.Song;
import com.dlt.spotify.models.spotifyAPI.Track;
import com.dlt.spotify.services.SongService;
import com.dlt.spotify.services.spotifyAPI.SpotifyPlaylistService;
import com.dlt.spotify.services.spotifyAPI.SpotifyTrackService;
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
    private final SpotifyTrackService spotifyTrackService;
    private final SpotifyPlaylistService spotifyPlaylistService;

    public SongController(SongService songService, SpotifyTrackService spotifyTrackService, SpotifyPlaylistService spotifyPlaylistService) {
        this.songService = songService;
        this.spotifyTrackService = spotifyTrackService;
        this.spotifyPlaylistService = spotifyPlaylistService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> createSong(@RequestBody Song song) {
        try {
            if (song.getSpotify_pc() != 0) songService.sort_song(song.getSpotify_pc());

            songService.saveSong(song);

            if (song.isSaved() || song.getPeople().size() > 0 || song.getPlaylists().size() > 0) {

                Track track = new Track(song);

                if (song.getPeople().size() > 0 || song.getPlaylists().size() > 0) {
                    track = spotifyTrackService.getTrackById(track.getId());

                    for (Playlist playlist : song.getPlaylists()) {
                        spotifyPlaylistService.addTrackToPlaylist(playlist.getSpotify_url(), track.getUri());
                    }

                    for (Person person : song.getPeople()) {
                        spotifyPlaylistService.addTrackToPlaylist(person.getSpotify_url(), track.getUri());
                    }
                }

                if (song.isSaved())
                    spotifyTrackService.saveTrack(track.getId());
            }

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
