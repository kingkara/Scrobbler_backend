package com.crud.scrobbler_backend.scheduler;

import com.crud.scrobbler_backend.services.SpotifyService;
import com.crud.scrobbler_backend.spotify.saveToDatabase.Saver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class GetTracksFromSpotifyScheduler {
    @Autowired
    private Saver saver;
    @Autowired
    private SpotifyService service;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void getTracksFromSpotify() throws JsonProcessingException {
        saver.saveUsersTracksAndArtists();
        service.getCurrentPlaying();
    }
}
