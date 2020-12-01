package br.com.cotemig.projetofinal.ui.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.Itens
import br.com.cotemig.projetofinal.models.Produtos
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import br.com.cotemig.projetofinal.ui.activities.CardapioActivity
import br.com.cotemig.projetofinal.ui.adapters.CarrinhoAdapter
import kotlinx.android.synthetic.main.fragment_cardapio.view.linear_fragment_cardapio
import kotlinx.android.synthetic.main.fragment_cardapio.view.lista_produtos
import kotlinx.android.synthetic.main.fragment_cardapio.view.pb_cardapio
import kotlinx.android.synthetic.main.fragment_carrinho.view.*
import retrofit2.Call
import retrofit2.Response

class CarrinhoFragment : Fragment() {
    lateinit var activity: AppCompatActivity

    var itemList = ArrayList<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_carrinho, container, false)


        getCarrinho(view)

        Anime().tradeView(view.pb_cardapio, view.relative_fragment_cardapio)

        view.finalizar_pedidos.setOnClickListener {
            var activity = context as CardapioActivity
            activity.setFragment(EnderecoFragment(), "EnderecoFragment")
        }

        return view
    }

    fun getCarrinho(view: View) {

        var activity = context as CardapioActivity

        var s = RetrofitInitializer().serviceProdutos()

        var call = s.getProdutos()

        call.enqueue(object : retrofit2.Callback<Produtos> {
            override fun onResponse(call: Call<Produtos>?, response: Response<Produtos>?) {

                response?.let {

                    if (it.code() == 200) {

                        var carrinho = CardapioFragment().getCarrinho()
                        var carrinhoProdutos = ArrayList<Itens>()
                        var aux = carrinho.size
                        var j: Int = 0

                        carrinho.run {

                            sort()
                        }

                        for (i in it.body().produtos.indices) {

                            if (j < aux) {
                                if (carrinho[i] == it.body().produtos[i].id) {

                                    carrinhoProdutos.add(it.body().produtos[i])

                                }
                            }
                            j++
                        }

                        if (carrinhoProdutos.isEmpty()){
                            view.CarrinhoVazio.visibility = View.VISIBLE
                            view.relative_finalizar_pedidos.visibility = View.INVISIBLE

                        } else{
                            view.CarrinhoVazio.visibility = View.INVISIBLE
                            view.relative_finalizar_pedidos.visibility = View.VISIBLE

                        }

                        view.lista_produtos.adapter = CarrinhoAdapter(activity, carrinhoProdutos)

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

    class Anime {
        private fun fadeIn(view: View) {
            val animation = AlphaAnimation(0f, 1f)
            animation.duration = 500L
            view.startAnimation(animation)
        }

        private fun fadeOut(view: View) {
            val animation = AlphaAnimation(1f, 0f)
            animation.duration = 500L
            view.startAnimation(animation)
        }

        fun tradeView(view1: View, view2: View) {
            fadeOut(view1)

            Handler().postDelayed({
                view1.visibility = View.INVISIBLE
                view2.visibility = View.VISIBLE
                fadeIn(view2)
            }, 500L)

        }
    }
}