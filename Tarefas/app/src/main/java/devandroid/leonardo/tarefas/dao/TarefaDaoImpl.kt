package devandroid.leonardo.tarefas.dao

import devandroid.leonardo.tarefas.model.Tarefa

class TarefaDaoImpl : TarefaDao {
    companion object{
        private val tarefas = mutableListOf<Tarefa>()
    }
    override fun adicionarTarefa(tarefa: Tarefa) {
        Companion.tarefas.add(tarefa)
    }

    override fun obterTarefas(): List<Tarefa> {
        return tarefas
    }

    override fun atualizarTarefa(tarefa: Tarefa) {
        if(tarefa.check == false){
            tarefa.check = true
        }
    }
}