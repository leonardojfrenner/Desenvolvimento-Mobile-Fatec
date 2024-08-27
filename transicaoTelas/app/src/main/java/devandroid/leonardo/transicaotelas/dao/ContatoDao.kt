package devandroid.leonardo.transicaotelas.dao

import devandroid.leonardo.transicaotelas.model.Contato

class ContatoDao {
    companion object {
        private var contato: Contato? = null
    }

    fun cadastroContato(contato: Contato){
        Companion.contato = contato
    }

    fun exibirContato():Contato{
        return Companion.contato?:Contato()
    }
}