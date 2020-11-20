package br.com.cotemig.projetofinal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.Account
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.email
import kotlinx.android.synthetic.main.activity_sign_up.password
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signup.setOnClickListener {
            signupClick()
        }

        logar.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish()
        }

    }

    fun signupClick() {

        var senha = password.text.toString()
        var confirmacao = confirm.text.toString()

        if (senha == confirmacao) {

            var s = RetrofitInitializer().serviceAccount()

            var account = Account()
            account.name = nome.text.toString()
            account.email = email.text.toString()
            account.password = password.text.toString()

            var call = s.create(account)

            call.enqueue(object : retrofit2.Callback<Account> {
                override fun onResponse(call: Call<Account>?, response: Response<Account>?) {
                    response?.let {

                        //Caso de sucesso
                        if (it.code() == 200) {
                            MaterialDialog(this@SignUpActivity).show {
                                title(R.string.sucesso)
                                message(R.string.UserCreated)
                                positiveButton(R.string.ok)

                                // Chama tela para realizar o login
                                var intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                                startActivity(intent)

                                finish()

                            }
                        } else {
                            MaterialDialog(this@SignUpActivity).show {
                                title(R.string.title)
                                message(R.string.UserAlreadyExist)
                                positiveButton(R.string.ok)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Account>?, t: Throwable?) {
                    MaterialDialog(this@SignUpActivity).show {
                        title(R.string.title)
                        message(R.string.erro_generico)
                        positiveButton(R.string.ok)
                    }
                }


            })
        } else {
            MaterialDialog(this@SignUpActivity).show {
                title(R.string.title)
                message(R.string.InvalidPassword)
                positiveButton(R.string.ok)
            }
        }

    }
}
