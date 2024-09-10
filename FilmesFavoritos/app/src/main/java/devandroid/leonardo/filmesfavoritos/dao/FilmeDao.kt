package devandroid.leonardo.filmesfavoritos.dao

import devandroid.leonardo.filmesfavoritos.model.Filme

interface FilmeDao {
    fun adicionarFilme(filme : Filme)
    fun obterFilme():List<Filme>
}