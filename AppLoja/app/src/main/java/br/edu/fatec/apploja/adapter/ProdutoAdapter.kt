package br.edu.fatec.apploja.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatec.apploja.R
import br.edu.fatec.apploja.model.Produto

class ProdutoAdapter(private val produtos: List<Produto>) :
    RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    // ViewHolder que representa o layout de cada item
    class ProdutoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNomeProduto: TextView = view.findViewById(R.id.txtNomeProduto)
        val txtCategoriaProduto: TextView = view.findViewById(R.id.txtCategoriaProduto)
        val txtPrecoProduto: TextView = view.findViewById(R.id.txtPrecoProduto)
    }

    // Infla o layout do item para o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lista, parent, false)
        return ProdutoViewHolder(view)
    }

    // Vincula os dados do produto aos elementos da ViewHolder
    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.txtNomeProduto.text = produto.nome
        holder.txtCategoriaProduto.text = produto.categoria
        holder.txtPrecoProduto.text = String.format("R$ %.2f", produto.preco)
    }

    // Retorna o número de itens na lista
    override fun getItemCount(): Int {
        return produtos.size
    }
}
