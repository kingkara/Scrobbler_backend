package com.crud.scrobbler_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Artist not found!")
public class ArtistNotFoundException extends Exception {
}
