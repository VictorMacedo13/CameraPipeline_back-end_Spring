# language: pt

@Camera @Geral
Funcionalidade: Gerenciar CRUD Camera
	Como um usuario
	Eu quero gerenciar o CRUD de uma ou mais Cameras
	
	Cenário: Usuario Gerencia uma camera com sucesso
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Quando tentar logar
	Então acessar aba Câmeras
	Dado que cliquei no botão AdicionarCamera
	E que informei o nome da camera como Camera1
	E que informei a URL como x
	E que selecionei sim para camera privada
	E que informei a latitude como -90
	E que informei a longitude como 90
	E que informei o limitadore de FPS como 60 
	Quando clicar no botão salvar
	Então sistema notifica CameraSalva_CameraPage
	Então verificar existencia camera Camera1
	Então editar camera Camera1
	E que informei o nome da camera como Camera2
	E que informei a URL como y
	E que selecionei sim para camera privada
	E que informei a latitude como -50
	E que informei a longitude como 50
	E que informei o limitadore de FPS como 90
	Quando clicar no botão salvar 
	Então sistema notifica CameraAtualizada_CameraPage
	Então Desabilitar camera Camera2
	Então excluir camera Camera2
	Então verificar inexistencia camera Camera2
	
	
	Cenário: Validação modal excluir Camera
	* remover usuario userteste1@user.com
	Dado que existe a conta email userteste1@user.com e senha 123456
	Dado que informei email userteste1@user.com e senha 123456
	Dado que tenho uma Camera
	Quando tentar logar
	Então acessar aba Câmeras
	Então verificar existencia camera Camera test
	Então excluir camera Camera test
	Então verificar inexistencia camera Camera test
	
	
