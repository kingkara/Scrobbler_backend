package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.ArtistDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArtistMapper {

    public Artist mapToArtist(final ArtistDto artistDto) {
        return new Artist(artistDto.getName());
    }

    public ArtistDto mapToArtistDto(final Artist artist) {
        return new ArtistDto(artist.getArtistId(), artist.getName(), artist.getUsersArtist(), artist.getTracks());
    }

    public List<ArtistDto> mapToArtistDtoList(final List<Artist> artists) {
        return artists.stream()
                .map(a -> new ArtistDto(a.getArtistId(), a.getName(),a.getUsersArtist(), a.getTracks()))
                .collect(Collectors.toList());
    }
}
