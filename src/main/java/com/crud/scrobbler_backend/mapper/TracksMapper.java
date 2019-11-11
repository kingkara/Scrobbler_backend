package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.domain.TrackDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TracksMapper {

    public TrackDto mapToTrackDto(final Track track) {
        return new TrackDto(track.getId(), track.getTitle(), track.getArtist(), track.getUsersTracks(),
                track.getComments(), track.getArtist().getName());
    }

    public List<TrackDto> mapToTrackDtoList(final List<Track> tracks) {
        return tracks.stream()
                .map(track -> new TrackDto(track.getId(), track.getTitle(), track.getArtist(), track.getUsersTracks(),
                        track.getComments(), track.getArtist().getName()))
                .collect(Collectors.toList());
    }
}
