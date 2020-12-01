package br.com.cotemig.projetofinal.services

import br.com.cotemig.projetofinal.models.CepUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepUserService{

    @GET("{cep}/json")
    fun getCep(@Path("cep") t: String): Call<CepUser>

}