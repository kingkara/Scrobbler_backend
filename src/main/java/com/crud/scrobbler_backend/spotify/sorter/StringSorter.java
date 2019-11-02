package com.crud.scrobbler_backend.spotify.sorter;

import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrack;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class StringSorter implements Comparator<SpotifyFullTrack> {
    @Override
    public int compare(SpotifyFullTrack track1, SpotifyFullTrack track2) {
        return track2.getPlayedAt().compareTo(track1.getPlayedAt());
    }
}
