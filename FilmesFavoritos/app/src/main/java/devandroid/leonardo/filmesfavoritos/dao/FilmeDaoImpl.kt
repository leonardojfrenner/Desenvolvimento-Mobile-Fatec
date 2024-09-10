package devandroid.leonardo.filmesfavoritos.dao

import devandroid.leonardo.filmesfavoritos.model.Filme


class FilmeDaoImpl : FilmeDao {
    companion object {
    private val filmes = mutableListOf<Filme>()
    }

    override fun adicionarFilme(filme: Filme) {
        Companion.filmes.add(filme)
    }


    override fun obterFilme(): List<Filme> {
        return filmes
    }
}