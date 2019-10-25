package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.ArtistDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArtistMapper {

    public Artist mapToArtist(final ArtistDto artistDto) {
        return new Artist(artistDto.getName(), artistDto.getCount(), artistDto.getSpotifyId(), artistDto.getUsers(), artistDto.getTracks());
    }

    public ArtistDto mapToArtistDto(final Artist artist) {
        return new ArtistDto(artist.getArtistId(), artist.getName(), artist.getCount(), artist.getSpotifyId(), artist.getUsers(), artist.getTracks());
    }

    public List<ArtistDto> mapToArtistDtoList(final List<Artist> artists) {
        return artists.stream()
                .map(a -> new ArtistDto(a.getArtistId(), a.getName(), a.getCount(),
                        a.getSpotifyId(), a.getUsers(), a.getTracks()))
                .collect(Collectors.toList());
    }
}
