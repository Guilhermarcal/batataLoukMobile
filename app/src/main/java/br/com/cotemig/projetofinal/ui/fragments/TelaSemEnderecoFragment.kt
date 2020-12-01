package br.com.cotemig.projetofinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.ui.activities.CardapioActivity
import kotlinx.android.synthetic.main.fragment_tela_sem_endereco.view.*

class TelaSemEnderecoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_tela_sem_endereco, container, false)

        view.btn_cadastrar_endereco.setOnClickListener {
            var activity = context as CardapioActivity
            activity.setFragment(CadastrarEnderecoFragment(), "CadastrarEnderecoFragment")
        }

        // Inflate the layout for this fragment
        return view
    }

}