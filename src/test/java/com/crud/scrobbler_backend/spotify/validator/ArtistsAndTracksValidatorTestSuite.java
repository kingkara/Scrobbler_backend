package com.crud.scrobbler_backend.spotify.validator;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.domain.spotify.SpotifyArtistDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrack;
import com.crud.scrobbler_backend.domain.spotify.SpotifyTrackDto;
import com.crud.scrobbler_backend.exceptions.ArtistNotFoundException;
import com.crud.scrobbler_backend.services.ArtistsService;
import com.crud.scrobbler_backend.services.TracksService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ArtistsAndTracksValidatorTestSuite {
    @Autowired
    private ArtistsAndTracksValidator validator;
    @Autowired
    private ArtistsService artistsService;
    @Autowired
    private TracksService tracksService;

    @Test
    void shouldValidateArtist() throws ArtistNotFoundException {
        //Given
        Artist artist = new Artist("Test artist name", "Test spotify id");
        artistsService.addArtist(artist);

        SpotifyArtistDto artistDto = new SpotifyArtistDto("Test spotify id", "Test artist name");
        SpotifyArtistDto artistDto1 = new SpotifyArtistDto("Test spotify id 2", "Test artist name 2");
        SpotifyArtistDto artistDto2 = new SpotifyArtistDto("Test spotify id 3", "Test artist name 3");
        List<SpotifyArtistDto> spotifyArtistDtos = new ArrayList<>();
        spotifyArtistDtos.add(artistDto);
        spotifyArtistDtos.add(artistDto1);
        spotifyArtistDtos.add(artistDto2);

        //When
        List<Artist> verifiedArtists = validator.validateArtist(spotifyArtistDtos);

        //Then
        assertEquals(2, verifiedArtists.size());
        assertEquals("Test spotify id 2", verifiedArtists.get(0).getSpotifyArtistId());
        assertEquals("Test artist name 2", verifiedArtists.get(0).getName());
        assertEquals("Test spotify id 3", verifiedArtists.get(1).getSpotifyArtistId());
        assertEquals("Test artist name 3", verifiedArtists.get(1).getName());

        //CleanUp
        artistsService.deleteArtist(artist.getArtistId());
        artistsService.deleteArtist(verifiedArtists.get(0).getArtistId());
        artistsService.deleteArtist(verifiedArtists.get(1).getArtistId());
    }


    @Test
    void shouldValidateTrack() throws Exception {
        //Given
        Artist artist = new Artist("test artist name", "test spotify id");
        artistsService.addArtist(artist);
        Track track = new Track("Test title", artist);
        tracksService.addTrack(track);
        long trackId = track.getId();

        SpotifyArtistDto spotifyArtistDto = new SpotifyArtistDto("test spotify id", "test artist name");
        List<SpotifyArtistDto> artists = new ArrayList<>();
        artists.add(spotifyArtistDto);
        SpotifyFullTrack spotifyFullTrack = new SpotifyFullTrack(
                new SpotifyTrackDto(artists, "test track id 2", "test title 2"), "test played at 2");

        //When
        Track validatedTrack = validator.validateTrack(spotifyFullTrack);
        long validatedId = validatedTrack.getId();

        //Then
        assertEquals("test title 2", validatedTrack.getTitle());
        assertEquals("test artist name", validatedTrack.getArtist().getName());
        assertEquals("test spotify id", validatedTrack.getArtist().getSpotifyArtistId());

        //CleanUp
        tracksService.deleteTrack(trackId);
        tracksService.deleteTrack(validatedId);
        artistsService.deleteArtist(artist.getArtistId());
    }
}