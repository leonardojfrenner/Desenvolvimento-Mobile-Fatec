package br.edu.fatecpg.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatecpg.ui.R
import br.edu.fatecpg.ui.adapter.ContatoAdapter
import br.edu.fatecpg.ui.dao.ContatoDaoImpl // Certifique-se de que esse caminho está correto
import br.edu.fatecpg.ui.model.Contato

class ListaActivity : AppCompatActivity() {

    private val dao = ContatoDaoImpl() // Instanciação do DAO
    private lateinit var recyclerView: RecyclerView
    private lateinit var contatoAdapter: ContatoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        // Obtendo a lista de contatos a partir da DAO
        val contatos: List<Contato> = dao.obterContatos()

        // Configurando o RecyclerView
        recyclerView = findViewById(R.id.recycler_contatos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        contatoAdapter = ContatoAdapter(contatos)
        recyclerView.adapter = contatoAdapter

    }
}
