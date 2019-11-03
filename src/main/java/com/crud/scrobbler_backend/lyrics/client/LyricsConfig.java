package com.crud.scrobbler_backend.lyrics.client;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LyricsConfig {
    @Value("${lyrics.api.endpoint.prod}")
    private String lyricsApiEndpoint;

    @Value("$lyrics.api.token")
    private String lyricsToken;
}
