package br.com.cotemig.projetofinal.ui.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.EnderecoUser
import br.com.cotemig.projetofinal.models.Pedido
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import br.com.cotemig.projetofinal.ui.activities.CardapioActivity
import br.com.cotemig.projetofinal.ui.adapters.EnderecoAdapter
import br.com.cotemig.projetofinal.ui.adapters.PedidoAdapter
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_cadastrar_endereco.view.*
import kotlinx.android.synthetic.main.fragment_cardapio.view.*
import kotlinx.android.synthetic.main.fragment_carrinho.view.*
import kotlinx.android.synthetic.main.fragment_endereco.view.*
import kotlinx.android.synthetic.main.fragment_endereco.view.list_endereco_user
import kotlinx.android.synthetic.main.fragment_pedido.view.*
import retrofit2.Call
import retrofit2.Response

class PedidoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_pedido, container, false)

        Anime().tradeView(view.pb_pedido, view.relative_pedido)

        getPedido(view)

        // Inflate the layout for this fragment
        return view

    }

    fun getPedido(view: View){

        var activity = context as CardapioActivity

        var s = RetrofitInitializer().servicePedido()

        var call = s.getPedido(activity.getUserEmail())

        call.enqueue(object : retrofit2.Callback<Pedido>{
            override fun onResponse(call: Call<Pedido>?, response: Response<Pedido>?) {
                response?.let {
                    if (it.code() == 200){

                        if(it.body().pedidoPessoa.contains(null)){

                            //activity.setFragment(TelaSemEnderecoFragment(), "TelaSemEnderecoFragment")

                        }else{
                            view.lista_pedidos_finalizado.adapter = PedidoAdapter(activity, it.body().pedidoPessoa)

                            view.lista_pedidos_finalizado.layoutManager = LinearLayoutManager(
                                activity,
                                LinearLayoutManager.VERTICAL, false
                            )

                        }

                    }
                }
            }

            override fun onFailure(call: Call<Pedido>?, t: Throwable?) {
                TODO("Not yet implemented")
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