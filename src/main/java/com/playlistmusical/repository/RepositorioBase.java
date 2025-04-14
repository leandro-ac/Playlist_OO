package main.java.com.playlistmusical.repository;

import com.playlistmusical.model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Repositório genérico para entidades.
 */
@NoRepositoryBean
public interface RepositorioBase<T extends Entidade, ID> extends JpaRepository<T, ID> {
}