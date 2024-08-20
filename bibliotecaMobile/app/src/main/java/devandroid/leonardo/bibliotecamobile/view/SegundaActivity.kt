package devandroid.leonardo.bibliotecamobile.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import devandroid.leonardo.bibliotecamobile.R
import devandroid.leonardo.bibliotecamobile.model.Livro

class SegundaActivity : AppCompatActivity() {
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        val autorCadastrado = findViewById<TextView>(R.id.autorCadastrado)
        val tituloCadastrado = findViewById<TextView>(R.id.tituloCadastrado)
        val btnProximo = findViewById<FloatingActionButton>(R.id.btnProximo)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        val listaLivros = intent.getParcelableArrayListExtra<Livro>("ListaLivros")

        if (listaLivros != null && listaLivros.isNotEmpty()) {

            tituloCadastrado.text = "Título: ${listaLivros[currentIndex].titulo}"
            autorCadastrado.text = "Autor: ${listaLivros[currentIndex].autor}"

            btnProximo.setOnClickListener {
                currentIndex = (currentIndex + 1) % listaLivros.size
                val livroAtual = listaLivros[currentIndex]
                tituloCadastrado.text = "Título: ${livroAtual.titulo}"
                autorCadastrado.text = "Autor: ${livroAtual.autor}"
            }

            btnVoltar.setOnClickListener {
                finish()
            }
        } else {
            tituloCadastrado.text = "Nenhum livro cadastrado."
            autorCadastrado.text = ""
        }
    }
}
