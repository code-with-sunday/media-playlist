package com.example.demo.database;

import com.example.demo.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

public interface SongsRepository extends JpaRepository<Song, BigInteger> {

    public Optional<Song> findName(String name);

    Collection<Song> findByPlaylistId(BigInteger playlistId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Song s where s.playlistId = ?1")
    void deleteByPlaylistId(BigInteger playlistId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Song s where s.playlistId = ?1 and s.id = ?2")
    public void delete(BigInteger playlistId, BigInteger songId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Song s set s.playlistId = ?2 where s.id = ?1")
    public int updatePlaylist(BigInteger songId, BigInteger playlistId);


}
