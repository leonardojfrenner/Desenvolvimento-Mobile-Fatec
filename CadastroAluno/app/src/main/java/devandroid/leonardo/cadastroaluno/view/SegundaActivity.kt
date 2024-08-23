package devandroid.leonardo.cadastroaluno.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devandroid.leonardo.cadastroaluno.R

class SegundaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        val nomeAluno = findViewById<TextView>(R.id.nomeAluno)
        val btn = findViewById<Button>(R.id.btnVoltar)

        val nome = intent.getStringExtra("nomeAluno")
        val matricula = intent.getIntExtra("numeroMatricula", 0)

        nomeAluno.text = "Aluno: $nome\nMatrícula: $matricula"

        btn.setOnClickListener {
            finish()
        }

    }
}