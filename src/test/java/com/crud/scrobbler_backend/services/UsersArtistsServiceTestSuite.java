package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.domain.UsersArtist;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsersArtistsServiceTestSuite {
    @Autowired
    private UsersArtistsService service;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ArtistsService artistsService;

    @Test
    void shouldGetAllArtists() throws Exception {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        Artist artist = new Artist("test artist name", "test artist id");
        usersService.saveUser(user);
        artistsService.addArtist(artist);

        UsersArtist usersArtist = new UsersArtist(user, artist);
        usersArtist.setLastPlayedTime("test lastly played at");
        service.addUsersArtist(usersArtist);
        long userId = usersArtist.getUser().getId();

        //When
        List<UsersArtist> usersArtists = service.getAllArtists(userId);
        int usersArtistsRow = usersArtists.size() - 1;

        //Then
        assertEquals("test name", usersArtists.get(usersArtistsRow).getUser().getUsername());
        assertEquals("test email", usersArtists.get(usersArtistsRow).getUser().getEmail());
        assertEquals("test spotify id", usersArtists.get(usersArtistsRow).getUser().getSpotifyId());
        assertEquals("test artist name", usersArtists.get(usersArtistsRow).getArtist().getName());
        assertEquals("test artist id", usersArtists.get(usersArtistsRow).getArtist().getSpotifyArtistId());
        assertEquals("test lastly played at", usersArtists.get(usersArtistsRow).getLastPlayedTime());
        assertEquals(1, usersArtists.get(usersArtistsRow).getCount());

        //CleanUp
        service.deleteUsersArtist(artist.getArtistId(), userId);
        artistsService.deleteArtist(artist.getArtistId());
        usersService.deleteUser(userId);
    }

    @Test
    void shouldGetTopArtists() throws Exception {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        usersService.saveUser(user);
        long usersId = user.getId();

        Artist artist = new Artist("test artist name", "test artist id");
        artistsService.addArtist(artist);
        Artist artist2 = new Artist("test artist name 2", "test artist id 2");
        artistsService.addArtist(artist2);
        Artist artist3 = new Artist("test artist name 3", "test artist id 3");
        artistsService.addArtist(artist3);
        Artist artist4 = new Artist("test artist name 4", "test artist id 4");
        artistsService.addArtist(artist4);
        Artist artist5 = new Artist("test artist name 5", "test artist id 5");
        artistsService.addArtist(artist5);
        Artist artist6 = new Artist("test artist name 6", "test artist id 6");
        artistsService.addArtist(artist6);

        UsersArtist usersArtist = new UsersArtist(user, artist);
        usersArtist.setLastPlayedTime("test lastly played at");
        usersArtist.setCount(2);
        service.addUsersArtist(usersArtist);
        UsersArtist usersArtist2 = new UsersArtist(user, artist2);
        usersArtist2.setLastPlayedTime("test lastly played at 2");
        usersArtist2.setCount(3);
        service.addUsersArtist(usersArtist2);
        UsersArtist usersArtist3 = new UsersArtist(user, artist3);
        usersArtist3.setLastPlayedTime("test lastly played at 3");
        usersArtist3.setCount(4);
        service.addUsersArtist(usersArtist3);
        UsersArtist usersArtist4 = new UsersArtist(user, artist4);
        usersArtist4.setLastPlayedTime("test lastly played at 4");
        usersArtist4.setCount(5);
        service.addUsersArtist(usersArtist4);
        UsersArtist usersArtist5 = new UsersArtist(user, artist5);
        usersArtist5.setLastPlayedTime("test lastly played at 5");
        usersArtist5.setCount(6);
        service.addUsersArtist(usersArtist5);
        UsersArtist usersArtist6 = new UsersArtist(user, artist6);
        usersArtist6.setLastPlayedTime("test lastly played at 6");
        usersArtist6.setCount(7);
        service.addUsersArtist(usersArtist6);

        //When
        List<UsersArtist> usersArtists = service.getTopArtists(usersId);

        //Then
        assertEquals(5, usersArtists.size());
        assertEquals("test name", usersArtists.get(0).getUser().getUsername());
        assertEquals("test email", usersArtists.get(0).getUser().getEmail());
        assertEquals("test spotify id", usersArtists.get(0).getUser().getSpotifyId());
        assertEquals("test artist name 6", usersArtists.get(0).getArtist().getName());
        assertEquals("test artist id 6", usersArtists.get(0).getArtist().getSpotifyArtistId());
        assertEquals(7, usersArtists.get(0).getCount());
        assertEquals("test artist name 5", usersArtists.get(1).getArtist().getName());
        assertEquals("test artist id 5", usersArtists.get(1).getArtist().getSpotifyArtistId());
        assertEquals(6, usersArtists.get(1).getCount());
        assertEquals("test artist name 4", usersArtists.get(2).getArtist().getName());
        assertEquals("test artist id 4", usersArtists.get(2).getArtist().getSpotifyArtistId());
        assertEquals(5, usersArtists.get(2).getCount());
        assertEquals("test artist name 3", usersArtists.get(3).getArtist().getName());
        assertEquals("test artist id 3", usersArtists.get(3).getArtist().getSpotifyArtistId());
        assertEquals(4, usersArtists.get(3).getCount());
        assertEquals("test artist name 2", usersArtists.get(4).getArtist().getName());
        assertEquals("test artist id 2", usersArtists.get(4).getArtist().getSpotifyArtistId());
        assertEquals(3, usersArtists.get(4).getCount());

        //CleanUp
        service.deleteUsersArtist(artist.getArtistId(), usersId);
        service.deleteUsersArtist(artist2.getArtistId(), usersId);
        service.deleteUsersArtist(artist3.getArtistId(), usersId);
        service.deleteUsersArtist(artist4.getArtistId(), usersId);
        service.deleteUsersArtist(artist5.getArtistId(), usersId);
        service.deleteUsersArtist(artist6.getArtistId(), usersId);
        artistsService.deleteArtist(artist.getArtistId());
        artistsService.deleteArtist(artist2.getArtistId());
        artistsService.deleteArtist(artist3.getArtistId());
        artistsService.deleteArtist(artist4.getArtistId());
        artistsService.deleteArtist(artist5.getArtistId());
        artistsService.deleteArtist(artist6.getArtistId());
        usersService.deleteUser(usersId);
    }

    @Test
    void shouldGetByUserAndArtistsId() throws Exception {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        usersService.saveUser(user);
        long usersId = user.getId();

        Artist artist = new Artist("test artist name", "test artist id");
        artistsService.addArtist(artist);
        long artistId = artist.getArtistId();

        UsersArtist usersArtist = new UsersArtist(user, artist);
        usersArtist.setLastPlayedTime("test lastly played at");
        service.addUsersArtist(usersArtist);
        UsersArtist.UsersArtistIdBuilder id = usersArtist.getId();

        //When
        UsersArtist usersArtistFromDb = service.getByUserAndArtistsId(usersId, artistId);

        //Then
        assertEquals("test name", usersArtistFromDb.getUser().getUsername());
        assertEquals("test email", usersArtistFromDb.getUser().getEmail());
        assertEquals("test spotify id", usersArtistFromDb.getUser().getSpotifyId());
        assertEquals("test artist name", usersArtistFromDb.getArtist().getName());
        assertEquals("test artist id", usersArtistFromDb.getArtist().getSpotifyArtistId());
        assertEquals("test lastly played at", usersArtistFromDb.getLastPlayedTime());
        assertEquals(1, usersArtistFromDb.getCount());

        //CleanUp
        service.deleteUsersArtist(artistId, usersId);
        artistsService.deleteArtist(artistId);
        usersService.deleteUser(usersId);
    }

    @Test
    void updateCount() throws Exception {
        //Given
        User user = new User("test name", "test email", "test spotify id");
        usersService.saveUser(user);
        long usersId = user.getId();

        Artist artist = new Artist("test artist name", "test artist id");
        artistsService.addArtist(artist);
        long artistId = artist.getArtistId();

        UsersArtist usersArtist = new UsersArtist(user, artist);
        usersArtist.setLastPlayedTime("test lastly played at");
        service.addUsersArtist(usersArtist);
        UsersArtist.UsersArtistIdBuilder id = usersArtist.getId();

        UsersArtist usersArtistFromDb = service.getByUserAndArtistsId(usersId, artistId);

        //When
        usersArtistFromDb.setCount(10);
        UsersArtist usersArtistSaved = service.addUsersArtist(usersArtistFromDb);

        //Then
        assertEquals("test name", usersArtistSaved.getUser().getUsername());
        assertEquals("test email", usersArtistSaved.getUser().getEmail());
        assertEquals("test spotify id", usersArtistSaved.getUser().getSpotifyId());
        assertEquals("test artist name", usersArtistSaved.getArtist().getName());
        assertEquals("test artist id", usersArtistSaved.getArtist().getSpotifyArtistId());
        assertEquals("test lastly played at", usersArtistSaved.getLastPlayedTime());
        assertEquals(10, usersArtistSaved.getCount());

        //CleanUp
        service.deleteUsersArtist(artistId, usersId);
        artistsService.deleteArtist(artistId);
        usersService.deleteUser(usersId);
    }
}