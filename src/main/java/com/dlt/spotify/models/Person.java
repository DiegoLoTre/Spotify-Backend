package com.dlt.spotify.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person extends SpotifyModel {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "person_has_songs",
            joinColumns = {@JoinColumn(name = "person")},
            inverseJoinColumns = {@JoinColumn(name = "song")}
    )
    Set<Song> songs = new HashSet<>();
}
