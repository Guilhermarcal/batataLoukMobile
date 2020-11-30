package br.com.cotemig.projetofinal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.projetofinal.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_filtro.view.*

class FiltroAdapter (var context: Context, var table : ArrayList<String>) :
            RecyclerView.Adapter<FiltroAdapter.FiltroHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltroHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_filtro, parent, false)
        return FiltroHolder(view)
    }

    override fun onBindViewHolder(holder: FiltroHolder, position: Int) {
        holder.bind(context, table[position])
    }

    override fun getItemCount(): Int {
        return table.size
    }

    class FiltroHolder( itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind (context: Context, itens : String){
            if(itens == "Batata"){
                Glide.with(context).load(R.drawable.categoria_batata).into(itemView.foto_filtro)
                itemView.nome_filtro.text = "Batata's"
            }else if(itens == "Hamburguer"){
                Glide.with(context).load(R.drawable.categoria_hamb).into(itemView.foto_filtro)
                itemView.nome_filtro.text = "Hamburguer's"
            }else if(itens == "Pizza"){
                Glide.with(context).load(R.drawable.categoria_pizza).into(itemView.foto_filtro)
                itemView.nome_filtro.text = "Pizza's"
            }else if(itens == "Bebidas"){
                Glide.with(context).load(R.drawable.categoria_bebidas).into(itemView.foto_filtro)
                itemView.nome_filtro.text = "Bebida's"
            }
        }
    }

}