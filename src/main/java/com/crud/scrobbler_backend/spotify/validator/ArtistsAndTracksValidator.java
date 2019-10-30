package com.crud.scrobbler_backend.spotify.validator;

import com.crud.scrobbler_backend.domain.*;
import com.crud.scrobbler_backend.domain.spotify.SpotifyArtist;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrack;
import com.crud.scrobbler_backend.mapper.SpotifyArtistsMapper;
import com.crud.scrobbler_backend.services.ArtistsService;
import com.crud.scrobbler_backend.services.TracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArtistsAndTracksValidator {
    @Autowired
    private ArtistsService artistsService;
    @Autowired
    private TracksService tracksService;
    @Autowired
    private SpotifyArtistsMapper mapper;

    private List<Artist> validateArtist(List<SpotifyArtist> spotifyArtist) {
        List<Artist> newArtists = spotifyArtist.stream()
                .map(artist -> new Artist(artist.getName(), artist.getId()))
                .collect(Collectors.toList());
        List<Artist> artistToCheck = newArtists.stream()
                .map(artist -> artistsService.getArtistByName(artist.getName()))
                .collect(Collectors.toList());
        if (artistToCheck.size() != 0) {
            return artistToCheck;
        }
        for(Artist artist: newArtists) {
            artistsService.addArtist(artist);
        }
        return newArtists;
    }

    public void validateTrack(SpotifyFullTrack spotifyFullTrack) {
        Track newTrack = new Track(spotifyFullTrack.getSpotifyTrackDto().getTitle(), validateArtist((mapper.mapToSpotifyArtistList(spotifyFullTrack.getSpotifyTrackDto().getSpotifyArtistDto()))).get(0));
        Track trackToCheck = tracksService.findByTitle(newTrack.getTitle());
        if (trackToCheck != null) {
            return;
        }
        tracksService.addTrack(newTrack);
    }
}
