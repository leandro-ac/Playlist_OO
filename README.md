## Diagrama de Classes

1. **Entidade (Classe Abstrata)**  
   - **Descrição**: Classe base para entidades com ID único.  
   - **Atributos**:  
     - id: Long (anotado com @Id, @GeneratedValue(strategy = GenerationType.IDENTITY))  
   - **Métodos**:  
     - getId(): Long  
     - setId(Long id)  
   - **Relações**:  
     - Superclasse de `GeneroMusical`, `Musica` e `Playlist`.

2. **GeneroMusical (Classe)**  
   - **Descrição**: Representa um gênero musical.  
   - **Herança**: Estende `Entidade`.  
   - **Atributos**:  
     - nome: String (anotado com @Column(nullable = false, unique = true))  
   - **Métodos**:  
     - Construtores: GeneroMusical(), GeneroMusical(String nome)  
     - getNome(): String  
     - setNome(String nome)  
   - **Relações**:  
     - Relacionado a `Musica` (um gênero pode estar associado a várias músicas).

3. **Gerenciavel (Interface)**  
   - **Descrição**: Define comportamento para entidades gerenciáveis.  
   - **Métodos**:  
     - getDescricao(): String  
   - **Relações**:  
     - Implementada por `Musica` e `Playlist`.

4. **Musica (Classe)**  
   - **Descrição**: Representa uma música com título, artista e gênero.  
   - **Herança**: Estende `Entidade`.  
   - **Implementa**: `Gerenciavel`.  
   - **Atributos**:  
     - titulo: String (anotado com @Column(nullable = false))  
     - artista: String (anotado com @Column(nullable = false))  
     - genero: GeneroMusical (anotado com @ManyToOne)  
   - **Métodos**:  
     - Construtores: Musica(), Musica(String titulo, String artista, GeneroMusical genero)  
     - getTitulo(): String  
     - setTitulo(String titulo)  
     - getArtista(): String  
     - setArtista(String artista)  
     - getGenero(): GeneroMusical  
     - setGenero(GeneroMusical genero)  
     - getDescricao(): String (retorna "titulo - artista (nome do gênero ou 'Sem gênero')")  
   - **Relações**:  
     - Relacionada a `GeneroMusical` (muitas músicas para um gênero).  
     - Relacionada a `Playlist` (uma música pode estar em várias playlists).

5. **Playlist (Classe)**  
   - **Descrição**: Representa uma playlist de músicas.  
   - **Herança**: Estende `Entidade`.  
   - **Implementa**: `Gerenciavel`.  
   - **Atributos**:  
     - nome: String (anotado com @Column(nullable = false))  
     - musicas: List<Musica> (anotado com @ManyToMany, inicializado como ArrayList)  
   - **Métodos**:  
     - Construtores: Playlist(), Playlist(String nome)  
     - getNome(): String  
     - setNome(String nome)  
     - getMusicas(): List<Musica>  
     - setMusicas(List<Musica> musicas)  
     - adicionarMusica(Musica musica)  
     - removerMusica(Musica musica)  
     - getDescricao(): String (retorna "nome (quantidade de músicas)")  
   - **Relações**:  
     - Relacionada a `Musica` (muitas músicas em muitas playlists).

6. **PlaylistMusical (Classe - Controlador REST)**  
   - **Descrição**: Controlador REST para gerenciar playlists (simulado, sem persistência JPA).  
   - **Atributos**:  
     - playlists: List<Playlist> (inicializado como ArrayList)  
   - **Métodos**:  
     - criarPlaylist(@RequestBody Playlist playlist): ResponseEntity<Playlist> (POST /api/playlists)  
     - listarPlaylists(): ResponseEntity<List<Playlist>> (GET /api/playlists)  
     - buscarPlaylist(@PathVariable Long id): ResponseEntity<Playlist> (GET /api/playlists/{id})  
     - adicionarMusica(@PathVariable Long id, @RequestBody Musica musica): ResponseEntity<Playlist> (POST /api/playlists/{id}/musicas)  
     - removerMusica(@PathVariable Long id, @PathVariable Long musicaId): ResponseEntity<Playlist> (DELETE /api/playlists/{id}/musicas/{musicaId})  
   - **Anotações**:  
     - @RestController  
     - @RequestMapping("/api/playlists")  
   - **Relações**:  
     - Manipula instâncias de `Playlist` e `Musica`.

### Resumo das Relações

- **Herança**:
  - `Entidade` → `GeneroMusical`, `Musica`, `Playlist`.
- **Implementação**:
  - `Gerenciavel` → `Musica`, `Playlist`.
- **Associações**:
  - `Musica` → `GeneroMusical` (Many-to-One): Uma música tem um gênero.
  - `Playlist` ↔ `Musica` (Many-to-Many): Uma playlist contém várias músicas, e uma música pode estar em várias playlists.
- **Controlador**:
  - `PlaylistMusical` gerencia operações CRUD sobre `Playlist` e `Musica`.