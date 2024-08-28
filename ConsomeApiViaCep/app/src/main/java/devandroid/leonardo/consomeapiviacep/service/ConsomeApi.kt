import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class ConsomeApi {

    private val client = OkHttpClient()

    suspend fun buscaEndereco(cep: String): String? {
        return withContext(Dispatchers.IO) {
            val url = "https://viacep.com.br/ws/$cep/json/"
            val request = Request.Builder()
                .url(url)
                .build()

            try {
                val response = client.newCall(request).execute()
                if (!response.isSuccessful) {
                    throw IOException("Unexpected code $response")
                }
                response.body?.string()
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }
}
