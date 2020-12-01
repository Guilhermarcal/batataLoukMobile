package br.com.cotemig.projetofinal.services

import br.com.cotemig.projetofinal.models.EnderecoUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EnderecoService{

    @GET("endereco/getinfosys.json")
    fun getEnderecoPessoa(@Query("user") t: String) : Call<EnderecoUser>

    @GET("endereco/postApi")
    fun postNewEndereco(@Query("endereco_user") endereco_user: String, @Query("cep") cep: String, @Query("usuario") usuario: String) : Call<EnderecoUser>

}