package br.com.cotemig.projetofinal.models

data class Itens (
    var id : Int = 0,
    var nome : String = "",
    var descricao : String = "",
    var preco : Float = 0.0f,
    var foto : String = ""
)