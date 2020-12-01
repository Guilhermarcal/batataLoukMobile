package br.com.cotemig.projetofinal.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.projetofinal.R
import br.com.cotemig.projetofinal.models.InformacoesEnderecoUser
import br.com.cotemig.projetofinal.models.Itens
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cardapio.view.*
import kotlinx.android.synthetic.main.item_user_endereco.view.*

class EnderecoAdapter(var context : Context, var table : List<InformacoesEnderecoUser>) : BaseAdapter() {

    override fun getCount(): Int {
        return table.size
    }

    override fun getItem(p0: Int): Any {
        return ""
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var view = LayoutInflater.from(context).inflate(R.layout.item_user_endereco, null)

        var endereco = view.findViewById<TextView>(R.id.endereco_usuario)
        var cep = view.findViewById<TextView>(R.id.cep_endereco_usuario)

        endereco.text = table[p0].endereco
        cep.text = table[p0].cep

        return view

    }


}
