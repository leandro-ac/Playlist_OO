package main.java.com.playlistmusical.model;

import jakarta.persistence.Entity;

/**
 * Representa uma música que pode ser adicionada a playlists.
 */
@Entity
public class Musica extends Entidade {
    private String titulo;
    private String cantor;
    private GeneroMusical genero;

    // Construtores
    public Musica() {}

    public Musica(String titulo, String cantor, GeneroMusical genero) {
        this.titulo = titulo;
        this.cantor = cantor;
        this.genero = genero;
    }

    // Getters e Setters (Encapsulamento)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCantor() {
        return cantor;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusical genero) {
        this.genero = genero;
    }
}