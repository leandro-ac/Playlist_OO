package main.java.com.playlistmusical.service;

import com.playlistmusical.exception.ResourceNotFoundException;
import com.playlistmusical.model.Musica;
import com.playlistmusical.model.Playlist;
import com.playlistmusical.repository.MusicaRepository;
import com.playlistmusical.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço para gerenciamento de playlists e músicas.
 */
@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private MusicaRepository musicaRepository;

    /**
     * Busca uma playlist por ID.
     * @param id ID da playlist.
     * @return Playlist encontrada.
     * @throws ResourceNotFoundException se a playlist não existir.
     */
    public Playlist buscarPorId(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist não encontrada: " + id));
    }

    /**
     * Busca uma música por ID.
     * @param id ID da música.
     * @return Música encontrada.
     * @throws ResourceNotFoundException se a música não existir.
     */
    public Musica buscarMusicaPorId(Long id) {
        return musicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Música não encontrada: " + id));
    }

    /**
     * Adiciona uma música a uma playlist.
     * @param playlistId ID da playlist.
     * @param musicaId ID da música.
     * @return Playlist atualizada.
     */
    public Playlist adicionarMusica(Long playlistId, Long musicaId) {
        Playlist playlist = buscarPorId(playlistId);
        Musica musica = buscarMusicaPorId(musicaId);
        playlist.adicionarMusica(musica);
        return playlistRepository.save(playlist);
    }

    /**
     * Edita uma música existente.
     * @param musicaId ID da música.
     * @param musicaAtualizada Dados atualizados da música.
     * @return Música atualizada.
     */
    public Musica editarMusica(Long musicaId, Musica musicaAtualizada) {
        Musica musica = buscarMusicaPorId(musicaId);
        musica.setTitulo(musicaAtualizada.getTitulo());
        musica.setCantor(musicaAtualizada.getCantor());
        musica.setGenero(musicaAtualizada.getGenero());
        return musicaRepository.save(musica);
    }

    /**
     * Remove uma música de uma playlist.
     * @param playlistId ID da playlist.
     * @param musicaId ID da música.
     * @return Playlist atualizada.
     */
    public Playlist removerMusica(Long playlistId, Long musicaId) {
        Playlist playlist = buscarPorId(playlistId);
        Musica musica = buscarMusicaPorId(musicaId);
        playlist.removerMusica(musica);
        return playlistRepository.save(playlist);
    }

    /**
     * Cria uma nova playlist.
     * @param playlist Dados da playlist.
     * @return Playlist criada.
     */
    public Playlist criarPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    /**
     * Cria uma nova música.
     * @param musica Dados da música.
     * @return Música criada.
     */
    public Musica criarMusica(Musica musica) {
        return musicaRepository.save(musica);
    }
}