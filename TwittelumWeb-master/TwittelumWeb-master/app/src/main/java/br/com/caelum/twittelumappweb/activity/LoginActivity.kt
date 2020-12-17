package br.com.caelum.twittelumappweb.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import br.com.caelum.twittelumappweb.databinding.ActivityLoginBinding
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.viewmodel.UsuarioViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: UsuarioViewModel by lazy { ViewModelProvider(this, ViewModelFactory).get(UsuarioViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginCriar.setOnClickListener { viewModel.cria(usuarioDaTela()) }
        binding.loginEntrar.setOnClickListener { viewModel.loga(usuarioDaTela()) }

        viewModel.getErro().observe(this) {
            it?.let {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                Log.i("error", it.message)
            }
        }
        viewModel.getUsuario().observe(this) {
            it?.let {
                vaiParaMain()
            }
        }

    }

    private fun vaiParaMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun usuarioDaTela(): Usuario {
        val nome = binding.loginCampoNome.text.toString()
        val senha = binding.loginCampoSenha.text.toString()
        val username = binding.loginCampoUsername.text.toString()
        return Usuario(username = username, senha = senha, nome = nome )
    }
}

