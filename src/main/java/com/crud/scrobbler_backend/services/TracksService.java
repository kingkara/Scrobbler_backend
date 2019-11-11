package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.exceptions.TrackNotFoundException;
import com.crud.scrobbler_backend.repository.TracksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TracksService {
    @Autowired
    private TracksRepository repository;

    public Track addTrack(final Track track) {
        return repository.save(track);
    }

    public Track findByTitle(final String title) {
        return repository.findTrackByTitle(title);
    }

    public void deleteTrack(final long id) throws TrackNotFoundException {
        Track trackToDelete = repository.findById(id).orElseThrow(TrackNotFoundException::new);
        repository.delete(trackToDelete);
    }

    public Track getTrack(final long id) throws TrackNotFoundException {
        return repository.findById(id).orElseThrow(TrackNotFoundException::new);
    }

    public List<Track> getTracks() {
        return repository.findAll();
    }

    public List<Track> getTracksByArtistName(final String artistName) {
        return repository.findByArtist_Name(artistName);
    }
}
