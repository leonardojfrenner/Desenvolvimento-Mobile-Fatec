package br.edu.fatec.pg.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.fatec.pg.R
import br.edu.fatec.pg.adapter.PacienteAdapter
import br.edu.fatec.pg.databinding.ActivityVisualizarConsultasBinding
import br.edu.fatec.pg.dao.PacienteDao
import com.google.firebase.firestore.FirebaseFirestore

class VisualizarConsultasActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: PacienteAdapter
    private lateinit var binding: ActivityVisualizarConsultasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVisualizarConsultasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()

        binding.rcvLista.layoutManager = LinearLayoutManager(this)


        adapter = PacienteAdapter(emptyList()) // Inicia com lista vazia
        binding.rcvLista.adapter = adapter

        carregarConsultas()
    }

    private fun carregarConsultas() {
        db.collection("consultas") // Nome da coleção no Firestore
            .get()
            .addOnSuccessListener { result ->
                val pacientes = mutableListOf<PacienteDao>()
                for (document in result) {
                    val nome = document.getString("nome") ?: ""
                    val email = document.getString("email") ?: ""
                    val data = document.getString("data") ?: ""
                    val horario = document.getString("horario") ?: ""

                    val paciente = PacienteDao(nome, email, data, horario)


                    pacientes.add(paciente)
                }


                adapter = PacienteAdapter(pacientes)
                binding.rcvLista.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Erro ao carregar dados: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}
