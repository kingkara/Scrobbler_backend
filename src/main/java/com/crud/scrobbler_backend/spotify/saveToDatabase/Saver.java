package com.crud.scrobbler_backend.spotify.saveToDatabase;

import com.crud.scrobbler_backend.domain.*;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrack;
import com.crud.scrobbler_backend.exceptions.*;
import com.crud.scrobbler_backend.mapper.SpotifyTracksMapper;
import com.crud.scrobbler_backend.services.ArtistsService;
import com.crud.scrobbler_backend.services.SpotifyService;
import com.crud.scrobbler_backend.services.TracksService;
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
public class Saver {
    @Value("${spotify.app.user.id}")
    private String clientId;
    @Autowired
    private SpotifyService spotifyService;
    @Autowired
    private SpotifyTracksMapper spotifyTracksMapper;
    @Autowired
    private ArtistsAndTracksValidator artistsAndTracksValidator;
    @Autowired
    private UsersService usersService;
    @Autowired
    private TracksService tracksService;
    @Autowired
    private ArtistsService artistsService;
    @Autowired
    private UsersArtistsAndTracksValidator usersArtistsAndTracksValidator;


    public void saveTracksAndArtists() throws JsonProcessingException {
        List<SpotifyFullTrack> lastlyPlayed = spotifyTracksMapper.mapToSpotifyTrackList(spotifyService.getPlayback());
        lastlyPlayed.forEach(track -> artistsAndTracksValidator.validateTrack(track));
        System.out.println(artistsService.getArtists().size());
    }

    public void saveUsersTracksAndArtists() throws JsonProcessingException {
        List<SpotifyFullTrack> lastlyPlayed = spotifyTracksMapper.mapToSpotifyTrackList(spotifyService.getPlayback());
        User user = usersService.getUserBySpotifyId(clientId);
        System.out.println(user.getUsername());
        lastlyPlayed.sort(new StringSorter());
        System.out.println(lastlyPlayed.get(0).getSpotifyTrackDto().getTitle());
        lastlyPlayed.forEach(track -> {
            try {
                usersArtistsAndTracksValidator.validateUsersArtist(user.getId(),
                        artistsService.getArtistByName("Yiurima").getArtistId(), track.getPlayedAt());
                usersArtistsAndTracksValidator.validateUsersTrack(user.getId(), tracksService.findByTitle(track.getSpotifyTrackDto().getTitle()).getId(), track.getPlayedAt());
            } catch (UserNotFoundException | ArtistNotFoundException | UsersArtistNotFoundException | UsersTrackNotFoundException
                    | TrackNotFoundException e) {
                e.printStackTrace();
            }
        });

    }
}
