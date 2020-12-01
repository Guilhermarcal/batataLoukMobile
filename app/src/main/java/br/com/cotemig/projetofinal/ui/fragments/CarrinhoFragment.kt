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
import br.com.cotemig.projetofinal.models.Produtos
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import br.com.cotemig.projetofinal.ui.activities.CardapioActivity
import br.com.cotemig.projetofinal.ui.adapters.CarrinhoAdapter
import br.com.cotemig.projetofinal.ui.adapters.ProdutosAdapter
import kotlinx.android.synthetic.main.fragment_cardapio.view.*
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

        Anime().tradeView(view.pb_cardapio, view.linear_fragment_cardapio)

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


                        view.lista_produtos.adapter = CarrinhoAdapter(activity, it.body().produtos)

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