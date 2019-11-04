package com.crud.scrobbler_backend.scheduler;

import com.crud.scrobbler_backend.services.LyricsApiService;
import com.crud.scrobbler_backend.services.SpotifyService;
import com.crud.scrobbler_backend.spotify.Facade.SpotifyFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class GetTracksFromSpotifyScheduler {
    @Autowired
    private SpotifyFacade spotifyFacade;
    @Autowired
    private SpotifyService service;
    @Autowired
    private LyricsApiService lyricsApiService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void getTracksFromSpotify() throws Exception {
        spotifyFacade.saveUsersTracksAndArtists();
        System.out.println(lyricsApiService.getLyrics(service.getCurrentPlaying().getArtistDtos().get(0).getName(), service.getCurrentPlaying().getTitle()).getLyrics());
    }
}
