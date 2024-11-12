package br.edu.fatec.pg.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatec.pg.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        if (auth.currentUser != null) {
            val intent = Intent(this, CadastroActivity::class.java)
            intent.putExtra("EMAIL", auth.currentUser?.email)
            startActivity(intent)
            finish()
            return
        }

        binding.btnEntrar.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val senha = binding.edtSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, CadastroActivity::class.java)
                        intent.putExtra("EMAIL", email)
                        startActivity(intent)
                        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Erro ao fazer login", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.btnCadastrar.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val senha = binding.edtSenha.text.toString()
            val nome = binding.edtNome.text.toString()
            val isMedico = binding.rdMedico.isChecked

            if (email.isEmpty() || senha.isEmpty() || nome.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        val userMap = hashMapOf(
                            "nome" to nome,
                            "email" to email
                        )


                        val collection = if (isMedico) "medicos" else "pacientes"

                        db.collection(collection).document(user?.uid ?: "")
                            .set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()

                                val intent = if (isMedico) {
                                    Intent(this, VisualizarConsultasActivity::class.java)
                                } else {
                                    Intent(this, CadastroActivity::class.java)
                                }

                                intent.putExtra("EMAIL", email)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Erro ao cadastrar: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Erro ao criar conta", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
