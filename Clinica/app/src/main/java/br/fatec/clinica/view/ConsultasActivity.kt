package br.fatec.clinica.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.fatec.clinica.adapter.ConsultasAdapter
import br.fatec.clinica.dao.Consulta
import br.fatec.clinica.databinding.ActivityConsultasBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ConsultasActivity : AppCompatActivity() {
    lateinit var binding: ActivityConsultasBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: ConsultasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val db = Firebase.firestore

        binding.recyclerViewConsultas.layoutManager = LinearLayoutManager(this)
        adapter = ConsultasAdapter(emptyList())
        binding.recyclerViewConsultas.adapter = adapter

        carregarConsultas(db)

        binding.buttonVoltar.setOnClickListener {
            finish()
        }
    }

    private fun carregarConsultas(db: FirebaseFirestore) {
        db.collection("consultas")
            .get()
            .addOnSuccessListener { result ->
                val consultas = mutableListOf<Consulta>()
                for (document in result) {
                    try {
                        val pacienteId = document.getString("pacienteId") ?: ""
                        val data = document.getString("data") ?: ""
                        val hora = document.getString("hora") ?: ""
                        val descricao = document.getString("descricao")

                        val consulta = Consulta(pacienteId, data, hora, descricao)
                        consultas.add(consulta)
                    } catch (e: Exception) {
                        Log.e("ConsultasActivity", "Erro ao carregar consulta", e)
                    }
                }
                adapter = ConsultasAdapter(consultas)
                binding.recyclerViewConsultas.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Erro ao carregar dados: ${exception.message}", Toast.LENGTH_SHORT).show()
                Log.e("ConsultasActivity", "Erro ao carregar consultas", exception)
            }
    }
}
