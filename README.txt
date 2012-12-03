Sistema de Reserva de Equipamentos e Salas

Sistema em desenvolvimento para a solução do problema de falta de monitoramento do uso do espaço físico da Faculdade do Gama (UnB - FGA).


Tutorial GitHub

Há duas formas de utilizar o GitHub, pelo Bash(Terminal, funciona igualmente ao terminal do Ubuntu):

>>>Git Bash: http://git-scm.com/download/win

E pelo programa do GitHub para Windows(juntamente com o próprio site do GitHub):

>>>GitHub Windows: http://github-windows.s3.amazonaws.com/GitHubSetup.exe


Passo a Passo para ter o SisRES no PC:

1) Crie uma conta no github.com

2) Dê um Fork no repositório:

	https://github.com/MatheusFaria/SisRES

	>> Para isso clique no botão de Fork, que fica na linha onde está o nome do repositório.

3) Clone o Repositório:
	$ git clone https://github.com/seuNome/SisRES.git
	>> Vai clonar para a pasta onde se encontra o terminal

	>>O mesmo processo pode ser feito:
		>>Clicando em "Clone in Windows" no site
		>>Clicando em "clone" na Home do programa do GitHub


4) Configurar o acesso as mudanças do repositório (apenas pro Bash):
	$ cd SisRES
	$ git remote add upstream https://github.com/seuNome/SisRES.git



Publicar mudanças no próprio repositório online:

	$ git push origin master
	
	>> No programa: após a mudanças aparecerá no canto superior direito o aviso para realizar a commit, dê um nome ao tópico da commit e uma descrição, depois clique em commit.



Pull Request
Publicar mudanças em outros repositórios (para atualizar todo mundo):

	>> No site: vá no botão de "Pull Request" (fica ao lado de watch), no campo esquerdo é o repositório de destino e o direito é o fonte. Para publicar suas mudanças, o seu repo deve estar na direita e o meu deve estar na esquerda. Depois escreva sobre o que é o Pull Request e clique em "Send Pull Request"

Pegar as novidade do repositório original (que vc deu um Fork):
	$ git fetch upstream
	$ git merge upstream/master

	>> No site: mesmo jeito do Pull Request, só que nesse o repositório direito vai ser o seu e o esquerdo vai ser o meu.


Dúvidas? https://help.github.com/