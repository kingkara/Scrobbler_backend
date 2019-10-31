package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.domain.TrackDto;
import org.springframework.stereotype.Component;

@Component
public class TracksMapper {
    public TrackDto mapToTrackDto(final Track track) {
        return new TrackDto(track.getId(), track.getTitle(), track.getArtist(), track.getUsersTracks(), track.getComments());
    }

}
