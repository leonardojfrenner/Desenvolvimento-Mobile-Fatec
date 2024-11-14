package br.fatec.clinica.dao

data class Consulta( val pacienteId: String,
                     val data: String,
                     val hora: String,
                     val descricao: String? = null)