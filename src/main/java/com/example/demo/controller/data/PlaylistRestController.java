package com.example.demo.controller.data;

import com.example.demo.model.Playlist;
import com.example.demo.model.Song;
import com.example.demo.service.PlaylistService;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistRestController {
    public PlaylistService service;

    @Qualifier("name")
    @Autowired
    public void setService(PlaylistService service){
        this.service = service;
    }

    @GetMapping("/")
    public String root(){
        return "application is running!";
    }

    @GetMapping("/all")
    public Iterable<Playlist> getAllPlaylists() {
        return service.getAllPlaylists();
    }

    @GetMapping("/{id}")
    public Optional<Playlist> getPlaylistById(final @PathVariable("id") BigInteger playlistId) {
        return Optional.ofNullable(service.getPlaylistById(playlistId));
    }

    @PostMapping("/{name}")
    public Iterable<Playlist> createPlaylist (final @PathVariable String name){
        return service.createdPlaylist(name);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylist (final @PathVariable("id") BigInteger playlistId){
        service.deletePlaylist(playlistId);
    }

    @GetMapping("/{id}/songs")
    public Iterable<Song> getSongsInPlaylist (@PathVariable("id") BigInteger playlistId){
        return service.getSongs(playlistId);
    }

    @DeleteMapping("/{id}/songs")
    public void deleteAllSongsInPlaylist (final @PathVariable("id") BigInteger playlistId){
        service.deleteSongs(playlistId);
    }

    @PostMapping("/{id}/add")
    public Song addSongToPlaylist (final @PathVariable("id") BigInteger playlistId
            final @RequestBody Song song){
        return service.addSong(playlistId, song);
    }

    @GetMapping("/songs")
    public Iterable<Song> getAllSongs(){
        return service.getSongs(null);
    }

    @PutMapping("/songs/{id}/move")
    public boolean moveSongToDifferentPlaylist(@PathVariable("id") BigInteger songId,
                                               @RequestParam("to_playlist") BigInteger toPlaylistId) {
        return service.moveSong(songId, toPlaylistId);
    }

    @DeleteMapping("/{id}/songs/{song_id}")
    public void deleteFromPlaylist (final @PathVariable("id") BigInteger playlistId,
                                    final @PathVariable("song_id") BigInteger songId){
        service.deleteSong(playlistId, songId);
    }
}
