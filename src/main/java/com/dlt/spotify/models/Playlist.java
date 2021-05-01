package com.dlt.spotify.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Playlist extends SpotifyModel {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "playlist_has_songs",
            joinColumns = {@JoinColumn(name = "playlist")},
            inverseJoinColumns = {@JoinColumn(name = "song")}
    )
    private Set<Song> songs = new HashSet<>();
}
