package com.crud.scrobbler_backend.spotify.validator;

import com.crud.scrobbler_backend.domain.*;
import com.crud.scrobbler_backend.exceptions.*;
import com.crud.scrobbler_backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UsersArtistsAndTracksValidator {
    @Autowired
    private UsersArtistsService usersArtistsService;
    @Autowired
    private UsersTracksService usersTracksService;

    public void validateUsersArtist(User user, Artist artist, String lastlyPlayed) throws UsersArtistNotFoundException {
        UsersArtist newUsersArtist = new UsersArtist(user, artist);
        newUsersArtist.setLastPlayedTime(lastlyPlayed);
        UsersArtist usersArtistToCheck = usersArtistsService.getByUserAndArtistsId(user.getId(), artist.getArtistId());
        if (usersArtistToCheck!=null) {
            usersArtistsService.updateCount(usersArtistToCheck.getId(),lastlyPlayed);
            return;
        }
        usersArtistsService.addUsersArtist(newUsersArtist);
    }

    public void validateUsersTrack(User user, Track track, String lastlyPlayed) {
        UsersTrack newUsersTrack = new UsersTrack(user, track);
        newUsersTrack.setLastPlayedTime(lastlyPlayed);
        UsersTrack usersTrackToCheck = usersTracksService.getByUserAndTrackId(user.getId(), track.getId());
        if (usersTrackToCheck!=null) {
            usersTracksService.updateCountAndLastlyPlayed(usersTrackToCheck.getId(), lastlyPlayed);
            return;
        }
        usersTracksService.addUsersTrack(newUsersTrack);
    }
}
