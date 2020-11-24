package br.com.cotemig.projetofinal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.helpers.SharedPreferencesHelpers
import br.com.cotemig.projetofinal.models.Account
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var user = SharedPreferencesHelpers.readString(this, "userdata", "user")

        user?.let {

            // se ele for vazio vai pra tela de login
            if (it.isEmpty()) {


                Handler(Looper.getMainLooper()).postDelayed({

                    showLoginActivity()

                }, 2000)
            } else {

                login(it)
            }
        }
    }

    fun login(user: String) {

        var s = RetrofitInitializer().serviceAccount()

        var j = JSONObject(user)

        var account = Account()
        account.email = j.getString("email")
        account.password = j.getString("password")

        var call = s.auth(account)

        call.enqueue(object : retrofit2.Callback<Account> {
            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {

                response?.let {
                    if (it.code() == 200) {

                        // login correto abre a tela principal
                        var intent = Intent(this@SplashActivity, CardapioActivity::class.java)
                        startActivity(intent)

                        //salvar usuario

                        // finaliza activity para o usuario nao voltar a tela de login
                        finish()

                    } else {

                        showLoginActivity()

                    }

                }

            }


            override fun onFailure(call: Call<Account>?, t: Throwable?) {
                MaterialDialog(this@SplashActivity).show {
                    title(R.string.title)
                    message(R.string.erro_generico)
                    positiveButton(R.string.ok)
                    positiveButton {
                        showLoginActivity()
                    }
                }

            }


        })

    }

    fun showLoginActivity() {

        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}