package devandroid.leonardo.transicaotelas.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devandroid.leonardo.transicaotelas.R
import devandroid.leonardo.transicaotelas.dao.ContatoDao
import devandroid.leonardo.transicaotelas.model.Contato

class ListaActivity : AppCompatActivity() {
    val dao = ContatoDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val txtNome = findViewById<TextView>(R.id.txt_nome)
        val txtWhats = findViewById<TextView>(R.id.txt_whats)
        val btnVoltar = findViewById<Button>(R.id.btn_voltar)

        val contato: Contato = dao.exibirContato()

        txtNome.text = contato.nome
        txtWhats.text=contato.whats



        btnVoltar.setOnClickListener {
            finish()
        }

    }
}