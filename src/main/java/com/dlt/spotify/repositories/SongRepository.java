package com.dlt.spotify.repositories;

import com.dlt.spotify.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    @Procedure("sort_song")
    int sort_song(int position);
}
