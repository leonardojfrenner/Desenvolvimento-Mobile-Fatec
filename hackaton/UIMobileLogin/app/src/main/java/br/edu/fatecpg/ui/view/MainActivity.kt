package br.edu.fatecpg.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.ui.R
import br.edu.fatecpg.ui.dao.ContatoDaoImpl
import br.edu.fatecpg.ui.model.Contato

class MainActivity : AppCompatActivity() {
    private val dao = ContatoDaoImpl() // Instanciando o ContatoDaoImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNome = findViewById<EditText>(R.id.txv_nome)
        val edtTelefone = findViewById<EditText>(R.id.txv_telefone)
        val edtImagem = findViewById<EditText>(R.id.txv_imagem)
        val btnCadastrar = findViewById<Button>(R.id.btn_cadastrar)
        val btnVerContatos = findViewById<TextView>(R.id.btn_lista)

        btnCadastrar.setOnClickListener {
            val nome = edtNome.text.toString()
            val telefone = edtTelefone.text.toString()
            val imagem = edtImagem.text.toString()
            val contato = Contato(nome, telefone, imagem)
            dao.adicionarContato(contato)
            Toast.makeText(this, "Contato adicionado", Toast.LENGTH_SHORT).show()
            edtNome.text.clear()
            edtTelefone.text.clear()
            edtImagem.text.clear()
        }

        btnVerContatos.setOnClickListener {
            val intent = Intent(this, ListaActivity::class.java)
            startActivity(intent)
        }
    }
}