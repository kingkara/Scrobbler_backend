package com.crud.scrobbler_backend.scheduler;

import com.crud.scrobbler_backend.repository.SpotifyRepository;
import com.crud.scrobbler_backend.spotify.client.SpotifyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetTracksFromSpotifyScheduler {
    @Autowired
    private SpotifyRepository repository;
    @Autowired
    private SpotifyClient client;
}
