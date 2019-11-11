package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.spotify.SpotifyCurrentlyPlayedDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrackDto;
import com.crud.scrobbler_backend.spotify.client.SpotifyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyService {
    @Autowired
    private SpotifyClient client;

    public List<SpotifyFullTrackDto> getPlayback() {
        return client.getRecentlyPlayed();
    }

    public SpotifyCurrentlyPlayedDto getCurrentPlaying() {
        return client.getCurrentPlayedTrack();
    }

}
