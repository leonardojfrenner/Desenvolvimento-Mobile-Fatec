package devandroid.leonardo.bibliotecamobile.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import devandroid.leonardo.bibliotecamobile.R
import devandroid.leonardo.bibliotecamobile.model.Livro

class MainActivity : AppCompatActivity() {

    private val listaLivro = mutableListOf<Livro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titulo = findViewById<EditText>(R.id.titulo)
        val autor = findViewById<EditText>(R.id.autor)
        val btn = findViewById<Button>(R.id.btnEnviar)
        val btnAdicionar = findViewById<Button>(R.id.btnAdicionarLivro)

        btnAdicionar.setOnClickListener {
            val tituloTexto = titulo.text.toString()
            val autorTexto = autor.text.toString()
            val livro = Livro(tituloTexto, autorTexto)
            listaLivro.add(livro)

            titulo.text.clear()
            autor.text.clear()

            hideKeyboard()

            Toast.makeText(this, "Livro adicionado: $tituloTexto", Toast.LENGTH_SHORT).show()
        }

        btn.setOnClickListener {
            val intent = Intent(this, SegundaActivity::class.java)
            intent.putParcelableArrayListExtra("ListaLivros", ArrayList(listaLivro))
            startActivity(intent)
        }
    }
    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
