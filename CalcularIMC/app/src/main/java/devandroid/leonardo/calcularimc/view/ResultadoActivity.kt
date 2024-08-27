package devandroid.leonardo.calcularimc.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import devandroid.leonardo.calcularimc.R
import devandroid.leonardo.calcularimc.dao.PessoaDao
import devandroid.leonardo.calcularimc.model.Pessoa

class ResultadoActivity : AppCompatActivity() {
    val dao = PessoaDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resultado)

        val txtResultado = findViewById<TextView>(R.id.txt_resultado)
        val btnVoltar = findViewById<Button>(R.id.btn_voltar)

        val imc = dao.calcularIMC()
        val resultadoFormatado = String.format("%.2f", imc)
        
        txtResultado.text = resultadoFormatado



        btnVoltar.setOnClickListener {
            finish()
        }

    }
}