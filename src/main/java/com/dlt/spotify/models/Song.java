package com.dlt.spotify.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song extends SpotifyModel {

    @Column(nullable = false, columnDefinition = "boolean default true")
    @Getter
    @Setter
    private boolean liked, saved;

    @Getter
    @Setter
    private int spotify_lap, spotify_pc;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "songs")
    private Set<Playlist> playlists = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(mappedBy = "songs")
    private Set<Person> people = new HashSet<>();
}
