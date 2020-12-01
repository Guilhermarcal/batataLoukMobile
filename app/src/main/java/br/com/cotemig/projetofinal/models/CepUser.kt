package br.com.cotemig.projetofinal.models

data class CepUser(
    var cep : String = "",
    var logradouro : String = "",
    var bairro : String = "",
    var localidade : String = "",
    var uf : String = ""
)