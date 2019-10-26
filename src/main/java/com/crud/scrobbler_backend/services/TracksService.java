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

    public void addTrack(final Track track) {
        repository.save(track);
    }

    public void deleteTrack(final long id) throws TrackNotFoundException {
        Track trackToDelete = repository.findById(id).orElseThrow(TrackNotFoundException::new);
        repository.delete(trackToDelete);
    }

    public Track updateTrack(final Track track) throws TrackNotFoundException {
        Track trackToUpdate = repository.findById(track.getId()).orElseThrow(TrackNotFoundException::new);
        trackToUpdate.setArtist(track.getArtist());
        trackToUpdate.setTitle(track.getTitle());
        return repository.save(trackToUpdate);
    }

    public Track getTrack(final long id) throws TrackNotFoundException {
        return repository.findById(id).orElseThrow(TrackNotFoundException::new);
    }

    public List<Track> getTracks() {
        return repository.findAll();
    }
    public List<Track> getTopTracks () {
        return repository.findAllOrOrderByCount();
    }

}
