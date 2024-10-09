package devandroid.leonardo.login.viewmodel

import androidx.lifecycle.ViewModel
import devandroid.leonardo.login.model.Usuario

class LoginViewModel : ViewModel() {
    private val usuarios = mutableListOf<Usuario>()

    fun login(user: Usuario): String {
        val usuario = usuarios.find { it.login == user.login }
        return when {
            usuario == null -> "Usuário não cadastrado"
            usuario.bloqueado -> "Usuário bloqueado"
            usuario.senha == user.senha -> {
                usuario.tentativas = 0
                "Login realizado com sucesso"
            }
            else -> {
                usuario.tentativas++
                if (usuario.tentativas >= 3) {
                    usuario.bloqueado = true
                    "Usuário bloqueado após 3 tentativas"
                } else {
                    "Senha incorreta. Tentativa ${usuario.tentativas} de 3"
                }
            }
        }
    }

    fun cadastrar(user: Usuario): String {
        return if (usuarios.any { it.login == user.login }) {
            "Usuário já cadastrado"
        } else {
            usuarios.add(user)
            "Usuário cadastrado com sucesso"
        }
    }

    fun getUsuarios() = usuarios
}
