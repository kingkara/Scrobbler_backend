package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.domain.TrackDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TracksMapperTestSuite {
    @Autowired
    private TracksMapper mapper;

    @Test
    void shouldMapToTrackDto() {
        //Given
        Track track = new Track("Test title", new Artist("Test artist", "Test artist id"));
        //When

        TrackDto trackDto = mapper.mapToTrackDto(track);

        //Then
        assertEquals("Test title", trackDto.getTitle());
        assertEquals("Test artist", trackDto.getArtist().getName());
        assertEquals("Test artist id", trackDto.getArtist().getSpotifyArtistId());
    }
}