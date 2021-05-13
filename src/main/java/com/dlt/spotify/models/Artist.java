package com.dlt.spotify.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int lastfm;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "artists")
    @JsonIgnoreProperties("artists")
    private Set<Song> songs;
}
