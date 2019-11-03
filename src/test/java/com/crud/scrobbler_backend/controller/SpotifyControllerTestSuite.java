package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.spotify.SpotifyCurrentlyPlayedDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyFullTrackDto;
import com.crud.scrobbler_backend.domain.spotify.SpotifyTrackDto;
import com.crud.scrobbler_backend.services.SpotifyService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SpotifyController.class)
class SpotifyControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SpotifyService spotifyService;

    @Test
    void shouldGetPlayback() throws Exception {
        //When
        SpotifyFullTrackDto spotifyFullTrackDto = new SpotifyFullTrackDto(
                new SpotifyTrackDto(), "Test played");
        SpotifyFullTrackDto spotifyFullTrackDto2 = new SpotifyFullTrackDto(
                new SpotifyTrackDto(), "Test played 2");
        List<SpotifyFullTrackDto> fullTrackDtos = new ArrayList<>();
        fullTrackDtos.add(spotifyFullTrackDto);
        fullTrackDtos.add(spotifyFullTrackDto2);
        when(spotifyService.getPlayback()).thenReturn(fullTrackDtos);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(fullTrackDtos);

        //When & Then
        mockMvc.perform(get("/v1/spotify/playback").contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetCurrentPlaying() throws Exception {
        //When
        SpotifyCurrentlyPlayedDto spotifyCurrentlyPlayedDto = new SpotifyCurrentlyPlayedDto("Test id", "Test title", new ArrayList<>());
        when(spotifyService.getCurrentPlaying()).thenReturn(spotifyCurrentlyPlayedDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(spotifyCurrentlyPlayedDto);

        //When & Then
        mockMvc.perform(get("/v1/spotify/current").contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}