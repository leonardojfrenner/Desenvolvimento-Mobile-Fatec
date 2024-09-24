package br.edu.fatecpg.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatecpg.ui.R
import br.edu.fatecpg.ui.model.Contato
import com.bumptech.glide.Glide


class ContatoAdapter(private val contatos: List<Contato>) :
    RecyclerView.Adapter<ContatoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPessoa: ImageView = itemView.findViewById(R.id.img_pessoa)
        val txvNome: TextView = itemView.findViewById(R.id.txv_nome)
        val txvTelefone: TextView = itemView.findViewById(R.id.txv_telefone)
        val btnLigar: Button = itemView.findViewById(R.id.btn_ligar)
        val btnWhatsApp: Button = itemView.findViewById(R.id.btn_whatsapp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contato, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contatos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contato = contatos[position]
        holder.txvNome.text = contato.nome
        holder.txvTelefone.text = contato.telefone

        // Carregar a imagem da pessoa usando Glide
        Glide.with(holder.itemView.context)
            .load(contato.url)
            .into(holder.imgPessoa)

        // Funções dos botões (não implementam a lógica, apenas placeholders)
        holder.btnLigar.setOnClickListener {
            // Ação para ligar
        }

        holder.btnWhatsApp.setOnClickListener {
            // Ação para enviar mensagem via WhatsApp
        }
    }
}