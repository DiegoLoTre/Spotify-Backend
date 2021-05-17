package com.dlt.spotify.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter @Setter
    private String language;

    @Getter @Setter
    @OneToMany(mappedBy = "language")
    @JsonIgnoreProperties({"artists", "playlists", "people", "language"})
    private Set<Song> songs;
}
