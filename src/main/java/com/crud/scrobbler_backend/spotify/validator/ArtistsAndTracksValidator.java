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
import java.util.stream.Collectors;

@Component
public class ArtistsAndTracksValidator {
    @Autowired
    private ArtistsService artistsService;
    @Autowired
    private TracksService tracksService;

    private List<Artist> validateArtist(List<SpotifyArtistDto> spotifyArtistDto) {
        List<Artist> newArtists = spotifyArtistDto.stream()
                .map(artist -> new Artist(artist.getName(), artist.getId()))
                .collect(Collectors.toList());

        List<Artist> artistToCheck = new ArrayList<>();
        for (Artist artistToValidate : newArtists) {
            System.out.println(artistToValidate.getName());
            if(artistToValidate.equals(artistsService.getArtistByName(artistToValidate.getName()))){
                artistToCheck.add(artistToValidate);
            }
        }

            if (artistToCheck.size() != 0) {
                for (Artist artist : newArtists) {
                    for (Artist artistToDelete : artistToCheck) {
                        if (artist.equals(artistToDelete)) {
                            newArtists.remove(artist);
                        }
                    }
                }
            }
        for (Artist artist : newArtists) {
            artistsService.addArtist(artist);
        }
        return newArtists;
    }

    public Track validateTrack(SpotifyFullTrack spotifyFullTrack) {
        Track newTrack = new Track(spotifyFullTrack.getSpotifyTrackDto().getTitle(), validateArtist(spotifyFullTrack.getSpotifyTrackDto().getSpotifyArtistDto()).get(0));
        Track trackToCheck = tracksService.findByTitle(newTrack.getTitle());
        if (trackToCheck != null) {
            return trackToCheck;
        }
        return tracksService.addTrack(newTrack);
    }
}
