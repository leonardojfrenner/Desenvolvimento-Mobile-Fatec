package devandroid.leonardo.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import devandroid.leonardo.login.R
import devandroid.leonardo.login.model.Usuario

class UsuarioAdapter(private val usuarios: List<Usuario>) :
    RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewLogin: TextView = view.findViewById(R.id.txv_login)
        val textViewStatus: TextView = view.findViewById(R.id.txv_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.textViewLogin.text = usuario.login
        holder.textViewStatus.text = if (usuario.bloqueado) "Bloqueado" else "Ativo"
    }

    override fun getItemCount() = usuarios.size
}
