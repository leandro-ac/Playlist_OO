package main.java.com.playlistmusical.repository;

import com.playlistmusical.model.Playlist;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações com a entidade Playlist.
 */
@Repository
public interface PlaylistRepository extends RepositorioBase<Playlist, Long> {
}