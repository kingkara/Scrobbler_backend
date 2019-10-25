package com.crud.scrobbler_backend.mapper;

import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.domain.UserDto;
import com.crud.scrobbler_backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser (final UserDto userDto) {
        return new User(userDto.getUsername(), userDto.getEmail(), userDto.getSpotifyId(), userDto.getUserArtistId(), userDto.getUserTrackId(),
                userDto.getComments());
    }

    public UserDto mapToUserDto (final User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getSpotifyId(), user.getArtists(), user.getTracks(),
                user.getComments());
    }

    public List<UserDto> mapToUserDtoList (final List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getSpotifyId(), user.getArtists(),
                        user.getTracks(), user.getComments()))
                .collect(Collectors.toList());
    }
}
