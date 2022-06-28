#language: pt
	Funcionalidade: Utilizacao da API Trello
			Como entrevistado da Sensedia devo utilizar a API Trello para executar algumas tarefas afim de validar minhas habilidades.


@executarTest
Cenario: Criacao novo board na API da Trello
  Dado que o usuario possua os dados de autenticacao na API da Trello
  Quando solicitar a criacao de um board informando o atributo nome 'Board01'
  Entao a operacao deve ser realizada com sucesso
  
@executarTest
Cenario: Criacao, alteracao e exclusao de um card na API da Trello
  Dado que o usuario possua os dados de autenticacao na API da Trello
  Quando solicitar a criacao de um card informando os atributos nome 'Card01' e descricao 'Esse é o card 01'
  	E realizar uma alteracao do nome para 'Card01-Modify' e descricao 'Card01-desc-modify'
  	E excluir o card criado
  Entao todas as operacoes devem ser realizadas com sucesso
  
@executarTest
Cenario: Remover board na API da Trello
  Dado que o usuario possua os dados de autenticacao na API da Trello
  Quando solicitar a remocao de um board
  Entao a operacao remover board deve ser realizada com sucesso  

