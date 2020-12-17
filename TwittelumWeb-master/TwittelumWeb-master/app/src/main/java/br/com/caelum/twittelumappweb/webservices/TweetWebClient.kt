package br.com.caelum.twittelumappweb.webservices

import br.com.caelum.twittelumappweb.modelo.TweetComUsuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class TweetWebClient(retrofit: Retrofit) {

    private val tweetService = retrofit.create(TweetService::class.java)


        private interface TweetService {
            @POST("/tweet")
            fun salva(@Body tweet: TweetComUsuario): Call<TweetComUsuario>

            @GET("/tweet")
            fun busca(): Call<List<TweetComUsuario>>
        }

    fun buscaTweets(
            sucesso: (tweets: List<TweetComUsuario>) -> Unit,
            falha: (erro: Throwable) -> Unit
    ) {
        tweetService.busca().enqueue(object : Callback<List<TweetComUsuario>> {
            override fun onFailure(call: Call<List<TweetComUsuario>>, t: Throwable) {
                falha(t)
            }

            override fun onResponse(call: Call<List<TweetComUsuario>>,
                                    response: Response<List<TweetComUsuario>>) {
                response.body()?.let(sucesso)
            }
        })

    }

    fun insere(
            tweet: TweetComUsuario,
            sucesso: (tweet: TweetComUsuario) -> Unit,
            falha: (erro: Throwable) -> Unit
    ) {
        tweetService.salva(tweet).enqueue(object : Callback<TweetComUsuario> {
            override fun onFailure(call: Call<TweetComUsuario>, t: Throwable) {
                falha(t)
            }

            override fun onResponse(call: Call<TweetComUsuario>, response: Response<TweetComUsuario
                    >) {
                response.body()?.let(sucesso)
            }
        })


    }
}