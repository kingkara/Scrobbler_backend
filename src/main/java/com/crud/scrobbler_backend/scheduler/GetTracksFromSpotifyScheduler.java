package com.crud.scrobbler_backend.scheduler;

import com.crud.scrobbler_backend.spotify.Facade.SpotifyFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class GetTracksFromSpotifyScheduler {
    @Autowired
    private SpotifyFacade spotifyFacade;

    @Scheduled(fixedRate = 120000)
    public void getTracksFromSpotify() {
        spotifyFacade.saveUsersTracksAndArtists();
    }
}
