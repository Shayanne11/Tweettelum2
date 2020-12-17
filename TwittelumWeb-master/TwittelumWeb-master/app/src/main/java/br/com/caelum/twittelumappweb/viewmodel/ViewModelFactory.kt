package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.webservices.InicializadorDoRetrofit
import br.com.caelum.twittelumappweb.webservices.TweetWebClient
import br.com.caelum.twittelumappweb.webservices.UsuarioWebClient

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {


    private val retrofit = InicializadorDoRetrofit.retrofit
    private val usuarioWebClient = UsuarioWebClient(retrofit)
    private val tweetWebClient = TweetWebClient(retrofit)
    private val tweetRepository = TweetRepository(tweetWebClient)
    private val usuarioRepository = UsuarioRepository(usuarioWebClient)


    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass){


        TweetViewModel::class.java -> { TweetViewModel(Injetor.getTweetRepository, Injetor.getUsuarioRepository) as T}
        else -> { UsuarioViewModel(Injetor.getUsuarioRepository) as T}
    }

    object Injetor {
        private val getRetrofit = InicializadorDoRetrofit.retrofit
        private val getTweetWebClient = TweetWebClient(getRetrofit)
        val getTweetRepository = TweetRepository(getTweetWebClient)
        private val getUsuarioWebClient = UsuarioWebClient(getRetrofit)
        val getUsuarioRepository = UsuarioRepository(getUsuarioWebClient)
    }
}