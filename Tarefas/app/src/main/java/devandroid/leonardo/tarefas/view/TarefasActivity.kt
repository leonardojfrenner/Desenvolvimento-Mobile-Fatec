package devandroid.leonardo.tarefas.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import devandroid.leonardo.tarefas.R
import devandroid.leonardo.tarefas.adapter.TarefaAdapter
import devandroid.leonardo.tarefas.dao.TarefaDaoImpl

class TarefasActivity : AppCompatActivity() {
    private val dao = TarefaDaoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefas)

        val tarefas = dao.obterTarefas()

        val btnVoltar = findViewById<Button>(R.id.btn_voltar)
        val rvTarefa = findViewById<RecyclerView>(R.id.rv_tarefas)
        rvTarefa.layoutManager = LinearLayoutManager(this)
        rvTarefa.adapter = TarefaAdapter(tarefas)

        btnVoltar.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
