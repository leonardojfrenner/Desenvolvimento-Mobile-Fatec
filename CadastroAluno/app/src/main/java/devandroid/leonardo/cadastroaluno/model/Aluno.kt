package devandroid.leonardo.cadastroaluno.model

import kotlin.random.Random

class Aluno (
   private var _nome:String,
   private var _numeroMatricula:Int = gerarNumeroMatricula()
) {
    var nome: String
        get() = _nome
        set(value) {
            _nome = value
        }

    var numeroMarticula: Int
        get() = _numeroMatricula
        set(value) {
            _numeroMatricula = value
        }
    companion object {
        fun gerarNumeroMatricula(): Int {
            return Random.nextInt(100000, 999999)
        }
    }
}