package com.example.demo.service;

import com.example.demo.model.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepo;

    @Autowired
    private SongsRepository songRepo;

    public Iterable<Playlist> getAllPlaylists() {
        return playlistRepo.findAll();
    }

    public Playlist getPlaylistById(BigInteger playlistId) {
        return getPlaylist(playlistId);

    }

    public Optional<Playlist> createPlaylist(String name) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setCreatedOn(new Date());
        return Optional.of(playlistRepo.save(playlist));
    }
}
