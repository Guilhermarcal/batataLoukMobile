package br.com.cotemig.projetofinal.services

import br.com.cotemig.projetofinal.models.EnderecoUser
import br.com.cotemig.projetofinal.models.Pedido
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PedidoService{

    @GET("pedidos/getinfosys.json")
    fun getPedido(@Query("user") t: String) : Call<Pedido>

    @GET("pedidos/postApi")
    fun postNewPedido(@Query("usuario") usuario: String, @Query("endereco") endereco: String) : Call<Pedido>

}