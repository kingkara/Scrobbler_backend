package com.crud.scrobbler_backend.spotify.validator;

import com.crud.scrobbler_backend.domain.*;
import com.crud.scrobbler_backend.domain.spotify.SpotifyArtistDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrack;
import com.crud.scrobbler_backend.services.ArtistsService;
import com.crud.scrobbler_backend.services.TracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArtistsAndTracksValidator {
    @Autowired
    private ArtistsService artistsService;
    @Autowired
    private TracksService tracksService;

    public List<Artist> validateArtist(List<SpotifyArtistDto> spotifyArtistDto) {
        List<Artist> newArtists = new ArrayList<>();
        for (SpotifyArtistDto artistToValidate : spotifyArtistDto) {
            Artist artistFromDb = artistsService.getArtistByName(artistToValidate.getName());
            if (artistFromDb == null) {
                Artist newArtist = new Artist(artistToValidate.getName(), artistToValidate.getId());
                artistsService.addArtist(newArtist);
                newArtists.add(newArtist);
            }
        }
        return newArtists;
    }

    public Track validateTrack(SpotifyFullTrack spotifyFullTrack) {
        List<Artist> validatedArtists = validateArtist(spotifyFullTrack.getSpotifyTrackDto().getSpotifyArtistDto());
        Artist artistToConstructor;
        if (validatedArtists.size() == 0) {
            artistToConstructor = artistsService.getArtistByName(spotifyFullTrack.getSpotifyTrackDto().getSpotifyArtistDto().get(0).getName());
        } else {
            artistToConstructor = validatedArtists.get(0);
        }

        Track newTrack = new Track(spotifyFullTrack.getSpotifyTrackDto().getTitle(), artistToConstructor);
        Track trackToCheck = tracksService.findByTitle(newTrack.getTitle());
        if (trackToCheck != null) {
            return trackToCheck;
        }
        return tracksService.addTrack(newTrack);
    }
}
