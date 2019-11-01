package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.exceptions.TrackNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TracksServiceTestSuite {
    @Autowired
    private TracksService service;
    @Autowired
    private ArtistsService artistsService;

    @Test
    void shouldAddTrack() throws TrackNotFoundException {
        //Given
        Artist artist = new Artist("Test name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);

        //When
        Track addedTrack = service.addTrack(track);
        long id = addedTrack.getId();

        //Then
        assertEquals("Test title", addedTrack.getTitle());
        assertEquals("Test name", addedTrack.getArtist().getName());
        assertEquals("Test id", addedTrack.getArtist().getSpotifyArtistId());

        //CleanUp
        service.deleteTrack(id);
    }

    @Test
    void shouldFindByTitle() throws TrackNotFoundException {
        //Given
        Artist artist = new Artist("Test name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title to get", artist);
        service.addTrack(track);
        String title = track.getTitle();

        //When
        Track trackFromDb = service.findByTitle(title);
        long id = trackFromDb.getId();

        //Then
        assertEquals("Test title to get", trackFromDb.getTitle());
        assertEquals("Test name", trackFromDb.getArtist().getName());
        assertEquals("Test id", trackFromDb.getArtist().getSpotifyArtistId());

        //CleanUp
        service.deleteTrack(id);
    }


    @Test
    void shouldGetTrack() throws TrackNotFoundException {
        //Given
        Artist artist = new Artist("Test name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        service.addTrack(track);
        long id = track.getId();

        //When
        Track trackFromDb = service.getTrack(id);

        //Then
        assertEquals("Test title", trackFromDb.getTitle());
        assertEquals("Test name", trackFromDb.getArtist().getName());
        assertEquals("Test id", trackFromDb.getArtist().getSpotifyArtistId());

        //CleanUp
        service.deleteTrack(id);
    }

    @Test
    void shouldGetTracks() throws TrackNotFoundException {
        //Given
        Artist artist = new Artist("Test name", "Test id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        service.addTrack(track);
        long id = track.getId();

        //When
        List<Track> tracks = service.getTracks();
        int trackToDeleteRow = tracks.size()-1;

        //Then
        assertEquals("Test title", tracks.get(trackToDeleteRow).getTitle());
        assertEquals("Test name", tracks.get(trackToDeleteRow).getArtist().getName());
        assertEquals("Test id", tracks.get(trackToDeleteRow).getArtist().getSpotifyArtistId());

        //CleanUp
        service.deleteTrack(id);
    }
}