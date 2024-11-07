package br.edu.fatec.appfirestore

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.appfirestore.R
import br.edu.appfirestore.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore

        binding.btnSalvar.setOnClickListener {
            val nome = binding.edtNome.text.toString()
            val idade = binding.edtIdade.text.toString().toInt()
            val tempo = FieldValue.serverTimestamp()
            val cliente = hashMapOf(
                "nome" to nome,
                "idade" to idade
            )
            db.collection("clientes")
                .add(cliente)
                .addOnSuccessListener {
                    Toast.makeText(this,"Sucesso!",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Falhou", Toast.LENGTH_SHORT).show()
                }
            binding.edtNome.text.clear()
            binding.edtIdade.text.clear()
        }

    }
}