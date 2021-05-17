package com.dlt.spotify.models;

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
    private Set<Song> songs;
}
