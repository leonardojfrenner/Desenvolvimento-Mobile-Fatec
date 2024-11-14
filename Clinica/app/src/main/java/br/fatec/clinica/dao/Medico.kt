package br.fatec.clinica.dao

data class Medico(
    val nome: String,
    val email: String,
    val senha: String,
    val especialidade: String,
    val crm: String // Número de registro do Conselho Regional de Medicina
)
