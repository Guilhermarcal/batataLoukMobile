package br.com.cotemig.projetofinal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.Itens
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cardapio.view.*

class ProdutosAdapter (var context: Context, var table : List<Itens>, var listener: ProdutosAdapterListener) :
    RecyclerView.Adapter<ProdutosAdapter.ProdutosHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_cardapio, parent, false)
        return ProdutosHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutosHolder, position: Int) {
        holder.bind(context, table[position], listener)
    }

    override fun getItemCount(): Int {
        return table.size
    }

    class ProdutosHolder( itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind (context: Context, itens : Itens, listener: ProdutosAdapterListener){

            itemView.nome_produto.text = itens.nome
            itemView.preco_produto.text = itens.preco.toString()
            Glide.with(context).load(itens.foto).into(itemView.imagem_produto)

            itemView.btn_adicionar.setOnClickListener {
                listener.carrinho(itens)
            }

        }
    }

    interface ProdutosAdapterListener {

        fun carrinho(itens : Itens)

    }

}