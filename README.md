# batataLoukMobile
Desenvolvimento mobile - Kotlin

API's : 

Pegar Produtos :

http://191.234.162.161/batata/produtos/getinfosys.json          // Pegar todos produtos

Endereco : 

http://191.234.162.161/batata/endereco/getinfosys.json?user=1   // Pegar endereço por usuário
http://191.234.162.161/batata/endereco/delete?id=2              // Deletar endereço do usuário
http://191.234.162.161/batata/endereco/postApi?endereco_user=teste - 20&cep=30154878&telefone=31 989898989&usuario=3 // Adicionar um novo endereço para usuário

Pedidos :

http://191.234.162.161/batata/pedidos/getinfosys.json?user=eu           //Filtrar pedidos por usuário
http://191.234.162.161/batata/pedidos/postApi?usuario=eu&endereco=nos   //Adicionar novo pedido com usuário e o endereço

CEP :

https://viacep.com.br/ws/{cep}/json // {cep} -> cep para filtrar
