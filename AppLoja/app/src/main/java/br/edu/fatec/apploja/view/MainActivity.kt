package br.edu.fatec.apploja.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatec.apploja.databinding.ActivityMainBinding
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
            val categoria = binding.edtCategoria.text.toString()
            val preco = binding.edtPreco.text.toString().toDouble()
            val tempo = FieldValue.serverTimestamp()
            
            val produto = hashMapOf(
                "nome" to nome,
                "categoria" to categoria,
                "preco" to preco
            )
            db.collection("produtos")
                .add(produto)
                .addOnSuccessListener {
                    Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Falhou", Toast.LENGTH_SHORT).show()
                }
            binding.edtNome.text.clear()
            binding.edtCategoria.text.clear()
            binding.edtPreco.text.clear()
        }
        binding.btnVerProdutos.setOnClickListener {
            val int = Intent(this,SegundaActivity::class.java)
            startActivity(int)
        }

    }
}