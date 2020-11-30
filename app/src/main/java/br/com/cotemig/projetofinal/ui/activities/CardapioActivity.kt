package br.com.cotemig.projetofinal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.ui.fragments.BuscaFragment
import br.com.cotemig.projetofinal.ui.fragments.CardapioFragment
import br.com.cotemig.projetofinal.ui.fragments.PedidoFragment
import br.com.cotemig.projetofinal.ui.fragments.UserFragment
import kotlinx.android.synthetic.main.activity_cardapio.*

class CardapioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardapio)

        setFragment(CardapioFragment(), "CardapioFragment")

        home.setOnClickListener {
            setFragment(CardapioFragment(), "CardapioFragment")
        }

        busca.setOnClickListener {
            setFragment(BuscaFragment(), "BuscaFragment")
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

}