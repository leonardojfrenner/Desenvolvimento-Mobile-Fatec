package devandroid.leonardo.filmesfavoritos.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

import devandroid.leonardo.filmesfavoritos.R
import devandroid.leonardo.filmesfavoritos.dao.FilmeDaoImpl
import devandroid.leonardo.filmesfavoritos.model.Filme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = FilmeDaoImpl()
        val edtNome = findViewById<EditText>(R.id.edt_nome_filme)
        val edtAno = findViewById<EditText>(R.id.edt_ano_filme)
        val btnSalvar = findViewById<Button>(R.id.btn_salvar)
        val btnVerLista = findViewById<FloatingActionButton>(R.id.btn_ver_lista)

        btnSalvar.setOnClickListener {
            var nome = edtNome.text.toString()
            var ano=  edtAno.text.toString().toInt()
            var filme = Filme(nome, ano)
            dao.adicionarFilme(filme)
            Toast.makeText(this,"Filme adicionado",Toast.LENGTH_SHORT).show()
            edtNome.text.clear()
            edtAno.text.clear()
        }

        btnVerLista.setOnClickListener {
            val intent = Intent(this,ListaActivity::class.java)
            startActivity(intent)
        }
    }
}