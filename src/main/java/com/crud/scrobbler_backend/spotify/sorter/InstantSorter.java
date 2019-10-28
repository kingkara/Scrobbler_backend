package com.crud.scrobbler_backend.spotify.sorter;

import com.crud.scrobbler_backend.domain.SpotifyTrackDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class InstantSorter implements Comparator <SpotifyTrackDto> {
    @Override
    public int compare(SpotifyTrackDto track1, SpotifyTrackDto track2) {
        return track2.getPlayedAt().compareTo(track1.getPlayedAt());
    }
}
