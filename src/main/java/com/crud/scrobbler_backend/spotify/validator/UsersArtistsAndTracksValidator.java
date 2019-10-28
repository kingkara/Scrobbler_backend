package com.crud.scrobbler_backend.spotify.validator;

import com.crud.scrobbler_backend.domain.UsersArtist;
import com.crud.scrobbler_backend.domain.UsersTrack;
import com.crud.scrobbler_backend.exceptions.*;
import com.crud.scrobbler_backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UsersArtistsAndTracksValidator {
    @Autowired
    private UsersArtistsService usersArtistsService;
    @Autowired
    private UsersTracksService usersTracksService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ArtistsService artistsService;
    @Autowired
    private TracksService tracksService;

    public UsersArtist validateUsersArtist(long userId, long artistId, Instant lastlyPlayed) throws UserNotFoundException, ArtistNotFoundException, UsersArtistNotFoundException {
        UsersArtist newUsersArtist = new UsersArtist(usersService.getUser(userId), artistsService.getArtistById(artistId));
        UsersArtist usersArtistToCheck = usersArtistsService.getByUserAndArtistsId(userId, artistId);
        if (usersArtistToCheck!=null) {
            usersArtistsService.updateCount(usersArtistToCheck.getId(),lastlyPlayed);
            return usersArtistToCheck;
        }
        usersArtistsService.addUsersArtist(newUsersArtist);
        return newUsersArtist;
    }

    public UsersTrack validateUsersTrack(long userId, long trackId, Instant lastlyPlayed) throws UserNotFoundException, TrackNotFoundException, UsersTrackNotFoundException {
        UsersTrack newUsersTrack = new UsersTrack(usersService.getUser(userId), tracksService.getTrack(trackId));
        UsersTrack usersTrackToCheck = usersTracksService.getByUserAndTrackId(userId, trackId);
        if (usersTrackToCheck!=null) {
            usersTracksService.updateCountAndLastlyPlayed(usersTrackToCheck.getId(), lastlyPlayed);
            return usersTrackToCheck;
        }
        usersTracksService.addUsersTrack(newUsersTrack);
        return newUsersTrack;
    }
}
