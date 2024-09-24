package br.edu.fatecpg.ui.dao

import br.edu.fatecpg.ui.model.Contato

class ContatoDaoImpl : ContatoDao {
    companion object {
        private val contatos = mutableListOf<Contato>()
    }

    override fun adicionarContato(contato: Contato) {
        Companion.contatos.add(contato)
    }

    override fun obterContatos(): List<Contato> {
        return contatos
    }

}