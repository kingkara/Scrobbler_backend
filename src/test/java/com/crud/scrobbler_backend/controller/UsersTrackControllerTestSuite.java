package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.*;
import com.crud.scrobbler_backend.mapper.UsersTracksMapper;
import com.crud.scrobbler_backend.services.UsersTracksService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersTrackController.class)
class UsersTrackControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsersTracksMapper mapper;
    @MockBean
    private UsersTracksService service;

    @Test
    void shouldGetUsersTracks() throws Exception {
        //Given
        User user = new User(123L, "Test name", "Test email", "Test spotify id",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        long id = user.getId();
        Artist artist = new Artist("Test artist", "Test artist id");
        Track track = new Track("Test title", artist);
        Track track2 = new Track("Test title 2", artist);

        UsersTrackDto usersTrackDto = new UsersTrackDto(user, track);
        UsersTrackDto usersTrackDto2 = new UsersTrackDto(user, track2);
        List<UsersTrackDto> usersTrackDtos = new ArrayList<>();
        usersTrackDtos.add(usersTrackDto);
        usersTrackDtos.add(usersTrackDto2);

        when(mapper.mapToUsersTrackDtoList(service.getAllUsersTracks(id))).thenReturn(usersTrackDtos);

        //When & Then
        mockMvc.perform(get("/v1/usersTracks/123").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("userId", Long.toString(id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].user.username", is("Test name")))
                .andExpect(jsonPath("$[0].user.email", is("Test email")))
                .andExpect(jsonPath("$[0].user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("$[0].track.artist.name", is("Test artist")))
                .andExpect(jsonPath("$[0].track.artist.spotifyArtistId", is("Test artist id")))
                .andExpect(jsonPath("$[0].track.title", is("Test title")))
                .andExpect(jsonPath("$[1].user", is(1)))
                .andExpect(jsonPath("$[1].track.artist", is(3)))
                .andExpect(jsonPath("$[1].track.title", is("Test title 2")));
    }

    @Test
    void shouldGetFavouriteTracks() throws Exception {
        //Given
        User user = new User(123L, "Test name", "Test email", "Test spotify id",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        long id = user.getId();
        Artist artist = new Artist("Test artist", "Test artist id");
        Track track = new Track("Test title", artist);
        Track track2 = new Track("Test title 2", artist);

        UsersTrackDto usersTrackDto = new UsersTrackDto(user, track);
        usersTrackDto.setFavouriteStatus(true);
        UsersTrackDto usersTrackDto2 = new UsersTrackDto(user, track2);
        usersTrackDto2.setFavouriteStatus(true);
        List<UsersTrackDto> usersTrackDtos = new ArrayList<>();
        usersTrackDtos.add(usersTrackDto);
        usersTrackDtos.add(usersTrackDto2);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(id);

        when(mapper.mapToUsersTrackDtoList(service.getAllUsersTracks(id))).thenReturn(usersTrackDtos);

        //When & Then
        mockMvc.perform(get("/v1/usersTracks").contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].user.username", is("Test name")))
                .andExpect(jsonPath("$[0].user.email", is("Test email")))
                .andExpect(jsonPath("$[0].user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("$[0].track.artist.name", is("Test artist")))
                .andExpect(jsonPath("$[0].track.artist.spotifyArtistId", is("Test artist id")))
                .andExpect(jsonPath("$[0].track.title", is("Test title")))
                .andExpect(jsonPath("$[0].favouriteStatus", is(true)))
                .andExpect(jsonPath("$[1].user", is(1)))
                .andExpect(jsonPath("$[1].track.artist", is(3)))
                .andExpect(jsonPath("$[1].track.title", is("Test title 2")))
                .andExpect(jsonPath("$[1].favouriteStatus", is(true)));
    }

    @Test
    void shouldChangeFavouriteStatus() throws Exception {
        //Given
        User user = new User(123L, "Test name", "Test email", "Test spotify id",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        long userId = user.getId();
        Artist artist = new Artist("Test artist", "Test artist id");
        Track track = new Track(123L, "Test title", artist, new ArrayList<>(), new ArrayList<>());
        long trackId = track.getId();

        UsersTrackDto usersTrackDto = new UsersTrackDto(user, track);
        usersTrackDto.setFavouriteStatus(true);

        when(mapper.mapToUsersTrackDto(service.changeFavouriteStatus(userId, trackId))).thenReturn(usersTrackDto);

        //When & Then
        mockMvc.perform(put("/v1/usersTracks/123/123").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("userId", Long.toString(userId))
                .param("trackId", Long.toString(trackId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("user.username", is("Test name")))
                .andExpect(jsonPath("user.email", is("Test email")))
                .andExpect(jsonPath("user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("track.artist.name", is("Test artist")))
                .andExpect(jsonPath("track.artist.spotifyArtistId", is("Test artist id")))
                .andExpect(jsonPath("track.title", is("Test title")))
                .andExpect(jsonPath("favouriteStatus", is(true)));
    }

    @Test
    void shouldGetTopUsersTracks() throws Exception {
        //Given
        User user = new User(123L, "Test name", "Test email", "Test spotify id",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        long id = user.getId();
        Artist artist = new Artist("Test artist", "Test artist id");
        Track track = new Track("Test title", artist);
        Track track2 = new Track("Test title 2", artist);
        Track track3 = new Track("Test title 3", artist);
        Track track4 = new Track("Test title 4", artist);
        Track track5 = new Track("Test title 5", artist);

        UsersTrackDto usersTrackDto = new UsersTrackDto(user, track);
        usersTrackDto.setCount(25);
        UsersTrackDto usersTrackDto2 = new UsersTrackDto(user, track2);
        usersTrackDto2.setCount(20);
        UsersTrackDto usersTrackDto3 = new UsersTrackDto(user, track3);
        usersTrackDto3.setCount(15);
        UsersTrackDto usersTrackDto4 = new UsersTrackDto(user, track4);
        usersTrackDto4.setCount(10);
        UsersTrackDto usersTrackDto5 = new UsersTrackDto(user, track5);
        usersTrackDto5.setCount(5);
        List<UsersTrackDto> usersTrackDtos = new ArrayList<>();
        usersTrackDtos.add(usersTrackDto);
        usersTrackDtos.add(usersTrackDto2);
        usersTrackDtos.add(usersTrackDto3);
        usersTrackDtos.add(usersTrackDto4);
        usersTrackDtos.add(usersTrackDto5);

        when(mapper.mapToUsersTrackDtoList(service.getTopTracks(id))).thenReturn(usersTrackDtos);

        //When & Then
        mockMvc.perform(get("/v1/usersTracks/top/123").contentType(MediaType.APPLICATION_JSON)
                .param("userId", Long.toString(id))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].user.username", is("Test name")))
                .andExpect(jsonPath("$[0].user.email", is("Test email")))
                .andExpect(jsonPath("$[0].user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("$[0].track.artist.name", is("Test artist")))
                .andExpect(jsonPath("$[0].track.artist.spotifyArtistId", is("Test artist id")))
                .andExpect(jsonPath("$[0].track.title", is("Test title")))
                .andExpect(jsonPath("$[0].count", is(25)))
                .andExpect(jsonPath("$[1].user", is(1)))
                .andExpect(jsonPath("$[1].track.artist", is(3)))
                .andExpect(jsonPath("$[1].track.title", is("Test title 2")))
                .andExpect(jsonPath("$[1].count", is(20)))
                .andExpect(jsonPath("$[2].user", is(1)))
                .andExpect(jsonPath("$[2].track.artist", is(3)))
                .andExpect(jsonPath("$[2].track.title", is("Test title 3")))
                .andExpect(jsonPath("$[2].count", is(15)))
                .andExpect(jsonPath("$[3].user", is(1)))
                .andExpect(jsonPath("$[3].track.artist", is(3)))
                .andExpect(jsonPath("$[3].track.title", is("Test title 4")))
                .andExpect(jsonPath("$[3].count", is(10)))
                .andExpect(jsonPath("$[4].user", is(1)))
                .andExpect(jsonPath("$[4].track.artist", is(3)))
                .andExpect(jsonPath("$[4].track.title", is("Test title 5")))
                .andExpect(jsonPath("$[4].count", is(5)));
    }

    @Test
    void shouldDeleteTrack() throws Exception {
        //Given
        User user = new User(123L, "Test name", "Test email", "Test spotify id",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        long userId = user.getId();
        Artist artist = new Artist("Test artist", "Test artist id");
        Track track = new Track(123L, "Test title", artist, new ArrayList<>(), new ArrayList<>());
        long trackId = track.getId();

        UsersTrack usersTrack = new UsersTrack(user, track);

        when(service.getByUserAndTrackId(userId, trackId)).thenReturn(usersTrack);
        when(service.addUsersTrack(mapper.mapToUsersTrack(ArgumentMatchers.any(UsersTrackDto.class)))).thenReturn(usersTrack);

        //When & Then
        mockMvc.perform(delete("/v1/usersTracks/123/123").contentType(MediaType.APPLICATION_JSON)
                .param("userId", Long.toString(userId))
                .param("trackId", Long.toString(trackId))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
        verify(service).deleteUsersTrack(userId, trackId);
    }
}