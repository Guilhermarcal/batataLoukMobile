package br.com.cotemig.projetofinal.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.helpers.SharedPreferencesHelpers
import br.com.cotemig.projetofinal.ui.fragments.*
import kotlinx.android.synthetic.main.activity_cardapio.*
import org.json.JSONObject


class CardapioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardapio)

        setFragment(CardapioFragment.getInstance(this), "CardapioFragment")

        home.setOnClickListener {
            setFragment(CardapioFragment(), "CardapioFragment")
        }

        carrinho.setOnClickListener {
            setFragment(CarrinhoFragment(), "BuscaFragment")
        }

        pedido.setOnClickListener {
            setFragment(PedidoFragment(), "PedidoFragment")
        }

        user.setOnClickListener {
            setFragment(UserFragment(), "UserFragment")
        }

    }

    fun setFragment(f: Fragment, name: String){

        //iniciando a transação para trocar de conteúdo da tela (fragment)
        val ft = supportFragmentManager.beginTransaction()

        //adicionando o objeto instanciado do fragment
        ft.replace(R.id.content, f)

        //adicionando o fragment com o nome na pilha de fragments
        ft.addToBackStack(name)

        //confirmando a troca do fragment no framelayout
        ft.commit()
    }

    fun getUserEmail(): String {

        var teste = SharedPreferencesHelpers.readString(this, "userdata", "user")

        var j = JSONObject(teste)

        return j.getString("email")
    }

}