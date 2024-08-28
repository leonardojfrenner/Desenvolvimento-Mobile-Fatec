package devandroid.leonardo.consomeapiviacep.dao

import devandroid.leonardo.consomeapiviacep.model.Endereco

class EnderecoDao {

    companion object {
        private var endereco: Endereco? = null

        fun cadastrarEndereco(novoEndereco: Endereco?) {
            endereco = novoEndereco
        }

        fun getEndereco(): Endereco? {
            return endereco
        }
    }
}
