package br.com.cotemig.projetofinal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.InformacoesEnderecoUser
import br.com.cotemig.projetofinal.models.Itens
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cardapio.view.*
import kotlinx.android.synthetic.main.item_user_endereco.view.*

class EnderecoAdapter (var context: Context, var table : List<InformacoesEnderecoUser>) :
    RecyclerView.Adapter<EnderecoAdapter.EnderecoHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EnderecoHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_user_endereco, parent, false)
        return EnderecoHolder(view)
    }

    override fun onBindViewHolder(holder: EnderecoHolder, position: Int) {
        holder.bind(context, table[position])
    }

    override fun getItemCount(): Int {
        return table.size
    }

    class EnderecoHolder( itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bind ( context: Context, itens : InformacoesEnderecoUser){

            itemView.endereco_usuario.text = itens.endereco
            itemView.cep_endereco_usuario.text = itens.cep

        }
    }


}
