package br.com.caelum.twittelumappweb.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.databinding.ListaTweetsFragmentBinding
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory

class BuscadorDeTweetsFragment: Fragment() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProvider(activity!!, ViewModelFactory).get(TweetViewModel::class.java)
    }

    private lateinit var binding: ListaTweetsFragmentBinding



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = ListaTweetsFragmentBinding.inflate(inflater,container, false)
        return binding.root

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater?.inflate(R.menu.buscador_menu,menu)
        val botaoBusca = menu.findItem(R.id.barra_busca)
        val search = botaoBusca?.actionView as SearchView
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(texto: String?): Boolean {
                if (!texto.isNullOrBlank()) {
                    val filtrados = viewModel.filtraTweetsPelo(texto)
                    binding.listaTweets.adapter = TweetAdapter(filtrados)
                }
                return false
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
}