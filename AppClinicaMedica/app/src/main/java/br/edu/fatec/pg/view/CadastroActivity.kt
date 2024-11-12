package br.edu.fatec.pg.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatec.pg.R
import br.edu.fatec.pg.databinding.ActivityCadastroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.btnAgendar.setOnClickListener {
            cadastrarConsulta()
        }
    }

    private fun cadastrarConsulta() {
        val data = binding.edtData.text.toString().trim()
        val horario = binding.edtHorario.text.toString().trim()
        val userId = auth.currentUser?.uid

        if (data.isEmpty() || horario.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (userId == null) {
            Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_SHORT).show()
            return
        }

        val emailPaciente = auth.currentUser?.email ?: ""
        val nomePacienteRef = db.collection("pacientes").document(userId)

        nomePacienteRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val nomePaciente = document.getString("nome") ?: "Paciente"

                val consulta = hashMapOf(
                    "nome" to nomePaciente,
                    "email" to emailPaciente,
                    "data" to data,
                    "horario" to horario,
                    "pacienteId" to userId
                )


                db.collection("consultas")
                    .add(consulta)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Consulta agendada com sucesso", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Erro ao agendar consulta: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Paciente não encontrado", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { e ->
            Toast.makeText(this, "Erro ao buscar paciente: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}
