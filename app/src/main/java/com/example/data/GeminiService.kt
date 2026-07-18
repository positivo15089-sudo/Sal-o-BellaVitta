package com.example.data

import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object GeminiService {
    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    suspend fun getBeautyRecommendation(hairType: String, skinType: String, query: String): String = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext "O Bella AI está em modo demonstração. Para ativar as recomendações de IA, configure a chave GEMINI_API_KEY nos segredos do AI Studio."
        }

        val prompt = """
            Você é a Bella, uma consultora especialista em beleza, estética e cuidados capilares/faciais de um salão de beleza de luxo chamado BellaVita.
            O cliente informou:
            - Tipo de cabelo: $hairType
            - Tipo de pele: $skinType
            - Pergunta/Necessidade: $query
            
            Responda de forma elegante, acolhedora, empática e profissional (em português).
            Dê dicas práticas de rotina de autocuidado, sugira tratamentos específicos (que podem incluir os serviços do salão BellaVita como Corte & Escova, Coloração Premium, Limpeza de Pele, Massagem Relaxante, etc.), e recomende cuidados em casa. Use emojis adequados e formatação limpa. Limite a resposta a cerca de 150-200 palavras para manter a leitura agradável no celular.
        """.trimIndent()

        val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey"

        val jsonRequest = JSONObject().apply {
            put("contents", org.json.JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", org.json.JSONArray().apply {
                        put(JSONObject().apply {
                            put("text", prompt)
                        })
                    })
                })
            })
        }

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonRequest.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return@withContext "Erro na consulta: ${response.code}. Verifique sua chave de API nos segredos do AI Studio."
                }
                val responseBody = response.body?.string() ?: return@withContext "Resposta vazia."
                val jsonResponse = JSONObject(responseBody)
                val candidates = jsonResponse.getJSONArray("candidates")
                if (candidates.length() > 0) {
                    val candidate = candidates.getJSONObject(0)
                    val content = candidate.getJSONObject("content")
                    val parts = content.getJSONArray("parts")
                    if (parts.length() > 0) {
                        return@withContext parts.getJSONObject(0).getString("text")
                    }
                }
                "Não foi possível obter uma resposta adequada da Bella AI."
            }
        } catch (e: Exception) {
            "Erro de conexão com o assistente Bella AI: ${e.localizedMessage}. Verifique sua conexão com a internet."
        }
    }

    suspend fun getAdminAssistance(contextInfo: String, query: String): String = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext "O Assistente Administrativo está em modo demonstração. Configure a chave GEMINI_API_KEY para ativar respostas dinâmicas de IA."
        }

        val prompt = """
            Você é o assistente inteligente de gestão e concierge do salão de beleza de luxo BellaVita.
            Sua tarefa é auxiliar o gerente e a equipe do salão no trabalho do dia a dia.
            
            Informações do contexto atual do salão:
            $contextInfo
            
            Solicitação da equipe:
            $query
            
            Responda em português com tom profissional, prático, encorajador e voltado para excelência no atendimento ao cliente. Dê respostas formatadas de forma organizada, usando tópicos claros se necessário. Se pedirem mensagens para clientes (como lembretes ou desculpas por atraso), escreva modelos prontos para copiar e colar que sejam extremamente cordiais e refinados.
        """.trimIndent()

        val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey"

        val jsonRequest = JSONObject().apply {
            put("contents", org.json.JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", org.json.JSONArray().apply {
                        put(JSONObject().apply {
                            put("text", prompt)
                        })
                    })
                })
            })
        }

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = jsonRequest.toString().toRequestBody(mediaType)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return@withContext "Erro de resposta da IA (${response.code})."
                }
                val responseBody = response.body?.string() ?: return@withContext "Sem resposta do servidor."
                val jsonResponse = JSONObject(responseBody)
                val candidates = jsonResponse.getJSONArray("candidates")
                if (candidates.length() > 0) {
                    val candidate = candidates.getJSONObject(0)
                    val content = candidate.getJSONObject("content")
                    val parts = content.getJSONArray("parts")
                    if (parts.length() > 0) {
                        return@withContext parts.getJSONObject(0).getString("text")
                    }
                }
                "Erro ao processar o conselho administrativo."
            }
        } catch (e: Exception) {
            "Erro de conexão: ${e.localizedMessage}"
        }
    }
}
