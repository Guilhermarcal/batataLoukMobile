package br.com.cotemig.projetofinal.ui.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.CepUser
import br.com.cotemig.projetofinal.models.EnderecoUser
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import br.com.cotemig.projetofinal.ui.activities.CardapioActivity
import br.com.cotemig.projetofinal.ui.adapters.EnderecoAdapter
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.fragment_cadastrar_endereco.*
import kotlinx.android.synthetic.main.fragment_cadastrar_endereco.view.*
import kotlinx.android.synthetic.main.fragment_endereco.view.*
import retrofit2.Call
import retrofit2.Response

class CadastrarEnderecoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_cadastrar_endereco, container, false)

        view.btn_procurar_cep_endereco.setOnClickListener {

            getEnderecoCep(view)

        }

        view.btn_salvar_endereco.setOnClickListener {
            getNovoEndereco(view)
        }

        // Inflate the layout for this fragment
        return view
    }

    fun getEnderecoCep(view: View){
        var s = RetrofitInitializer().serviceCepUser()

        var call = s.getCep(view.cep_filtro_usuario.text.toString())

        call.enqueue(object : retrofit2.Callback<CepUser>{
            override fun onResponse(call: Call<CepUser>?, response: Response<CepUser>?) {
                response?.let {
                    if (it.code() == 200){

                        tradeView(view.linear_cadastro_endereco, relative_cadastro_endereco)

                        view.endereco_usuario_cadastro.text = it.body().logradouro
                        view.localidade_usuario_cadastro.text = it.body().localidade + " - " + it.body().uf
                        view.cep_usuario_cadastro.text = it.body().cep

                    }else{
                        var activity = context as CardapioActivity

                        MaterialDialog(activity).show {
                            title(R.string.title)
                            message(R.string.ErrorCpf)
                            positiveButton(R.string.ok)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<CepUser>?, t: Throwable?) {


            }

        })
    }

    fun getNovoEndereco(view: View){

        var activity = context as CardapioActivity

        var s = RetrofitInitializer().serviceEnderecoUser()

        var enderecoPrincipal = view.endereco_usuario_cadastro.text.toString() + " - " + view.numero_casa_usuario_cadastro.text.toString()
        var cepUsuario = view.cep_usuario_cadastro.text.toString()
        var usuarioPrincipal = activity.getUserEmail()

        var call = s.postNewEndereco(enderecoPrincipal,cepUsuario,usuarioPrincipal)

        call.enqueue(object : retrofit2.Callback<EnderecoUser>{
            override fun onResponse(call: Call<EnderecoUser>?, response: Response<EnderecoUser>?) {
                response?.let {
                    if (it.code() == 200){

                        MaterialDialog(activity).show {
                            title(R.string.sucesso)
                            message(R.string.CadastroEndereco)
                            positiveButton(R.string.ok)
                        }

                        activity.setFragment(EnderecoFragment(), "EnderecoFragment")

                    }
                }
            }

            override fun onFailure(call: Call<EnderecoUser>?, t: Throwable?) {
                MaterialDialog(activity).show {
                    title(R.string.sucesso)
                    message(R.string.CadastroEndereco)
                    positiveButton(R.string.ok)
                }

                activity.setFragment(EnderecoFragment(), "EnderecoFragment")
            }

        })

    }

    fun tradeView(view1 : View, view2 : View){

        Handler().postDelayed({
            view1.visibility = View.INVISIBLE
            view2.visibility = View.VISIBLE
        }, 500L)

    }

}