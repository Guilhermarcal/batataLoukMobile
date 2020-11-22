package br.com.cotemig.projetofinal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.helpers.SharedPreferencesHelpers
import br.com.cotemig.projetofinal.ui.fragments.CardapioFragment
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sair.setOnClickListener {

            /*MaterialDialog(this@MainActivity).show {
                title(R.string.title)
                message(R.string.erro_generico)
                positiveButton(R.string.ok)
                positiveButton {
                    showLoginActivity()
                }       */
            showLoginActivity()

        }

        //setFragment(CardapioFragment(), "CardapioFragment")
    }

    fun showLoginActivity() {

        SharedPreferencesHelpers.delete(this,"userdata", "user")

        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


}