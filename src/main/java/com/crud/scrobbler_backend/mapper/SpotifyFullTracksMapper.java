package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrack;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrackDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpotifyFullTracksMapper {

    public List<SpotifyFullTrack> mapToSpotifyTrackList(final List<SpotifyFullTrackDto> spotifyFullTrackDtos) {
        return spotifyFullTrackDtos.stream()
                .map(s -> new SpotifyFullTrack(s.getSpotifyTrackDto(), s.getPlayedAt()))
                .collect(Collectors.toList());
    }
}
