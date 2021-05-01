package com.dlt.spotify.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class People extends SpotifyModel {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "people_has_songs",
            joinColumns = {@JoinColumn(name = "people")},
            inverseJoinColumns = {@JoinColumn(name = "song")}
    )
    Set<Song> songs = new HashSet<>();
}
