package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.UsersArtist;
import com.crud.scrobbler_backend.domain.UsersArtistDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersArtistsMapper {
    public UsersArtist mapToUsersArtist (final UsersArtistDto usersArtistDto) {
        return new UsersArtist(usersArtistDto.getLastPLayedTime(), usersArtistDto.getCount(), usersArtistDto.getUser(),
                usersArtistDto.getArtist());
    }

    public UsersArtistDto mapToUsersArtistDto (final UsersArtist usersArtist) {
        return new UsersArtistDto(usersArtist.getId(), usersArtist.getLastPLayedTime(), usersArtist.getCount(),
                usersArtist.getUser(), usersArtist.getArtist());
    }

    public List<UsersArtistDto> mapToUsersArtistDtoList (final List<UsersArtist> usersArtists) {
        return usersArtists.stream()
                .map(usersArtist -> new UsersArtistDto(usersArtist.getId(), usersArtist.getLastPLayedTime(),
                        usersArtist.getCount(), usersArtist.getUser(), usersArtist.getArtist()))
                .collect(Collectors.toList());
    }
}