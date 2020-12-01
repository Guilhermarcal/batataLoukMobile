package br.com.cotemig.projetofinal.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.helpers.SharedPreferencesHelpers
import br.com.cotemig.projetofinal.ui.activities.CardapioActivity
import br.com.cotemig.projetofinal.ui.activities.LoginActivity
import kotlinx.android.synthetic.main.fragment_pedido.view.*
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
        var activity = context as CardapioActivity
        view.email_usuario.text = activity.getUserEmail()

        Anime().tradeView(view.pb_user, view.relative_user)

        // Inflate the layout for this fragment
        return view

    }

    fun showLoginActivity() {

        var activity = context as CardapioActivity

        SharedPreferencesHelpers.delete(activity, "userdata", "user")

        var intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)

        //finish()
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