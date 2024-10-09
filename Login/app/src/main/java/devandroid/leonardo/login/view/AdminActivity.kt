package devandroid.leonardo.login.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import devandroid.leonardo.login.adapter.UsuarioAdapter
import devandroid.leonardo.login.databinding.ActivityAdminBinding
import devandroid.leonardo.login.viewmodel.LoginViewModel

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private val viewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Chama a função para configurar o RecyclerView
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // Obtém a lista atualizada de usuários
        val usuarios = viewModel.getUsuarios()

        val recyclerView = binding.recyclerViewUsuarios
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UsuarioAdapter(usuarios)
    }

    // Chama a função setupRecyclerView novamente se houver alterações nos dados
    override fun onResume() {
        super.onResume()
        setupRecyclerView() // Atualiza a lista de usuários quando a activity é retomada
    }
}
