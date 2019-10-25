package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.SpotifyTrackDto;
import com.crud.scrobbler_backend.spotify_client.SpotifyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyService {
    @Autowired
    private SpotifyClient client;

    public List<SpotifyTrackDto> getPlayback() {
        return client.getRecentlyPlayed();
    }

    public SpotifyTrackDto getCurrentPlaying() {
        return client.getCurrentPlayedTrack();
    }
}
