package devandroid.leonardo.filmesfavoritos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import devandroid.leonardo.filmesfavoritos.R
import devandroid.leonardo.filmesfavoritos.model.Filme

class FilmeAdapter(private val filmes:List<Filme>):
    RecyclerView.Adapter<FilmeAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val txvNome = itemView.findViewById<TextView>(R.id.txv_nome)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filme,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.txvNome.text = filme.nome
    }
}
