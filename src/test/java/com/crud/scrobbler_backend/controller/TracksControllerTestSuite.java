package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.TrackDto;
import com.crud.scrobbler_backend.mapper.TracksMapper;
import com.crud.scrobbler_backend.services.TracksService;
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
@WebMvcTest(TracksController.class)
class TracksControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TracksService service;
    @MockBean
    private TracksMapper mapper;

    @Test
    void shouldGetTrack() throws Exception {
        //Given
        Artist artist = new Artist("Test name", "Test id");
        TrackDto trackDto = new TrackDto(123L, "Test title", artist, new ArrayList<>(), new ArrayList<>());
        long id = trackDto.getId();

        when(mapper.mapToTrackDto(service.getTrack(id))).thenReturn(trackDto);

        //When & Then
        mockMvc.perform(get("/v1/tracks/123").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("trackId", Long.toString(id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.artist.name", is("Test name")))
                .andExpect(jsonPath("$.artist.spotifyArtistId", is("Test id")))
                .andExpect(jsonPath("$.usersTracks", hasSize(0)))
                .andExpect(jsonPath("$.comment", hasSize(0)));
    }
}