package com.crud.scrobbler_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Users track not found!")
public class UsersTrackNotFoundException extends Exception {
}
