package br.edu.fatec.pg.dao

data class MedicoDao(
    var nome: String,
    var email: String,
    var senha: String,
    var medico: Boolean = false
)