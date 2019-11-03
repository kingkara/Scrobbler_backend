package com.crud.scrobbler_backend.spotify.validator;

import com.crud.scrobbler_backend.domain.*;
import com.crud.scrobbler_backend.services.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UsersArtistsAndTracksValidatorTestSuite {
    @Autowired
    private UsersArtistsAndTracksValidator validator;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ArtistsService artistsService;
    @Autowired
    private TracksService tracksService;
    @Autowired
    private UsersArtistsService usersArtistsService;
    @Autowired
    private UsersTracksService usersTracksService;

    @Test
    void shouldValidateUsersArtist() throws Exception {
        //Given
        User user = new User("test username", "test email", "test spotify id");
        usersService.saveUser(user);
        Artist artist = new Artist("test artist name", "test artists spotify id");
        artistsService.addArtist(artist);

        UsersArtist usersArtist = new UsersArtist(user, artist);
        usersArtist.setLastPlayedTime("lastly played");
        usersArtistsService.addUsersArtist(usersArtist);

        //When
        validator.validateUsersArtist(user, artist, "test lastly played at");
        UsersArtist validatedUsersArtist = usersArtistsService.getByUserAndArtistsId(user.getId(), artist.getArtistId());

        //Then
        assertEquals(2, validatedUsersArtist.getCount());
        assertEquals("test lastly played at", validatedUsersArtist.getLastPlayedTime());

        //CleanUp
        usersArtistsService.deleteUsersArtist(artist.getArtistId(), user.getId());
        artistsService.deleteArtist(artist.getArtistId());
        usersService.deleteUser(user.getId());
    }

    @Test
    void shouldValidateUsersTrack() throws Exception {
        //Given
        User user = new User("test username", "test email", "test spotify id");
        usersService.saveUser(user);
        Artist artist = new Artist("test artist name", "test artists spotify id");
        artistsService.addArtist(artist);
        Track track = new Track("test tile", artist);
        tracksService.addTrack(track);

        UsersTrack usersTrack = new UsersTrack(user, track);
        usersTrack.setLastPlayedTime("lastly played");
        usersTracksService.addUsersTrack(usersTrack);

        //When
        validator.validateUsersTrack(user, track, "updated lastly played");
        UsersTrack validatedTrack = usersTracksService.getByUserAndTrackId(user.getId(), track.getId());

        //Then
        assertEquals(2, validatedTrack.getCount());
        assertEquals("updated lastly played", validatedTrack.getLastPlayedTime());

        //CleanUp
        usersTracksService.deleteUsersTrack(user.getId(), track.getId());
        usersService.deleteUser(user.getId());
        tracksService.deleteTrack(track.getId());
        artistsService.deleteArtist(artist.getArtistId());
    }
}