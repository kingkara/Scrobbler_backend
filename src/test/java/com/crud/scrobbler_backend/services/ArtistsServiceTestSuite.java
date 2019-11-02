package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.exceptions.ArtistNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArtistsServiceTestSuite {
    @Autowired
    private ArtistsService service;

    @Test
    void shouldGetArtistById() throws ArtistNotFoundException {
        //Given
        Artist artist = new Artist("Test name", "Test id");
        service.addArtist(artist);
        long id = artist.getArtistId();

        //When
        Artist artistToCheck = service.getArtistById(id);

        //Then
        assertEquals("Test name", artistToCheck.getName());
        assertEquals("Test id", artistToCheck.getSpotifyArtistId());
        assertEquals(id, artistToCheck.getArtistId());

        //CleanUp
        service.deleteArtist(id);
    }

    @Test
    void shouldGetArtistByName() throws ArtistNotFoundException {
        //Given
        Artist artist = new Artist("Test artist name", "Test id");
        service.addArtist(artist);
        String name = artist.getName();

        //When
        Artist artistToCheck = service.getArtistByName(name);
        long idToDelete = artistToCheck.getArtistId();

        //Then
        assertEquals("Test artist name", artistToCheck.getName());
        assertEquals("Test id", artistToCheck.getSpotifyArtistId());

        //CleanUp
        service.deleteArtist(idToDelete);
    }

    @Test
    void shouldGetArtists() throws ArtistNotFoundException {
        //Given
        Artist artist = new Artist("Test name", "Test id");
        service.addArtist(artist);
        long id = artist.getArtistId();

        //When
        List<Artist> artists = service.getArtists();
        int artistToCheckRow = artists.size() - 1;

        //Then
        assertEquals("Test name", artists.get(artistToCheckRow).getName());
        assertEquals("Test id", artists.get(artistToCheckRow).getSpotifyArtistId());

        //CleanUp
        service.deleteArtist(id);
    }

    @Test
    void shouldAddArtist() throws ArtistNotFoundException {
        //Given
        Artist artist = new Artist("Test name", "Test id");

        //When
        Artist createdArtist = service.addArtist(artist);
        long id = createdArtist.getArtistId();

        //Then
        assertEquals("Test name", createdArtist.getName());
        assertEquals("Test id", createdArtist.getSpotifyArtistId());

        //CleanUp
        service.deleteArtist(id);
    }
}