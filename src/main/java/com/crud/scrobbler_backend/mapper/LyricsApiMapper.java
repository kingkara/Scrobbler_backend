package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Lyrics;
import com.crud.scrobbler_backend.domain.LyricsDto;
import org.springframework.stereotype.Component;

@Component
public class LyricsApiMapper {

    public Lyrics mapToLyrics (final LyricsDto lyricsDto) {
        return new Lyrics(lyricsDto.getLyrics());
    }

    public LyricsDto mapToLyricsDto (final Lyrics lyrics) {
        return new LyricsDto(lyrics.getLyrics());
    }
}
