package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.SpotifyTrack;
import com.crud.scrobbler_backend.domain.SpotifyTrackDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpotifyTrackMapper {
    public SpotifyTrack mapToSpotifyTrack (final SpotifyTrackDto spotifyTrackDto) {
        return new SpotifyTrack(spotifyTrackDto.getId(), spotifyTrackDto.getName(), spotifyTrackDto.getArtist());
    }

    public SpotifyTrackDto mapToSpotifyTrackDto (final SpotifyTrack spotifyTrack) {
        return new SpotifyTrackDto(spotifyTrack.getId(), spotifyTrack.getName(), spotifyTrack.getArtist());
    }

    public List<SpotifyTrackDto> mapToSpotifyTrackDtoList (final List<SpotifyTrack> spotifyTrack) {
        return spotifyTrack.stream()
                .map(s->new SpotifyTrackDto(s.getId(), s.getName(), s.getArtist()))
                .collect(Collectors.toList());
    }
}
