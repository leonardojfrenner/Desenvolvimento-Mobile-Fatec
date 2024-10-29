package br.edu.fatecpg

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import br.edu.fatecpg.databinding.ActivitySegundaBinding

class SegundaActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySegundaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("EMAIL")
        binding.textViewEmail.text = email

        binding.btnAtualizar.setOnClickListener {
            email?.let {
                auth.sendPasswordResetEmail(it)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Email de redefinição enviado.", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Erro ao enviar email: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.btnExcluir.setOnClickListener {
            auth.currentUser?.delete()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Cadastro excluído com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao excluir cadastro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
