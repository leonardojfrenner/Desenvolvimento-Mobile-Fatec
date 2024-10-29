package br.edu.fatecpg.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.SegundaActivity
import br.edu.fatecpg.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.btnEntrar.setOnClickListener{

            val email = binding.txvEmail.text.toString()
            val senha = binding.txvSenha.text.toString()

           auth.signInWithEmailAndPassword(email, senha)
               .addOnCompleteListener(this){ task ->
                   if (task.isSuccessful){
                       val intent = Intent(this, SegundaActivity::class.java)
                       intent.putExtra("EMAIL", email)
                       startActivity(intent)
                       Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
                   }
                   else{
                       Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show()
                   }
               }
        }
        binding.btnCadastrar.setOnClickListener {
            val email = binding.txvEmail.text.toString()
            val senha = binding.txvSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val intent = Intent(this, SegundaActivity::class.java)
                            intent.putExtra("EMAIL", email)
                            startActivity(intent)

                            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Erro ao cadastrar: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}