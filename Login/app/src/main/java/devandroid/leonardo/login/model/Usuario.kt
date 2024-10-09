package devandroid.leonardo.login.model

data class Usuario(
    val login: String,
    var senha: String,
    var bloqueado: Boolean = false,
    var tentativas: Int = 0
)