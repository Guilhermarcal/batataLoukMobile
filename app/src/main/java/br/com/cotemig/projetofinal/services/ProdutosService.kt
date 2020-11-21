package br.com.cotemig.projetofinal.services

import br.com.cotemig.projetofinal.models.Produtos
import retrofit2.Call
import retrofit2.http.GET

interface ProdutosService{

    @GET("produtos/getinfosys.json")
    fun getProdutos() : Call<Produtos>

}