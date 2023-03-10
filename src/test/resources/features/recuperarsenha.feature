# language: pt
#enconding: UTF-8

@Recuperarsenha @Geral
Funcionalidade: Deve validar a Recuperação de senha
	Como um Usuario
	Eu quero informar o email da conta
	Para receber um email com link parar alteração de senha
	
Cenário: Validação de botoes
	Quando clicar em RecuperarSenha
	Então devo estar na pagina de Recuperacaodesenha 
	Quando RCS clicar em Voltar
	Então devo estar na pagina de Login
	Quando clicar em RecuperarSenha
	Então devo estar na pagina de Recuperacaodesenha 
	Quando RCS clicar em Enviar
	Então sistema notifica campoemail_Geral
	
	
Cenário: Usuario informa email valido cadastrado para recuperação de senha
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456 
	Quando clicar em RecuperarSenha
	Então devo estar na pagina de Recuperacaodesenha 
	Dado que informo o emailrecuperacaosenha userteste1@user.com
	E RCS clicar em Enviar
	Então sistema notifica EmailRecuperacao_Enviado

Cenário: Usuario recupera senha com sucesso atravez do link
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456 
	Dado que usuario solicita recuperação de senha
	Quando acessar o link enviado por email 
	Dado que informo a novasenharecuperacao 654321 e confirmo 654321
	Quando RCS clicar em SR-alterar
	Então sistema notifica senhaalterada_senhareset
	Então devo estar na pagina de Login 
	Dado que informei email userteste1@user.com e senha 654321
	Quando tentar logar
	Então devo estar na pagina de Dashboard
	
	
	

