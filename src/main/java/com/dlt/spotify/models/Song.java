package com.dlt.spotify.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private int spotify, lastfm;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "artist_play_songs",
            joinColumns = {@JoinColumn(name = "song")},
            inverseJoinColumns = {@JoinColumn(name = "artist")}
    )
    @JsonIgnoreProperties("songs")
    private Set<Artist> artists;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "playlist_has_songs",
            joinColumns = {@JoinColumn(name = "song")},
            inverseJoinColumns = {@JoinColumn(name = "playlist")}
    )
    @JsonIgnoreProperties("songs")
    private Set<Playlist> playlists;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_has_songs",
            joinColumns = {@JoinColumn(name = "song")},
            inverseJoinColumns = {@JoinColumn(name = "person")}
    )
    @JsonIgnoreProperties("songs")
    private Set<Person> people;
}
