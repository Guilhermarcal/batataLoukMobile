package br.com.cotemig.projetofinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.Produtos
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import br.com.cotemig.projetofinal.ui.activities.CardapioActivity
import br.com.cotemig.projetofinal.ui.adapters.ProdutosAdapter
import kotlinx.android.synthetic.main.fragment_cardapio.view.*
import retrofit2.Call
import retrofit2.Response

class CardapioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_cardapio, container, false)

        getProdutos(view)
        return view
    }

    fun getProdutos(view: View){

        var activity = context as CardapioActivity

        var s = RetrofitInitializer().serviceProdutos()

        var call = s.getProdutos()

        call.enqueue(object : retrofit2.Callback<Produtos>{
            override fun onResponse(call: Call<Produtos>?, response: Response<Produtos>?) {

                response?.let {

                    if (it.code() == 200){

                        view.lista_produtos.adapter = ProdutosAdapter(activity, it.body().produtos)

                        view.lista_produtos.layoutManager = LinearLayoutManager(
                            activity,
                            LinearLayoutManager.VERTICAL, false
                        )

                    }

                }

            }

            override fun onFailure(call: Call<Produtos>?, t: Throwable?) {

            }

        })
    }


}