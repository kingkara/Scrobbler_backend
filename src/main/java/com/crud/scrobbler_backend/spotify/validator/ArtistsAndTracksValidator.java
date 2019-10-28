package com.crud.scrobbler_backend.spotify.validator;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.SpotifyArtistDto;
import com.crud.scrobbler_backend.domain.SpotifyTrackDto;
import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.mapper.SpotifyArtistsMapper;
import com.crud.scrobbler_backend.services.ArtistsService;
import com.crud.scrobbler_backend.services.TracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArtistsAndTracksValidator {
    @Autowired
    private ArtistsService artistsService;
    @Autowired
    private TracksService tracksService;
    @Autowired
    private SpotifyArtistsMapper mapper;

    private Artist validateArtist(SpotifyArtistDto spotifyArtistDto) {
        Artist newArtist = new Artist(spotifyArtistDto.getName());
        Artist artistToCheck = artistsService.getArtistByName(newArtist.getName());
        if(artistToCheck!=null) {
            return artistToCheck;
        }
        artistsService.addArtist(newArtist);
        return newArtist;
    }

    public Track validateTrack(SpotifyTrackDto spotifyTrackDto) {
        Track newTrack = new Track(spotifyTrackDto.getName(), validateArtist(mapper.mapToSpotifyArtistDto(spotifyTrackDto.getArtist())));
        Track trackToCheck = tracksService.findByTitle(newTrack.getTitle());
        if(trackToCheck!=null) {
            return trackToCheck;
        }
        tracksService.addTrack(newTrack);
        return newTrack;
    }
}
