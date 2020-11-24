package br.com.cotemig.projetofinal.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.helpers.SharedPreferencesHelpers
import br.com.cotemig.projetofinal.ui.activities.CardapioActivity
import br.com.cotemig.projetofinal.ui.activities.LoginActivity
import kotlinx.android.synthetic.main.fragment_user.view.*


class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        var view = inflater.inflate(R.layout.fragment_user, container, false)

        view.sair.setOnClickListener {
            showLoginActivity()
        }

        // Inflate the layout for this fragment
        return view

    }

    fun showLoginActivity() {

        var activity = context as CardapioActivity

        SharedPreferencesHelpers.delete(activity,"userdata", "user")

        var intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)

        //finish()
    }

}