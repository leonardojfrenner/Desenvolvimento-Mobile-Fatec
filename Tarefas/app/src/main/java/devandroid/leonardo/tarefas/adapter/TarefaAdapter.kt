package devandroid.leonardo.tarefas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import devandroid.leonardo.tarefas.R
import devandroid.leonardo.tarefas.model.Tarefa

class TarefaAdapter(private val tarefas: List<Tarefa>) :
    RecyclerView.Adapter<TarefaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txvNome: TextView = itemView.findViewById(R.id.txv_nome)
        val txvDescricao: TextView = itemView.findViewById(R.id.txv_descricao)
        val chkConcluida: CheckBox = itemView.findViewById(R.id.chk_concluida)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarefa, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tarefas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarefa = tarefas[position]
        holder.txvNome.text = tarefa.nome
        holder.txvDescricao.text = tarefa.descricao
        holder.chkConcluida.isChecked = tarefa.check

        holder.chkConcluida.setOnCheckedChangeListener { _, isChecked ->
            tarefa.check = isChecked
        }
    }
}
