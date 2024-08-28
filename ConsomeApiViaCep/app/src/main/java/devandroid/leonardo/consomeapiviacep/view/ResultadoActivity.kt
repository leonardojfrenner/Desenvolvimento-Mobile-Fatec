package devandroid.leonardo.consomeapiviacep.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import devandroid.leonardo.consomeapiviacep.R
import devandroid.leonardo.consomeapiviacep.dao.EnderecoDao

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val txtCep = findViewById<TextView>(R.id.txt_cep)
        val txtLogradouro = findViewById<TextView>(R.id.txt_logradouro)
        val txtBairro = findViewById<TextView>(R.id.txt_bairro)
        val btnVoltar = findViewById<Button>(R.id.btn_voltar)

        val endereco = EnderecoDao.getEndereco()

        if (endereco != null) {
            txtCep.text = endereco.cep
            txtLogradouro.text = endereco.logradouro
            txtBairro.text = endereco.bairro
        } else {
            txtCep.text = "Não disponível"
            txtLogradouro.text = "Não disponível"
            txtBairro.text = "Não disponível"
        }

        btnVoltar.setOnClickListener {
            finish()
        }
    }
}
