package com.dlt.spotify.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Person extends SpotifyModel {

    @Getter
    @Setter
    @ManyToMany(mappedBy = "people")
    @JsonIgnoreProperties("people")
    private Set<Song> songs;
}
