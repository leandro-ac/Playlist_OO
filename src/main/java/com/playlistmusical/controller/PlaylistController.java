package com.playlistmusical.controller;

import com.playlistmusical.model.Musica;
import com.playlistmusical.model.Playlist;
import com.playlistmusical.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gerenciamento de playlists e músicas.
 */
@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    /**
     * Lista todas as músicas de uma playlist.
     * @param playlistId ID da playlist.
     * @return Lista de músicas.
     */
    @GetMapping("/{playlistId}/musicas")
    public ResponseEntity<List<Musica>> listarMusicas(@PathVariable Long playlistId) {
        Playlist playlist = playlistService.buscarPorId(playlistId);
        return ResponseEntity.ok(playlist.getMusicas());
    }

    /**
     * Cria uma nova playlist.
     * @param playlist Dados da playlist.
     * @return Playlist criada.
     */
    @PostMapping
    public ResponseEntity<Playlist> criarPlaylist(@RequestBody Playlist playlist) {
        return new ResponseEntity<>(playlistService.criarPlaylist(playlist), HttpStatus.CREATED);
    }

    /**
     * Adiciona uma música a uma playlist.
     * @param playlistId ID da playlist.
     * @param musicaId ID da música.
     * @return Playlist atualizada.
     */
    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<Playlist> adicionarMusica(
            @PathVariable Long playlistId, @PathVariable Long musicaId) {
        return ResponseEntity.ok(playlistService.adicionarMusica(playlistId, musicaId));
    }

    /**
     * Cria uma nova música.
     * @param musica Dados da música.
     * @return Música criada.
     */
    @PostMapping("/musicas")
    public ResponseEntity<Musica> criarMusica(@RequestBody Musica musica) {
        return new ResponseEntity<>(playlistService.criarMusica(musica), HttpStatus.CREATED);
    }

    /**
     * Edita uma música existente.
     * @param musicaId ID da música.
     * @param musica Dados atualizados.
     * @return Resposta vazia com status OK.
     */
    @PutMapping("/musicas/{musicaId}")
    public ResponseEntity<Void> editarMusica(
            @PathVariable Long musicaId, @RequestBody Musica musica) {
        playlistService.editarMusica(musicaId, musica);
        return ResponseEntity.ok().build();
    }

    /**
     * Atualiza parcialmente o título de uma música.
     * @param musicaId ID da música.
     * @param novoTitulo Novo título.
     * @return Música atualizada.
     */
    @PatchMapping("/musicas/{musicaId}/titulo")
    public ResponseEntity<Musica> atualizarTitulo(
            @PathVariable Long musicaId, @RequestBody String novoTitulo) {
        Musica musica = playlistService.buscarMusicaPorId(musicaId);
        musica.setTitulo(novoTitulo);
        playlistService.criarMusica(musica);
        return ResponseEntity.ok(musica);
    }

    /**
     * Remove uma música de uma playlist.
     * @param playlistId ID da playlist.
     * @param musicaId ID da música.
     * @return Playlist atualizada.
     */
    @DeleteMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<Playlist> removerMusica(
            @PathVariable Long playlistId, @PathVariable Long musicaId) {
        return ResponseEntity.ok(playlistService.removerMusica(playlistId, musicaId));
    }

    /**
     * Verifica a existência de uma playlist.
     * @param playlistId ID da playlist.
     * @return Resposta vazia com status OK.
     */
    @RequestMapping(method = RequestMethod.HEAD, value = "/{playlistId}")
    public ResponseEntity<Void> verificarPlaylist(@PathVariable Long playlistId) {
        playlistService.buscarPorId(playlistId);
        return ResponseEntity.ok().build();
    }

    /**
     * Lista os métodos HTTP permitidos.
     * @return Resposta com cabeçalho Allow.
     */
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> opcoesPermitidas() {
        return ResponseEntity.ok()
                .header("Allow", "GET,POST,PUT,PATCH,DELETE,HEAD,OPTIONS")
                .build();
    }
}