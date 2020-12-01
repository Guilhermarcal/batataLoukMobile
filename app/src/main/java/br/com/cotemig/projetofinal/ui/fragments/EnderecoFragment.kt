package br.com.cotemig.projetofinal.ui.fragments

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_endereco.view.*
import retrofit2.Call
import retrofit2.Response

class EnderecoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_endereco, container, false)

        getEnderecoUser(view)


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

                            activity.setFragment(TelaSemEnderecoFragment(), "CadastrarEnderecoFragment")

                        }else{

                            view.list_endereco_user.adapter = EnderecoAdapter(activity, it.body().enderecoPessoa)

                            view.list_endereco_user.layoutManager = LinearLayoutManager(
                                activity,
                                LinearLayoutManager.VERTICAL, false
                            )

                        }

                    }
                }
            }

            override fun onFailure(call: Call<EnderecoUser>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }

}