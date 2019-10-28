package com.crud.scrobbler_backend.spotify.saveToDatabase;

import com.crud.scrobbler_backend.domain.*;
import com.crud.scrobbler_backend.exceptions.*;
import com.crud.scrobbler_backend.services.ArtistsService;
import com.crud.scrobbler_backend.services.TracksService;
import com.crud.scrobbler_backend.services.UsersService;
import com.crud.scrobbler_backend.spotify.client.SpotifyClient;
import com.crud.scrobbler_backend.spotify.sorter.InstantSorter;
import com.crud.scrobbler_backend.spotify.validator.ArtistsAndTracksValidator;
import com.crud.scrobbler_backend.spotify.validator.UsersArtistsAndTracksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Saver {
    @Autowired
    private SpotifyClient client;
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


    private List<SpotifyTrackDto> lastlyPlayed = client.getRecentlyPlayed();
    private SpotifyTrackDto currentPlayed = client.getCurrentPlayedTrack();

    public void saveTracksAndArtists() {
        lastlyPlayed.forEach(track -> artistsAndTracksValidator.validateTrack(track));
        artistsAndTracksValidator.validateTrack(currentPlayed);
    }

    public void saveUsersTracksAndArtists(long userId) throws UserNotFoundException {
        User user = usersService.getUser(userId);
        lastlyPlayed.add(currentPlayed);
        lastlyPlayed.sort(new InstantSorter());
        lastlyPlayed.forEach(track -> {
            try {
                usersArtistsAndTracksValidator.validateUsersArtist(userId,
                        artistsService.getArtistByName(track.getArtist().getName()).getArtistId(), track.getPlayedAt());

                usersArtistsAndTracksValidator.validateUsersTrack(userId, tracksService.findByTitle(track.getName()).getId(), track.getPlayedAt());
            } catch (UserNotFoundException | ArtistNotFoundException | UsersArtistNotFoundException | UsersTrackNotFoundException
                    | TrackNotFoundException e) {
                e.printStackTrace();
            }
        });

    }
}
