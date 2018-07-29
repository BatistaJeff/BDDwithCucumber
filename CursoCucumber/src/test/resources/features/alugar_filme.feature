# language: pt

Funcionalidade: Alugar Filme
	Como um usuário
	Eu quero cadastrar aluguéis de filmes
	Para controlar preços e datas de entrega
	
Cenário: Deve alugar um filme com sucesso
	Dado um filme com estoque de 2 unidades
	E que o preço do aluguel seja de R$ 3
	Quando alugar
	Então o preço do aluguel será 3
	E a data de entrega será no dia seguinte
	E o estoque do filme será de 1 unidade