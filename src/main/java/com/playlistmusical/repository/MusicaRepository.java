package main.java.com.playlistmusical.repository;

import com.playlistmusical.model.Musica;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações com a entidade Musica.
 */
@Repository
public interface MusicaRepository extends RepositorioBase<Musica, Long> {
}