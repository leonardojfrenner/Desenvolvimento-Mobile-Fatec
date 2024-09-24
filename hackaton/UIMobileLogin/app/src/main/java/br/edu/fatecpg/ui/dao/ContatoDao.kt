package br.edu.fatecpg.ui.dao

import br.edu.fatecpg.ui.model.Contato

interface ContatoDao {
    fun adicionarContato(contato: Contato)
    fun obterContatos(): List<Contato>
}