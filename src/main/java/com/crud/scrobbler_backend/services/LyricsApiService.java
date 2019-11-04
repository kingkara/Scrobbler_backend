package com.crud.scrobbler_backend.services;

import com.crud.scrobbler_backend.domain.lyricsApi.LyricsDto;
import com.crud.scrobbler_backend.lyrics.client.LyricsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LyricsApiService {
    @Autowired
    private LyricsClient client;

    public LyricsDto getLyrics(String artistName, String title) throws Exception {
        return client.getTrackLyrics(artistName, title);
    }
}
