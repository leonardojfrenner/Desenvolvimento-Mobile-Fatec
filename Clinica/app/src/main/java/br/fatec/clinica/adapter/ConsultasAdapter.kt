package br.fatec.clinica.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.fatec.clinica.dao.Consulta
import br.fatec.clinica.databinding.ItemConsultaBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ConsultasAdapter(private val consultas: List<Consulta>) : RecyclerView.Adapter<ConsultasAdapter.ConsultaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultaViewHolder {
        val binding = ItemConsultaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConsultaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConsultaViewHolder, position: Int) {
        holder.bind(consultas[position])
    }

    override fun getItemCount(): Int = consultas.size

    class ConsultaViewHolder(private val binding: ItemConsultaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(consulta: Consulta) {
            val db = Firebase.firestore

            // Verifica se o pacienteId está corretamente configurado
            if (consulta.pacienteId.isNotEmpty()) {
                db.collection("pacientes").document(consulta.pacienteId).get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) {
                            val pacienteNome = document.getString("nome")
                            binding.textViewTitulo.text = pacienteNome ?: "Paciente Desconhecido"
                        } else {
                            binding.textViewTitulo.text = "Paciente Desconhecido"
                        }
                    }
                    .addOnFailureListener {
                        binding.textViewTitulo.text = "Erro ao buscar paciente"
                    }
            } else {
                binding.textViewTitulo.text = "ID do paciente inválido"
            }

            binding.textViewData.text = consulta.data
            binding.textViewHora.text = consulta.hora
        }
    }
}
