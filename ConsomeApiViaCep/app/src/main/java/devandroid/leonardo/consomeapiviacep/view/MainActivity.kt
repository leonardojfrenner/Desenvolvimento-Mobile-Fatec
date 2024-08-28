package devandroid.leonardo.consomeapiviacep.view

import ConsomeApi
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import devandroid.leonardo.consomeapiviacep.R
import devandroid.leonardo.consomeapiviacep.dao.EnderecoDao
import devandroid.leonardo.consomeapiviacep.model.Endereco
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtCep = findViewById<EditText>(R.id.edt_cep)
        val btnPesquisar = findViewById<Button>(R.id.btn_pesquisar)

        btnPesquisar.setOnClickListener {
            val cep = edtCep.text.toString()
            if (cep.isEmpty()) {
                Toast.makeText(this, "Por favor, insira um CEP.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch{
                try {
                    val enderecoJson = ConsomeApi().buscaEndereco(cep)
                    if (enderecoJson != null) {
                        val endereco = Gson().fromJson(enderecoJson, Endereco::class.java)
                        Log.d("MainActivity", "Endereço deserializado: $endereco")

                        EnderecoDao.cadastrarEndereco(endereco)
                        val intent = Intent(this@MainActivity, ResultadoActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "Endereço não encontrado.", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "Erro ao buscar endereço: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e("MainActivity", "Erro ao buscar endereço", e)
                }
            }
        }
    }
}
