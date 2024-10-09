package devandroid.leonardo.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import devandroid.leonardo.login.R
import devandroid.leonardo.login.databinding.ActivityLoginBinding
import devandroid.leonardo.login.model.Usuario
import devandroid.leonardo.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            val (login, senha) = pegarDados()
            if (login.isNotEmpty() && senha.isNotEmpty()) { // Verifica se os campos não estão vazios
                val user = Usuario(login, senha)
                val retorno = viewModel.login(user)
                Toast.makeText(this, retorno, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCadastrar.setOnClickListener {
            val (login, senha) = pegarDados()
            if (login.isNotEmpty() && senha.isNotEmpty()) { // Verifica se os campos não estão vazios
                val user = Usuario(login, senha)
                val retorno = viewModel.cadastrar(user)
                Toast.makeText(this, retorno, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAdmin.setOnClickListener {
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pegarDados(): Pair<String, String> {
        val login = binding.edtNome.text.toString()
        val senha = binding.edtSenha.text.toString()
        return Pair(login, senha)
    }
}
