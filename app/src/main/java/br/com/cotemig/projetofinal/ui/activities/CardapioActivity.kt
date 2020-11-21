package br.com.cotemig.projetofinal.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.Produtos
import br.com.cotemig.projetofinal.services.RetrofitInitializer
import br.com.cotemig.projetofinal.ui.adapters.ProdutosAdapter
import kotlinx.android.synthetic.main.activity_cardapio.*
import retrofit2.Call
import retrofit2.Response

class CardapioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardapio)

        getProdutos()

    }

    fun getProdutos(){

        var s = RetrofitInitializer().serviceProdutos()

        var call = s.getProdutos()

        call.enqueue(object : retrofit2.Callback<Produtos>{
            override fun onResponse(call: Call<Produtos>?, response: Response<Produtos>?) {

                response?.let {

                    if (it.code() == 200){

                        lista_produtos.adapter = ProdutosAdapter(this@CardapioActivity, it.body().produtos)

                    }

                }

            }

            override fun onFailure(call: Call<Produtos>?, t: Throwable?) {

            }

        })
    }

}