package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.ArtistDto;
import com.crud.scrobbler_backend.mapper.ArtistMapper;
import com.crud.scrobbler_backend.services.ArtistsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistsController.class)
class ArtistsControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArtistsService service;
    @MockBean
    private ArtistMapper mapper;

    @Test
    void shouldGetArtist() throws Exception {
        //Given
        ArtistDto artist = new ArtistDto(123L, "Test name", "Test id", new ArrayList<>(), new ArrayList<>());
        long id = artist.getId();
        when(mapper.mapToArtistDto(service.getArtistById(id))).thenReturn(artist);

        //When & Then
        mockMvc.perform(get("/v1/artists/Test%20name").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("artistName", "Test name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test name")))
                .andExpect(jsonPath("$.spotifyArtistId", is("Test id")))
                .andExpect(jsonPath("$.usersArtists", hasSize(0)))
                .andExpect(jsonPath("$.tracks", hasSize(0)));
    }
}