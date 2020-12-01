package br.com.cotemig.projetofinal.ui.activities

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.helpers.SharedPreferencesHelpers
import br.com.cotemig.projetofinal.models.Account
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            loginClick()
        }

        cadastrar.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

            finish()
        }

        esqueciMinhaSenha.setOnClickListener {
            var intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)

            finish()
        }
    }


    fun loginClick(){

        var s = RetrofitInitializer().serviceAccount()

        var account = Account()
        account.email = email.text.toString()
        account.password = password.text.toString()

        var call = s.auth(account)

        login.visibility = View.GONE

        call.enqueue(object : retrofit2.Callback<Account> {
            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {

                login.visibility = View.VISIBLE

                response?.let {
                    if (it.code() == 200) {

                        // salvar o usuario ao logar
                        saveUser(account)

                        // login correto abre a tela principal
                        var intent = Intent(this@LoginActivity, CardapioActivity::class.java)
                        startActivity(intent)

                        //salvar usuario

                        // finaliza activity para o usuario nao voltar a tela de login
                        finish()

                    } else {

                        //usuario ou senha invalidos
                        MaterialDialog(this@LoginActivity).show {
                            title(R.string.title)
                            message(R.string.UserAndPasswordInvalid)
                            positiveButton(R.string.ok)
                        }

                    }

                }
            }

            override fun onFailure(call: Call<Account>?, t: Throwable?) {

                login.visibility = View.VISIBLE

                MaterialDialog(this@LoginActivity).show {
                    title(R.string.title)
                    message(R.string.erro_generico)
                    positiveButton(R.string.ok)
                }

            }


        })


    }

    fun saveUser(account: Account){

        var j = JSONObject()
        j.put("email", account.email)
        j.put("password", account.password)
        SharedPreferencesHelpers.saveString(this, "userdata", "user", j.toString())


    }
}