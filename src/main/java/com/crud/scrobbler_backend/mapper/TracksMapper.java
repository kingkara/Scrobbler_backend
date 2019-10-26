package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.domain.TrackDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TracksMapper {
    public Track mapToTrack (final TrackDto trackDto) {
        return new Track(trackDto.getTitle(), trackDto.getCount(), trackDto.getArtist(), trackDto.getSpotifyTrack(),
                trackDto.getUsersTracks(), trackDto.getComment());
    }

    public TrackDto mapToTrackDto (final Track track) {
        return new TrackDto(track.getId(), track.getTitle(), track.getCount(), track.getArtist(), track.getSpotifyTrack(),
                track.getUsersTracks(), track.getComments());
    }

    public List<TrackDto> mapToTrackDtoList (final List<Track> tracks) {
        return tracks.stream()
                .map(t-> new TrackDto(t.getId(), t.getTitle(), t.getCount(), t.getArtist(), t.getSpotifyTrack(),
                        t.getUsersTracks(), t.getComments()))
                .collect(Collectors.toList());
    }
}
