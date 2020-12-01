package br.com.cotemig.projetofinal.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.CepUser
import br.com.cotemig.projetofinal.models.EnderecoUser
import br.com.cotemig.projetofinal.models.InformacoesEnderecoUser
import br.com.cotemig.projetofinal.models.Pedido
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import br.com.cotemig.projetofinal.ui.activities.CardapioActivity
import br.com.cotemig.projetofinal.ui.adapters.EnderecoAdapter
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_endereco.view.*
import retrofit2.Call
import retrofit2.Response

class EnderecoFragment : Fragment() {

    lateinit var lista : List<InformacoesEnderecoUser>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_endereco, container, false)

        getEnderecoUser(view)

        view.btn_adicionar_novo_endereco.setOnClickListener {
            var activity = context as CardapioActivity
            activity.setFragment(CadastrarEnderecoFragment(), "CadastrarEnderecoFragment")
        }

        view.list_endereco_user.setOnItemClickListener { adapterView, view, i, l ->

            getPedido(view, lista[i].endereco)

        }

        // Inflate the layout for this fragment
        return view
    }

    fun getEnderecoUser(view: View){

        var activity = context as CardapioActivity

        var s = RetrofitInitializer().serviceEnderecoUser()

        var call = s.getEnderecoPessoa(activity.getUserEmail())

        call.enqueue(object : retrofit2.Callback<EnderecoUser>{
            override fun onResponse(call: Call<EnderecoUser>?, response: Response<EnderecoUser>?) {
                response?.let {
                    if (it.code() == 200){

                        if(it.body().enderecoPessoa.contains(null)){

                            activity.setFragment(TelaSemEnderecoFragment(), "TelaSemEnderecoFragment")

                        }else{

                            view.list_endereco_user.adapter = EnderecoAdapter(activity, it.body().enderecoPessoa)

                            lista = it.body().enderecoPessoa

                        }

                    }
                }
            }

            override fun onFailure(call: Call<EnderecoUser>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getPedido(view: View, endereco : String){

        var activity = context as CardapioActivity

        var s = RetrofitInitializer().servicePedido()

        var call = s.postNewPedido(activity.getUserEmail(), endereco)

        call.enqueue(object : retrofit2.Callback<Pedido>{
            override fun onResponse(call: Call<Pedido>?, response: Response<Pedido>?) {
                response?.let {
                    if (it.code() == 200){

                        MaterialDialog(activity).show {
                            title(R.string.sucesso)
                            message(R.string.PedidoSucesso)
                            positiveButton(R.string.ok)
                        }

                        activity.setFragment(PedidoFragment(), "PedidoFragment")

                    }else{
                        MaterialDialog(activity).show {
                            title(R.string.sucesso)
                            message(R.string.PedidoSucesso)
                            positiveButton(R.string.ok)
                        }

                        activity.setFragment(PedidoFragment(), "PedidoFragment")
                    }
                }
            }

            override fun onFailure(call: Call<Pedido>?, t: Throwable?) {
                MaterialDialog(activity).show {
                    title(R.string.sucesso)
                    message(R.string.PedidoSucesso)
                    positiveButton(R.string.ok)
                }

                activity.setFragment(PedidoFragment(), "PedidoFragment")
            }

        })

    }
}