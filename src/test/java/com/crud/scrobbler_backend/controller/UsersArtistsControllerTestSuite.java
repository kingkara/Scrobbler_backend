package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.User;
import com.crud.scrobbler_backend.domain.UsersArtist;
import com.crud.scrobbler_backend.domain.UsersArtistDto;
import com.crud.scrobbler_backend.mapper.UsersArtistsMapper;
import com.crud.scrobbler_backend.services.UsersArtistsService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersArtistsController.class)
class UsersArtistsControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsersArtistsMapper mapper;
    @MockBean
    private UsersArtistsService service;

    @Test
    void shouldGetUsersArtists() throws Exception {
        //Given
        User user = new User(123L, "Test name", "Test email", "Test spotify id",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        long id = user.getId();
        Artist artist = new Artist("Test artist", "Test artist id");
        Artist artist2 = new Artist("Test artist 2", "Test artist id 2");

        UsersArtistDto usersArtist = new UsersArtistDto(user, artist);
        UsersArtistDto usersArtist2 = new UsersArtistDto(user, artist2);
        List<UsersArtistDto> usersArtists = new ArrayList<>();
        usersArtists.add(usersArtist);
        usersArtists.add(usersArtist2);

        when(mapper.mapToUsersArtistDtoList(service.getAllArtists(id))).thenReturn(usersArtists);

        //When & Then
        mockMvc.perform(get("/v1/usersArtists/123").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("userId", Long.toString(id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].user.username", is("Test name")))
                .andExpect(jsonPath("$[0].user.email", is("Test email")))
                .andExpect(jsonPath("$[0].user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("$[0].artist.name", is("Test artist")))
                .andExpect(jsonPath("$[0].artist.spotifyArtistId", is("Test artist id")))
                .andExpect(jsonPath("$[1].user.username", is("Test name")))
                .andExpect(jsonPath("$[1].user.email", is("Test email")))
                .andExpect(jsonPath("$[1].user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("$[1].artist.name", is("Test artist 2")))
                .andExpect(jsonPath("$[1].artist.spotifyArtistId", is("Test artist id 2")));
    }

    @Test
    void shouldGetTopFive() throws Exception {
        //Given
        User user = new User(123L, "Test name", "Test email", "Test spotify id",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        long id = user.getId();
        Artist artist = new Artist("Test artist", "Test artist id");
        Artist artist2 = new Artist("Test artist 2", "Test artist id 2");
        Artist artist3 = new Artist("Test artist 3", "Test artist id 3");
        Artist artist4 = new Artist("Test artist 4", "Test artist id 4");
        Artist artist5 = new Artist("Test artist 5", "Test artist id 5");

        UsersArtistDto usersArtist = new UsersArtistDto(user, artist);
        usersArtist.setCount(25);
        UsersArtistDto usersArtist2 = new UsersArtistDto(user, artist2);
        usersArtist2.setCount(20);
        UsersArtistDto usersArtist3 = new UsersArtistDto(user, artist3);
        usersArtist3.setCount(15);
        UsersArtistDto usersArtist4 = new UsersArtistDto(user, artist4);
        usersArtist4.setCount(10);
        UsersArtistDto usersArtist5 = new UsersArtistDto(user, artist5);
        usersArtist5.setCount(5);
        List<UsersArtistDto> usersArtists = new ArrayList<>();
        usersArtists.add(usersArtist);
        usersArtists.add(usersArtist2);
        usersArtists.add(usersArtist3);
        usersArtists.add(usersArtist4);
        usersArtists.add(usersArtist5);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(id);

        when(mapper.mapToUsersArtistDtoList(service.getTopArtists(id))).thenReturn(usersArtists);

        //When & Then
        mockMvc.perform(get("/v1/usersArtists").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].user.username", is("Test name")))
                .andExpect(jsonPath("$[0].user.email", is("Test email")))
                .andExpect(jsonPath("$[0].user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("$[0].artist.name", is("Test artist")))
                .andExpect(jsonPath("$[0].artist.spotifyArtistId", is("Test artist id")))
                .andExpect(jsonPath("$[0].count", is(25)))
                .andExpect(jsonPath("$[1].user.username", is("Test name")))
                .andExpect(jsonPath("$[1].user.email", is("Test email")))
                .andExpect(jsonPath("$[1].user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("$[1].artist.name", is("Test artist 2")))
                .andExpect(jsonPath("$[1].artist.spotifyArtistId", is("Test artist id 2")))
                .andExpect(jsonPath("$[1].count", is(20)))
                .andExpect(jsonPath("$[2].user.username", is("Test name")))
                .andExpect(jsonPath("$[2].user.email", is("Test email")))
                .andExpect(jsonPath("$[2].user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("$[2].artist.name", is("Test artist 3")))
                .andExpect(jsonPath("$[2].artist.spotifyArtistId", is("Test artist id 3")))
                .andExpect(jsonPath("$[2].count", is(15)))
                .andExpect(jsonPath("$[3].user.username", is("Test name")))
                .andExpect(jsonPath("$[3].user.email", is("Test email")))
                .andExpect(jsonPath("$[3].user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("$[3].artist.name", is("Test artist 4")))
                .andExpect(jsonPath("$[3].artist.spotifyArtistId", is("Test artist id 4")))
                .andExpect(jsonPath("$[3].count", is(10)))
                .andExpect(jsonPath("$[4].user.username", is("Test name")))
                .andExpect(jsonPath("$[4].user.email", is("Test email")))
                .andExpect(jsonPath("$[4].user.spotifyId", is("Test spotify id")))
                .andExpect(jsonPath("$[4].artist.name", is("Test artist 5")))
                .andExpect(jsonPath("$[4].artist.spotifyArtistId", is("Test artist id 5")))
                .andExpect(jsonPath("$[4].count", is(5)));
    }

    @Test
    void shouldDeleteArtist() throws Exception {
        //Given
        User user = new User(123L, "Test name", "Test email", "Test spotify id",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Artist artist = new Artist(123L, "Test artist", "Test artist id", new ArrayList<>(), new ArrayList<>());

        UsersArtist usersArtist = new UsersArtist(user, artist);

        when(service.getByUserAndArtistsId(user.getId(), artist.getArtistId())).thenReturn(usersArtist);
        when(service.addUsersArtist(mapper.mapToUsersArtist(ArgumentMatchers.any(UsersArtistDto.class)))).thenReturn(usersArtist);

        //When & Then
        mockMvc.perform(delete("/v1/usersArtists/123/123").contentType(MediaType.APPLICATION_JSON)
                .param("usersId", Long.toString(user.getId()))
                .param("artistId", Long.toString(artist.getArtistId()))
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
        verify(service).deleteUsersArtist(artist.getArtistId(), user.getId());
    }
}