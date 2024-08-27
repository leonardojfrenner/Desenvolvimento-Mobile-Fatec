package devandroid.leonardo.transicaotelas.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import devandroid.leonardo.transicaotelas.R
import devandroid.leonardo.transicaotelas.dao.ContatoDao
import devandroid.leonardo.transicaotelas.model.Contato

class CadastroActivity : AppCompatActivity() {
    val dao = ContatoDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        //edt_nome edt_what btn_salvar btn_ver_lista

        val edtNome = findViewById<EditText>(R.id.edt_nome)
        val edtWhats = findViewById<EditText>(R.id.edt_whats)
        val btnSalvar = findViewById<Button>(R.id.btn_salvar)
        val btnVerContatos = findViewById<FloatingActionButton>(R.id.btn_ver_contatos)

        btnSalvar.setOnClickListener {

            val nome = edtNome.text.toString()
            val whats = edtWhats.text.toString()
            val contato = Contato(nome,whats)

            dao.cadastroContato(contato)
            Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show()
            edtNome.text.clear()
            edtWhats.text.clear()
        }

        btnVerContatos.setOnClickListener {
            val intent = Intent(this,ListaActivity::class.java)
            startActivity(intent)
        }
    }
}