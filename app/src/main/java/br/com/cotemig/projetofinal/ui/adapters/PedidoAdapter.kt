package br.com.cotemig.projetofinal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.InformacoesPedido
import kotlinx.android.synthetic.main.item_pedidos.view.*

class PedidoAdapter (var context: Context, var table: List<InformacoesPedido>) :
    RecyclerView.Adapter<PedidoAdapter.PedidoHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_pedidos, parent, false)
        return PedidoHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoHolder, position: Int) {
        holder.bind(table[position])
    }

    override fun getItemCount(): Int {
        return table.size
    }

    class PedidoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(informacoesPedido: InformacoesPedido) {

            itemView.numero_pedido.text = informacoesPedido.id.toString()
            itemView.endereco_pedido.text = informacoesPedido.endereco

        }
    }

}