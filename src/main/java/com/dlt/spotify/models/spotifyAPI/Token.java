package com.dlt.spotify.models.spotifyAPI;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String username, password;

    @Getter
    @Setter
    private String refresh_token;


    @Getter
    @Setter
    @Column(length = 300)
    private String access_token;

    @Getter
    private Timestamp expire_at;

    public void setExpire_at(Timestamp expire_at) {
        this.expire_at = expire_at;
    }

    public void setExpire_at(int expire_in) {
        long expire_in_mili = (expire_in * 1000L);
        this.expire_at = new Timestamp(System.currentTimeMillis() + expire_in_mili);

    }

    public String getHeaderContent() {
        return username + ":" + password;
    }
}
