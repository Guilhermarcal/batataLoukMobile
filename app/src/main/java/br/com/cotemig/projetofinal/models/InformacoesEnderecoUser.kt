package br.com.cotemig.projetofinal.models

data class InformacoesEnderecoUser(
    var id : Int = 0,
    var endereco : String = "",
    var cep : String = "",
    var usuario : String = ""
)