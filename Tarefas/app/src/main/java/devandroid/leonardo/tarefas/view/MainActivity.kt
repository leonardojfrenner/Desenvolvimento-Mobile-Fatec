package devandroid.leonardo.tarefas.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devandroid.leonardo.tarefas.R
import devandroid.leonardo.tarefas.dao.TarefaDaoImpl
import devandroid.leonardo.tarefas.model.Tarefa

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = TarefaDaoImpl()

        val edtNome = findViewById<EditText>(R.id.edt_tarefa_nome)
        val edtDescricao = findViewById<EditText>(R.id.edt_tarefa_descricao)
        val btnSalvar = findViewById<Button>(R.id.btn_salvar_tarefa)
        val btnLimpar = findViewById<Button>(R.id.btn_limpar_tarefa)
        val btnVerTarefas = findViewById<Button>(R.id.btn_ver_lista_tarefas)

        btnSalvar.setOnClickListener {
            var nome = edtNome.text.toString()
            var descricao = edtDescricao.text.toString()
            var tarefa = Tarefa(nome,descricao)
            dao.adicionarTarefa(tarefa)
            Toast.makeText(this,"Adicionada",Toast.LENGTH_SHORT).show()
            btnVerTarefas.isEnabled = true
            edtNome.text.clear()
            edtDescricao.text.clear()
        }

        btnLimpar.setOnClickListener {
            edtNome.text.clear()
            edtDescricao.text.clear()
        }

        btnVerTarefas.setOnClickListener {
            val intent = Intent(this,TarefasActivity::class.java)
            startActivity(intent)
        }

    }
}