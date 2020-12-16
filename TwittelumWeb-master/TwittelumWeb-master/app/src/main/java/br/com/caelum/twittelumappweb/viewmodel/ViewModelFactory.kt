package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.webservices.InicializadorDoRetrofit
import br.com.caelum.twittelumappweb.webservices.UsuarioWebClient

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {


    private val retrofit = InicializadorDoRetrofit.retrofit
    private val usuarioWebClient = UsuarioWebClient(retrofit)
    private val tweetRepository = TweetRepository()
    private val usuarioRepository = UsuarioRepository(usuarioWebClient)


    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass){


        TweetViewModel::class.java -> { TweetViewModel(tweetRepository) as T}
        else -> { UsuarioViewModel(usuarioRepository) as T}
    }

}