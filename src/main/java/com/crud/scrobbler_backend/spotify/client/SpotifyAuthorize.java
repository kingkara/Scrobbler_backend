package com.crud.scrobbler_backend.spotify.client;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;

import java.io.IOException;

public class SpotifyAuthorize {
    private static final String clientId = "9ef734f0814d492689a03de00a67523e";
    private static final String clientSecret = "903ee87339a3479dac8f1b08f1251c76";
    private static final String refreshToken = "AQBZNR8HFduQkg0MWj6JebexUc2P9NrKLA5gXDDEkAw8aPO4McKaLlINL_a3SqqYpevFH437X1cg3qnPhcf1RJzoWG_YYMLt3j5RoBffcko5t9M2oShggm90PsGq8q_wKrdc_g";


    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRefreshToken(refreshToken)
            .build();

    private static final AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
            .build();

    static String getTokenFromSpotifyServer() {
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return spotifyApi.getAccessToken();
    }
}
