package devandroid.leonardo.calcularimc.dao

import devandroid.leonardo.calcularimc.model.Pessoa

class PessoaDao {
    companion object{
    private var pessoa:Pessoa?=null
    }

    fun cadastrarPessoa(pessoa: Pessoa){
        Companion.pessoa = pessoa
    }

    fun calcularIMC(): Double {
        val peso = pessoa?.peso?.toDoubleOrNull()
        val altura = pessoa?.altura?.toDoubleOrNull()
        return if (peso != null && altura != null && altura > 0) {
            peso / (altura * altura)
        } else {
            0.0
        }
    }

    }
