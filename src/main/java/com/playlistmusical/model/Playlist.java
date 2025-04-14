package main.java.com.playlistmusical.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma playlist que contém músicas.
 */
@Entity
public class Playlist extends Entidade implements Gerenciavel {
    private String nome;
    @ManyToMany
    private List<Musica> musicas = new ArrayList<>();
    private static int totalPlaylists = 0;

    // Construtores
    public Playlist() {
        totalPlaylists++;
    }

    public Playlist(String nome) {
        this.nome = nome;
        totalPlaylists++;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    // Métodos da interface Gerenciavel (Sobrescrita)
    @Override
    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }

    // Sobrecarga
    public void adicionarMusica(List<Musica> novasMusicas) {
        this.musicas.addAll(novasMusicas);
    }

    @Override
    public void removerMusica(Musica musica) {
        this.musicas.remove(musica);
    }

    // Método estático
    public static int getTotalPlaylists() {
        return totalPlaylists;
    }
}