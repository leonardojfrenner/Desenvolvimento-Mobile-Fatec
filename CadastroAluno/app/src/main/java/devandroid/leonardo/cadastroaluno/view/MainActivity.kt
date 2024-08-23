package devandroid.leonardo.cadastroaluno.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devandroid.leonardo.cadastroaluno.R
import devandroid.leonardo.cadastroaluno.model.Aluno
import devandroid.leonardo.cadastroaluno.model.Aluno.Companion.gerarNumeroMatricula

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nome = findViewById<EditText>(R.id.nomeAluno)
        var btn = findViewById<Button>(R.id.btnEnviar)

        btn.setOnClickListener{
            var nomeAluno = nome.text.toString()
            var novoAluno = Aluno(nomeAluno)

            val intent = Intent(this, SegundaActivity::class.java)
            intent.putExtra("nomeAluno", nomeAluno)
            intent.putExtra("numeroMatricula", novoAluno.numeroMarticula)
            startActivity(intent)
        }
    }

}