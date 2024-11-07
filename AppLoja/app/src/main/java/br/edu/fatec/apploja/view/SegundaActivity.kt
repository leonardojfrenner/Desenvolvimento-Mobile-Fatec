package br.edu.fatec.apploja.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.fatec.apploja.adapter.ProdutoAdapter
import br.edu.fatec.apploja.databinding.ActivitySegundaBinding
import br.edu.fatec.apploja.model.Produto
import com.google.firebase.firestore.FirebaseFirestore

class SegundaActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaBinding
    private val db = FirebaseFirestore.getInstance()
    private val produtos = mutableListOf<Produto>()  // Lista para armazenar produtos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        carregarProdutos()

        // Configura o botão "Voltar"
        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        // Configuração do RecyclerView
        binding.rcvLista.apply {
            layoutManager = LinearLayoutManager(this@SegundaActivity)
            adapter = ProdutoAdapter(produtos)
        }
    }

    private fun carregarProdutos() {
        // Consulta os produtos da coleção "produtos" no Firestore
        db.collection("produtos")
            .get()
            .addOnSuccessListener { result ->
                produtos.clear() // Limpa a lista antes de adicionar novos itens
                for (document in result) {
                    val nome = document.getString("nome") ?: "Sem Nome"
                    val categoria = document.getString("categoria") ?: "Sem Categoria"
                    val preco = document.getDouble("preco") ?: 0.0
                    val produto = Produto(nome, categoria, preco)
                    produtos.add(produto) // Adiciona o produto à lista
                }
                binding.rcvLista.adapter?.notifyDataSetChanged() // Atualiza o RecyclerView
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao carregar produtos", Toast.LENGTH_SHORT).show()
                Log.e("SegundaActivity", "Erro ao carregar produtos", e)
            }
    }
}
