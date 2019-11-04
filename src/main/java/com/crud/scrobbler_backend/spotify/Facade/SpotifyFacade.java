package com.crud.scrobbler_backend.spotify.Facade;

import com.crud.scrobbler_backend.domain.*;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrack;
import com.crud.scrobbler_backend.exceptions.*;
import com.crud.scrobbler_backend.mapper.SpotifyFullTracksMapper;
import com.crud.scrobbler_backend.services.SpotifyService;
import com.crud.scrobbler_backend.services.UsersService;
import com.crud.scrobbler_backend.spotify.sorter.StringSorter;
import com.crud.scrobbler_backend.spotify.validator.ArtistsAndTracksValidator;
import com.crud.scrobbler_backend.spotify.validator.UsersArtistsAndTracksValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpotifyFacade {
    @Value("${spotify.app.user.id}")
    private String clientId;
    @Autowired
    private SpotifyService spotifyService;
    @Autowired
    private SpotifyFullTracksMapper spotifyFullTracksMapper;
    @Autowired
    private ArtistsAndTracksValidator artistsAndTracksValidator;
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersArtistsAndTracksValidator usersArtistsAndTracksValidator;

    public void saveUsersTracksAndArtists() throws JsonProcessingException {
        List<SpotifyFullTrack> lastlyPlayed = spotifyFullTracksMapper.mapToSpotifyTrackList(spotifyService.getPlayback());
        lastlyPlayed.sort(new StringSorter());
        lastlyPlayed.forEach(track -> {
            try {
                Track validatedTrack = artistsAndTracksValidator.validateTrack(track);
                User user = usersService.getUserBySpotifyId(clientId);
                usersArtistsAndTracksValidator.validateUsersArtist(user, validatedTrack.getArtist(), track.getPlayedAt());
                usersArtistsAndTracksValidator.validateUsersTrack(user, validatedTrack, track.getPlayedAt());
            } catch (UsersArtistNotFoundException e) {
                e.printStackTrace();
            }
        });

    }
}
