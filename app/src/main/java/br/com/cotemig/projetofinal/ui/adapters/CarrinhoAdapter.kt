package br.com.cotemig.projetofinal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.Itens
import br.com.cotemig.projetofinal.ui.fragments.CardapioFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cardapio.view.*
import kotlinx.android.synthetic.main.item_cardapio.view.descricao_produto
import kotlinx.android.synthetic.main.item_cardapio.view.imagem_produto
import kotlinx.android.synthetic.main.item_cardapio.view.nome_produto
import kotlinx.android.synthetic.main.item_cardapio.view.preco_produto
import kotlinx.android.synthetic.main.item_carrinho.view.*

class CarrinhoAdapter(var context: Context, var table: List<Itens>) :
    RecyclerView.Adapter<CarrinhoAdapter.CarrinhoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrinhoHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_carrinho, parent, false)
        return CarrinhoHolder(view)
    }

    override fun onBindViewHolder(holder: CarrinhoHolder, position: Int) {
        holder.bind(context, table[position])
    }

    override fun getItemCount(): Int {
        return table.size
    }

    class CarrinhoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, itens: Itens) {


            itemView.nome_produto.text = itens.nome
            itemView.descricao_produto.text = itens.descricao
            itemView.preco_produto.text = itens.preco.toString()
            Glide.with(context).load(itens.foto).into(itemView.imagem_produto)


            itemView.btn_remover.setOnClickListener {
                itemView.item_completo.removeAllViews()
                CardapioFragment().setCarrinho(itens.id)
            }


        }
    }

}

