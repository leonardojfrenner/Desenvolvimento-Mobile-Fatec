package devandroid.leonardo.tarefas.dao

import devandroid.leonardo.tarefas.model.Tarefa

interface TarefaDao {
    fun adicionarTarefa(tarefa:Tarefa)
    fun obterTarefas():List<Tarefa>
    fun atualizarTarefa(tarefa:Tarefa)
}