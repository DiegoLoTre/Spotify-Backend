package com.dlt.spotify.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
public class SpotifyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String spotify_url;
}
