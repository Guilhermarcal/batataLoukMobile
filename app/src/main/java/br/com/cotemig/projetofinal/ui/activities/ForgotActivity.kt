package br.com.cotemig.projetofinal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.Account
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_forgot.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.email
import retrofit2.Call
import retrofit2.Response

class ForgotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        forgot.setOnClickListener {
            forgot()
        }

    }

    fun forgot(){

        var s = RetrofitInitializer().serviceAccount()

        var account = Account()
        account.email = email.text.toString()

        var call = s.forgot(account)

        call.enqueue(object : retrofit2.Callback<Account> {
            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {



                response?.let {
                    if (it.code() == 204) {

                        //Email encontrato
                        MaterialDialog(this@ForgotActivity).show {
                            title(R.string.Atencao)
                            message(R.string.Verifique)
                            positiveButton(R.string.ok)
                            positiveButton {
                                var intent = Intent(this@ForgotActivity, LoginActivity::class.java)
                                startActivity(intent)

                                finish()
                            }
                        }

                    } else {

                        //usuario ou senha invalidos
                        MaterialDialog(this@ForgotActivity).show {
                            title(R.string.title)
                            message(R.string.Forgot)
                            positiveButton(R.string.ok)

                        }

                    }

                }
            }

            override fun onFailure(call: Call<Account>?, t: Throwable?) {


                MaterialDialog(this@ForgotActivity).show {
                    title(R.string.title)
                    message(R.string.erro_generico)
                    positiveButton(R.string.ok)
                }

            }


        })


    }
}