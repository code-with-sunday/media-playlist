package com.example.demo.exception;

import java.math.BigInteger;

@SuppressWarnings("serial")
public class PlaylistNotFoundException extends RuntimeException{

    public PlaylistNotFoundException(final BigInteger id) {
        super(String.format("playlist with '%s' notfound", id));
    }
}
