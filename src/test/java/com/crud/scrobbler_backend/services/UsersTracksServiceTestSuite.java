package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.domain.UsersTrack;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsersTracksServiceTestSuite {
    @Autowired
    private UsersTracksService service;
    @Autowired
    private UsersService usersService;
    @Autowired
    private TracksService tracksService;
    @Autowired
    private ArtistsService artistsService;

    @Test
    void shouldGetAllUsersTracks() throws Exception {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        usersService.saveUser(user);
        Artist artist = new Artist("Test artist name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        tracksService.addTrack(track);

        UsersTrack usersTrack = new UsersTrack(user, track);
        usersTrack.setLastPlayedTime("test lastly played");
        service.addUsersTrack(usersTrack);

        long usersId = usersTrack.getUser().getId();

        //When
        List<UsersTrack> usersTracks = service.getAllUsersTracks(usersId);
        int elementToDelete = usersTracks.size() - 1;

        //Then
        assertEquals("test name", usersTracks.get(elementToDelete).getUser().getUsername());
        assertEquals("test email", usersTracks.get(elementToDelete).getUser().getEmail());
        assertEquals("test spotify id", usersTracks.get(elementToDelete).getUser().getSpotifyId());
        assertEquals("Test title", usersTracks.get(elementToDelete).getTrack().getTitle());
        assertEquals("Test artist name", usersTracks.get(elementToDelete).getTrack().getArtist().getName());
        assertEquals("Test id", usersTracks.get(elementToDelete).getTrack().getArtist().getSpotifyArtistId());
        assertEquals("test lastly played", usersTracks.get(elementToDelete).getLastPlayedTime());
        assertFalse(usersTracks.get(elementToDelete).isFavouriteStatus());
        assertEquals(1, usersTracks.get(elementToDelete).getCount());

        //CleanUp
        service.deleteUsersTrack(usersId, track.getId());
        tracksService.deleteTrack(track.getId());
        artistsService.deleteArtist(artist.getArtistId());
        usersService.deleteUser(usersId);
    }

    @Test
    void shouldGetTopTracks() throws Exception {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        usersService.saveUser(user);
        long usersId = user.getId();

        Artist artist = new Artist("Test artist name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        tracksService.addTrack(track);
        Track track2 = new Track("Test title 2", artist);
        tracksService.addTrack(track2);
        Track track3 = new Track("Test title 3", artist);
        tracksService.addTrack(track3);
        Track track4 = new Track("Test title 4", artist);
        tracksService.addTrack(track4);
        Track track5 = new Track("Test title 5", artist);
        tracksService.addTrack(track5);
        Track track6 = new Track("Test title 6", artist);
        tracksService.addTrack(track6);

        UsersTrack usersTrack = new UsersTrack(user, track);
        usersTrack.setLastPlayedTime("test lastly played");
        usersTrack.setCount(2);
        service.addUsersTrack(usersTrack);
        UsersTrack usersTrack2 = new UsersTrack(user, track2);
        usersTrack2.setLastPlayedTime("test lastly played 2");
        usersTrack2.setCount(3);
        service.addUsersTrack(usersTrack2);
        UsersTrack usersTrack3 = new UsersTrack(user, track3);
        usersTrack3.setLastPlayedTime("test lastly played 3");
        usersTrack3.setCount(4);
        service.addUsersTrack(usersTrack3);
        UsersTrack usersTrack4 = new UsersTrack(user, track4);
        usersTrack4.setLastPlayedTime("test lastly played 4");
        usersTrack4.setCount(5);
        service.addUsersTrack(usersTrack4);
        UsersTrack usersTrack5 = new UsersTrack(user, track5);
        usersTrack5.setLastPlayedTime("test lastly played 5");
        usersTrack5.setCount(6);
        service.addUsersTrack(usersTrack5);
        UsersTrack usersTrack6 = new UsersTrack(user, track6);
        usersTrack6.setLastPlayedTime("test lastly played 6");
        usersTrack6.setCount(7);
        service.addUsersTrack(usersTrack6);

        //When
        List<UsersTrack> usersTopTracks = service.getTopTracks(usersId);

        //Then
        assertEquals(5, usersTopTracks.size());
        assertEquals(7, usersTopTracks.get(0).getCount());
        assertEquals("test name", usersTopTracks.get(0).getUser().getUsername());
        assertEquals("test email", usersTopTracks.get(0).getUser().getEmail());
        assertEquals("test spotify id", usersTopTracks.get(0).getUser().getSpotifyId());
        assertEquals("Test title 6", usersTopTracks.get(0).getTrack().getTitle());
        assertEquals("Test artist name", usersTopTracks.get(0).getTrack().getArtist().getName());
        assertEquals("Test id", usersTopTracks.get(0).getTrack().getArtist().getSpotifyArtistId());
        assertEquals("test lastly played 6", usersTopTracks.get(0).getLastPlayedTime());
        assertFalse(usersTopTracks.get(0).isFavouriteStatus());
        assertEquals(6, usersTopTracks.get(1).getCount());
        assertEquals("test name", usersTopTracks.get(1).getUser().getUsername());
        assertEquals("test email", usersTopTracks.get(1).getUser().getEmail());
        assertEquals("test spotify id", usersTopTracks.get(1).getUser().getSpotifyId());
        assertEquals("Test title 5", usersTopTracks.get(1).getTrack().getTitle());
        assertEquals("Test artist name", usersTopTracks.get(1).getTrack().getArtist().getName());
        assertEquals("Test id", usersTopTracks.get(1).getTrack().getArtist().getSpotifyArtistId());
        assertEquals("test lastly played 5", usersTopTracks.get(1).getLastPlayedTime());
        assertFalse(usersTopTracks.get(0).isFavouriteStatus());
        assertEquals(5, usersTopTracks.get(2).getCount());
        assertEquals("test name", usersTopTracks.get(2).getUser().getUsername());
        assertEquals("test email", usersTopTracks.get(2).getUser().getEmail());
        assertEquals("test spotify id", usersTopTracks.get(2).getUser().getSpotifyId());
        assertEquals("Test title 4", usersTopTracks.get(2).getTrack().getTitle());
        assertEquals("Test artist name", usersTopTracks.get(2).getTrack().getArtist().getName());
        assertEquals("Test id", usersTopTracks.get(2).getTrack().getArtist().getSpotifyArtistId());
        assertEquals("test lastly played 4", usersTopTracks.get(2).getLastPlayedTime());
        assertFalse(usersTopTracks.get(0).isFavouriteStatus());
        assertEquals(4, usersTopTracks.get(3).getCount());
        assertEquals("test name", usersTopTracks.get(3).getUser().getUsername());
        assertEquals("test email", usersTopTracks.get(3).getUser().getEmail());
        assertEquals("test spotify id", usersTopTracks.get(3).getUser().getSpotifyId());
        assertEquals("Test title 3", usersTopTracks.get(3).getTrack().getTitle());
        assertEquals("Test artist name", usersTopTracks.get(3).getTrack().getArtist().getName());
        assertEquals("Test id", usersTopTracks.get(3).getTrack().getArtist().getSpotifyArtistId());
        assertEquals("test lastly played 3", usersTopTracks.get(3).getLastPlayedTime());
        assertFalse(usersTopTracks.get(0).isFavouriteStatus());
        assertEquals(3, usersTopTracks.get(4).getCount());
        assertEquals("test name", usersTopTracks.get(4).getUser().getUsername());
        assertEquals("test email", usersTopTracks.get(4).getUser().getEmail());
        assertEquals("test spotify id", usersTopTracks.get(4).getUser().getSpotifyId());
        assertEquals("Test title 2", usersTopTracks.get(4).getTrack().getTitle());
        assertEquals("Test artist name", usersTopTracks.get(4).getTrack().getArtist().getName());
        assertEquals("Test id", usersTopTracks.get(4).getTrack().getArtist().getSpotifyArtistId());
        assertEquals("test lastly played 2", usersTopTracks.get(4).getLastPlayedTime());
        assertFalse(usersTopTracks.get(0).isFavouriteStatus());

        //CleanUp
        service.deleteUsersTrack(usersId, track.getId());
        service.deleteUsersTrack(usersId, track2.getId());
        service.deleteUsersTrack(usersId, track3.getId());
        service.deleteUsersTrack(usersId, track4.getId());
        service.deleteUsersTrack(usersId, track5.getId());
        service.deleteUsersTrack(usersId, track6.getId());
        tracksService.deleteTrack(track.getId());
        tracksService.deleteTrack(track2.getId());
        tracksService.deleteTrack(track3.getId());
        tracksService.deleteTrack(track4.getId());
        tracksService.deleteTrack(track5.getId());
        tracksService.deleteTrack(track6.getId());
        artistsService.deleteArtist(artist.getArtistId());
        usersService.deleteUser(usersId);
    }

    @Test
    void shouldChangeFavouriteStatus() throws Exception {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        usersService.saveUser(user);
        Artist artist = new Artist("Test artist name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        tracksService.addTrack(track);

        UsersTrack usersTrack = new UsersTrack(user, track);
        usersTrack.setLastPlayedTime("test lastly played");
        service.addUsersTrack(usersTrack);

        //When
        UsersTrack usersFavourite = service.changeFavouriteStatus(user.getId(), track.getId());

        //Then
        assertEquals("test name", usersFavourite.getUser().getUsername());
        assertEquals("test email", usersFavourite.getUser().getEmail());
        assertEquals("test spotify id", usersFavourite.getUser().getSpotifyId());
        assertEquals("Test title", usersFavourite.getTrack().getTitle());
        assertEquals("Test artist name", usersFavourite.getTrack().getArtist().getName());
        assertEquals("Test id", usersFavourite.getTrack().getArtist().getSpotifyArtistId());
        assertEquals("test lastly played", usersFavourite.getLastPlayedTime());
        assertEquals(1, usersFavourite.getCount());
        assertTrue(usersFavourite.isFavouriteStatus());

        //CleanUp
        service.deleteUsersTrack(user.getId(), track.getId());
        tracksService.deleteTrack(track.getId());
        artistsService.deleteArtist(artist.getArtistId());
        usersService.deleteUser(user.getId());
    }

    @Test
    void shouldGetFavourites() throws Exception {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        usersService.saveUser(user);
        long usersId = user.getId();

        Artist artist = new Artist("Test artist name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        tracksService.addTrack(track);
        Track track2 = new Track("Test title 2", artist);
        tracksService.addTrack(track2);

        UsersTrack usersTrack = new UsersTrack(user, track);
        usersTrack.setLastPlayedTime("test lastly played");
        service.addUsersTrack(usersTrack);
        UsersTrack usersTrack2 = new UsersTrack(user, track2);
        usersTrack2.setLastPlayedTime("test lastly played 2");
        service.addUsersTrack(usersTrack2);

        service.changeFavouriteStatus(usersId, track2.getId());

        //When
        List<UsersTrack> usersFav = service.getFavourites(usersId);

        //Then
        assertEquals(1, usersFav.size());
        assertEquals("test name", usersFav.get(0).getUser().getUsername());
        assertEquals("test email", usersFav.get(0).getUser().getEmail());
        assertEquals("test spotify id", usersFav.get(0).getUser().getSpotifyId());
        assertEquals("Test title 2", usersFav.get(0).getTrack().getTitle());
        assertEquals("Test artist name", usersFav.get(0).getTrack().getArtist().getName());
        assertEquals("Test id", usersFav.get(0).getTrack().getArtist().getSpotifyArtistId());
        assertEquals("test lastly played 2", usersFav.get(0).getLastPlayedTime());
        assertEquals(1, usersFav.get(0).getCount());
        assertTrue(usersFav.get(0).isFavouriteStatus());

        //CleanUp
        service.deleteUsersTrack(usersId, track.getId());
        service.deleteUsersTrack(usersId, track2.getId());
        tracksService.deleteTrack(track.getId());
        tracksService.deleteTrack(track2.getId());
        artistsService.deleteArtist(artist.getArtistId());
        usersService.deleteUser(usersId);
    }

    @Test
    void shouldGetByUserAndTrackId() throws Exception {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        usersService.saveUser(user);
        long userId = user.getId();
        Artist artist = new Artist("Test artist name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        tracksService.addTrack(track);
        long trackId = track.getId();

        UsersTrack usersTrack = new UsersTrack(user, track);
        usersTrack.setLastPlayedTime("test lastly played");
        service.addUsersTrack(usersTrack);

        //When
        UsersTrack usersTrackFromDb = service.getByUserAndTrackId(userId, trackId);

        //Then
        assertEquals("test name", usersTrackFromDb.getUser().getUsername());
        assertEquals("test email", usersTrackFromDb.getUser().getEmail());
        assertEquals("test spotify id", usersTrackFromDb.getUser().getSpotifyId());
        assertEquals("Test title", usersTrackFromDb.getTrack().getTitle());
        assertEquals("Test artist name", usersTrackFromDb.getTrack().getArtist().getName());
        assertEquals("Test id", usersTrackFromDb.getTrack().getArtist().getSpotifyArtistId());
        assertEquals("test lastly played", usersTrackFromDb.getLastPlayedTime());
        assertEquals(1, usersTrackFromDb.getCount());
        assertFalse(usersTrackFromDb.isFavouriteStatus());

        //CleanUp
        service.deleteUsersTrack(user.getId(), track.getId());
        tracksService.deleteTrack(trackId);
        artistsService.deleteArtist(artist.getArtistId());
        usersService.deleteUser(userId);
    }

    @Test
    void updateCountAndLastlyPlayed() throws Exception {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        usersService.saveUser(user);
        Artist artist = new Artist("Test artist name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        tracksService.addTrack(track);

        UsersTrack usersTrack = new UsersTrack(user, track);
        usersTrack.setLastPlayedTime("test lastly played");
        service.addUsersTrack(usersTrack);
        UsersTrack.UsersTrackIdBuilder id = usersTrack.getId();

        //When
        UsersTrack updatedUsersTrack = service.updateCountAndLastlyPlayed(id, "Updated lastly played");

        //Then
        assertEquals("test name", updatedUsersTrack.getUser().getUsername());
        assertEquals("test email", updatedUsersTrack.getUser().getEmail());
        assertEquals("test spotify id", updatedUsersTrack.getUser().getSpotifyId());
        assertEquals("Test title", updatedUsersTrack.getTrack().getTitle());
        assertEquals("Test artist name", updatedUsersTrack.getTrack().getArtist().getName());
        assertEquals("Test id", updatedUsersTrack.getTrack().getArtist().getSpotifyArtistId());
        assertEquals("Updated lastly played", updatedUsersTrack.getLastPlayedTime());
        assertEquals(2, updatedUsersTrack.getCount());
        assertFalse(updatedUsersTrack.isFavouriteStatus());

        //CleanUp
        service.deleteUsersTrack(user.getId(), track.getId());
        tracksService.deleteTrack(track.getId());
        artistsService.deleteArtist(artist.getArtistId());
        usersService.deleteUser(user.getId());
    }
}