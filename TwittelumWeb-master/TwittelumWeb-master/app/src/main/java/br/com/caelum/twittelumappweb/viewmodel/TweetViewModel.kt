package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.modelo.TweetComUsuario
import br.com.caelum.twittelumappweb.modelo.toTweetComUsuario

class TweetViewModel(
        private val repository: TweetRepository,
        private val usuarioRepository: UsuarioRepository) : ViewModel() {


    fun salva(tweet: Tweet)  {
        val usuario = usuarioRepository.getUsuario().value!!
        repository.salva(tweet.toTweetComUsuario(usuario))
    }
    fun falha() = repository.excecao

    fun novoTweet() = repository.tweetCriado

    fun tweets(): LiveData<List<TweetComUsuario>> = repository.pegaLista()


    fun filtraTweetsPelo(texto: String): List<TweetComUsuario> {
        val tweets = tweets().value!!
        return tweets.filter { tweet -> tweet.mensagem.contains(texto, true) }
    }

    fun carregaLista() = repository.buscaLista()


}