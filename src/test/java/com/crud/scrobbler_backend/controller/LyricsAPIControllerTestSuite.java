package com.crud.scrobbler_backend.controller;

import com.crud.scrobbler_backend.domain.Artist;
import com.crud.scrobbler_backend.domain.lyricsApi.LyricsDto;
import com.crud.scrobbler_backend.domain.Track;
import com.crud.scrobbler_backend.services.LyricsApiService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LyricsAPIController.class)
class LyricsAPIControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LyricsApiService service;

    @Test
    void shouldGetLyrics() throws Exception {
        //Given
        Artist artist = new Artist("Test name", "Test spotify id");
        Track track = new Track("Test tile", artist);
        LyricsDto lyricsDto = new LyricsDto("Test lyrics");
        when(service.getLyrics(artist.getName(), track.getTitle())).thenReturn(lyricsDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(lyricsDto);

        //When & Then
        mockMvc.perform(get("/v1/lyrics/Test name/Test title").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("artistName", artist.getName())
                .param("title", track.getTitle())
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}