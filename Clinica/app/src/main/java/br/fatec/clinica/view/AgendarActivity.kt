package br.fatec.clinica.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.fatec.clinica.dao.Consulta
import br.fatec.clinica.databinding.ActivityAgendarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgendarActivity : AppCompatActivity() {
    lateinit var binding: ActivityAgendarBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val db = Firebase.firestore

        binding.buttonConfirmar.setOnClickListener {
            val data = binding.editTextData.text.toString()
            val hora = binding.editTextHora.text.toString()
            val user = auth.currentUser

            if (user != null) {
                val consulta = Consulta(pacienteId = user.uid, data = data, hora = hora)

                db.collection("consultas")
                    .add(consulta)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Consulta agendada para $data às $hora", Toast.LENGTH_SHORT).show()
                        // Limpar campos de entrada
                        binding.editTextData.text.clear()
                        binding.editTextHora.text.clear()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Erro ao agendar consulta", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonVoltar.setOnClickListener {
            finish()
        }
    }
}
