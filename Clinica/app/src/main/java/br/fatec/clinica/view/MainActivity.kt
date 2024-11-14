package br.fatec.clinica

import Paciente
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import br.fatec.clinica.dao.Medico
import br.fatec.clinica.databinding.ActivityMainBinding
import br.fatec.clinica.view.AgendarActivity
import br.fatec.clinica.view.ConsultasActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val db = Firebase.firestore

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radio_button_medico) {
                binding.editTextEspecialidade.visibility = View.VISIBLE
                binding.editTextCrm.visibility = View.VISIBLE
            } else {
                binding.editTextEspecialidade.visibility = View.GONE
                binding.editTextCrm.visibility = View.GONE
            }
        }

        binding.buttonEntrar.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val senha = binding.editTextSenha.text.toString()

            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        user?.let {
                            val userId = it.uid
                            db.collection("pacientes").document(userId).get()
                                .addOnSuccessListener { document ->
                                    if (document.exists()) {
                                        startActivity(Intent(this, AgendarActivity::class.java))
                                    } else {
                                        db.collection("medicos").document(userId).get()
                                            .addOnSuccessListener { doc ->
                                                if (doc.exists()) {
                                                    startActivity(Intent(this, ConsultasActivity::class.java))
                                                }
                                            }
                                    }
                                }
                        }
                    } else {
                        Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.buttonCadastrar.setOnClickListener {
            if (!binding.editTextNome.isVisible) {
                binding.editTextNome.visibility = View.VISIBLE
                binding.radioGroup.visibility = View.VISIBLE
                binding.buttonCadastrar.text = "Confirmar"
            } else {
                // Realiza o cadastro
                val nome = binding.editTextNome.text.toString()
                val email = binding.editTextEmail.text.toString()
                val senha = binding.editTextSenha.text.toString()
                val especialidade = binding.editTextEspecialidade.text.toString()
                val crm = binding.editTextCrm.text.toString()

                auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                            if (binding.radioButtonMedico.isChecked) {
                                // Cadastro de Médico
                                val medico = Medico(nome, email, senha, especialidade, crm)
                                db.collection("medicos").document(userId).set(medico)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Cadastro de médico realizado com sucesso!", Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(this, "Erro ao cadastrar médico", Toast.LENGTH_SHORT).show()
                                    }
                            } else {
                                // Cadastro de Paciente
                                val paciente = Paciente(nome, email, senha)
                                db.collection("pacientes").document(userId).set(paciente)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Cadastro de paciente realizado com sucesso!", Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(this, "Erro ao cadastrar paciente", Toast.LENGTH_SHORT).show()
                                    }
                            }

                            // Limpa os campos de entrada
                            binding.editTextNome.text.clear()
                            binding.editTextEmail.text.clear()
                            binding.editTextSenha.text.clear()
                            binding.editTextEspecialidade.text.clear()
                            binding.editTextCrm.text.clear()
                            binding.radioGroup.clearCheck()

                            // Esconde os campos adicionais novamente
                            binding.editTextNome.visibility = View.GONE
                            binding.editTextEspecialidade.visibility = View.GONE
                            binding.editTextCrm.visibility = View.GONE
                            binding.radioGroup.visibility = View.GONE
                            binding.buttonCadastrar.text = "Cadastrar"
                        } else {
                            Toast.makeText(this, "Erro ao realizar cadastro.", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}
