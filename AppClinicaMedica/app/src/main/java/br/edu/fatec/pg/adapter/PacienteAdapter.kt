package br.edu.fatec.pg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatec.pg.R
import br.edu.fatec.pg.dao.PacienteDao

class PacienteAdapter(private val pacientes: List<PacienteDao>) : RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_consulta, parent, false)
        return PacienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val paciente = pacientes[position]

        holder.txtNomePaciente.text = paciente.nome
        holder.txtEmailPaciente.text = paciente.email
        holder.txtDataHora.text = "${paciente.data} - ${paciente.horario}"
    }

    override fun getItemCount(): Int = pacientes.size

    inner class PacienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNomePaciente: TextView = itemView.findViewById(R.id.txtNomePaciente)
        val txtEmailPaciente: TextView = itemView.findViewById(R.id.txtEmailPaciente)
        val txtDataHora: TextView = itemView.findViewById(R.id.txtDataHora)
    }
}
