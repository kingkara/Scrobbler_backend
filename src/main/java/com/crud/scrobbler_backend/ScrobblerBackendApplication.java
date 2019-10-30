package com.crud.scrobbler_backend;

import com.crud.scrobbler_backend.spotify.client.SpotifyAuthorize;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrobblerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrobblerBackendApplication.class, args);
	}

}
