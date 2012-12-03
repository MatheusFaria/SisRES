Sistema de Reserva de Equipamentos e Salas

Sistema em desenvolvimento para a solu��o do problema de falta de monitoramento do uso do espa�o f�sico da Faculdade do Gama (UnB - FGA).


Tutorial GitHub

H� duas formas de utilizar o GitHub, pelo Bash(Terminal, funciona igualmente ao terminal do Ubuntu):

>>>Git Bash: http://git-scm.com/download/win

E pelo programa do GitHub para Windows(juntamente com o pr�prio site do GitHub):

>>>GitHub Windows: http://github-windows.s3.amazonaws.com/GitHubSetup.exe


Passo a Passo para ter o SisRES no PC:

1) Crie uma conta no github.com

2) D� um Fork no reposit�rio:

	https://github.com/MatheusFaria/SisRES

	>> Para isso clique no bot�o de Fork, que fica na linha onde est� o nome do reposit�rio.

3) Clone o Reposit�rio:
	$ git clone https://github.com/seuNome/SisRES.git
	>> Vai clonar para a pasta onde se encontra o terminal

	>>O mesmo processo pode ser feito:
		>>Clicando em "Clone in Windows" no site
		>>Clicando em "clone" na Home do programa do GitHub


4) Configurar o acesso as mudan�as do reposit�rio (apenas pro Bash):
	$ cd SisRES
	$ git remote add upstream https://github.com/seuNome/SisRES.git



Publicar mudan�as no pr�prio reposit�rio online:

	$ git push origin master
	
	>> No programa: ap�s a mudan�as aparecer� no canto superior direito o aviso para realizar a commit, d� um nome ao t�pico da commit e uma descri��o, depois clique em commit.



Pull Request
Publicar mudan�as em outros reposit�rios (para atualizar todo mundo):

	>> No site: v� no bot�o de "Pull Request" (fica ao lado de watch), no campo esquerdo � o reposit�rio de destino e o direito � o fonte. Para publicar suas mudan�as, o seu repo deve estar na direita e o meu deve estar na esquerda. Depois escreva sobre o que � o Pull Request e clique em "Send Pull Request"

Pegar as novidade do reposit�rio original (que vc deu um Fork):
	$ git fetch upstream
	$ git merge upstream/master

	>> No site: mesmo jeito do Pull Request, s� que nesse o reposit�rio direito vai ser o seu e o esquerdo vai ser o meu.


D�vidas? https://help.github.com/