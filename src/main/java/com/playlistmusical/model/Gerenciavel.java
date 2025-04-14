package main.java.com.playlistmusical.model;

/**
 * Interface para entidades que gerenciam músicas.
 */
public interface Gerenciavel {
    void adicionarMusica(Musica musica);
    void removerMusica(Musica musica);
}