package devandroid.leonardo.filmesfavoritos.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import devandroid.leonardo.filmesfavoritos.R
import devandroid.leonardo.filmesfavoritos.adapter.FilmeAdapter
import devandroid.leonardo.filmesfavoritos.dao.FilmeDaoImpl

class ListaActivity : AppCompatActivity() {
    val dao =FilmeDaoImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val rvFilmes = findViewById<RecyclerView>(R.id.rv_filmes)
        val btnAdicionar = findViewById<Button>(R.id.btn_adionar)
        val filmes = dao.obterFilme()

        rvFilmes.layoutManager = LinearLayoutManager(this)
        rvFilmes.adapter =FilmeAdapter(filmes)

        btnAdicionar.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
}