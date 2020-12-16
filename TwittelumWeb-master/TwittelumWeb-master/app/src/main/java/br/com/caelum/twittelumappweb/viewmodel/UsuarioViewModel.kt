package br.com.caelum.twittelumappweb.viewmodel

import androidx.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.modelo.Usuario


class UsuarioViewModel (private val repository: UsuarioRepository): ViewModel() {

    fun cria(usuario: Usuario) = repository.cadastra(usuario)
    fun loga(usuario: Usuario) = repository.entra(usuario)

    fun getUsuario() = repository.getUsuario()
    fun getErro() = repository.getErro()

}