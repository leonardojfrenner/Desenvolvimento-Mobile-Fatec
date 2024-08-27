package devandroid.leonardo.calcularimc.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import devandroid.leonardo.calcularimc.R
import devandroid.leonardo.calcularimc.dao.PessoaDao
import devandroid.leonardo.calcularimc.model.Pessoa

class MainActivity : AppCompatActivity() {
    val dao = PessoaDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtPeso = findViewById<EditText>(R.id.edt_peso)
        val edtAltura = findViewById<EditText>(R.id.edt_altura)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)


        btnCalcular.setOnClickListener {
            var intent = Intent(this,ResultadoActivity::class.java)
            var peso = edtPeso.text.toString()
            var altura = edtAltura.text.toString()

            var pessoa = Pessoa(altura, peso)

            dao.cadastrarPessoa(pessoa)

            startActivity(intent)

        }

    }
}