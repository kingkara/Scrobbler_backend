package com.crud.scrobbler_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "Track not found!")
public class TrackNotFoundException extends Exception {
}
