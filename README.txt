Esta é a pasta do projeto para o Eclipse.

Documentação:
http://www.grad.icmc.usp.br/~brorlandi/battleboard/html/index.html

ou
http://www.grad.icmc.usp.br/~brorlandi/
Digitar "poo" na caixa de texto e clicar Acessar.

-----------------------------------------------------------
Decisões de Projeto:

- adicionado o atributo MaxHP ao Character pois permite ver se o personagem esta com HP máximo.

- há um método print() para várias classes que imprime suas caracteristicas. O método toString() foi sobreescrito nestas classes e é usado pelo print().

- o método attackCharacter() retorna boolean, true se a vitima foi atacada e false se a vitima já esta morta.

- o método dropItem() retorna o Item que foi removido ou null se o item não está no inventário.

- o método giveItem() recebe como parametro a chave do item e também um Character que é o personagem que receberá o item. Retorna boolean, true se o item foi cedido com sucesso ou false caso o item não esteja no inventário.

- foi feito uma sobrecarga do método addItem do personagem permitindo que o personagem receba um item doado por outro jogador sem que tenha que ser setada uma chave pelo outro jogador. Neste caso a chave do item será o tamanho do iventário + 1.

- o método setConsumable() recebe um inteiro, que é a chave do item, como parametro, e seta um objeto do inventário, se ele é consumível, como item consumivel do personagem. Ou seja, o item consumível deve vir obrigatoriamente do inventário. Retorna true se foi setado com sucesso ou false no caso do objeto não estar no inventário ou não ser consumível.

- o método useConsumable() foi sobrecarregado na classe Character, ele pode ser usado sem parametros, o item consumível será aplicado ao próprio personagem, e pode receber um Character como parametro, neste caso o consumível será aplicado ao outro personagem com verificação de distancia entre os jogadores.

- método isDead() para a classe Character para verificar se o personagem está morto.

- A classe Character possui um atributo Weapon e Armor, ambos são a arma e a armadura que o personagem está usando em combate, o ataque dependerá do alcance desta arma e dos seus pontos de ataque, similarmente a defesa dependerá da armadura que o personagem estiver usando. Dessa forma os pontos de ataque não são mais calculados somando todos os items do inventário. Se o personagem não estiver usando arma ou armadura, seus pontos de ataque e defesa serão calculados apenas pelos seus atributos.

- Ao setar um item consumível, ou arma, ou armadura, se o Character já possuir um item deste tipo setado, este item retorna ao inventário e o novo item, do inventário, é setado. Permitindo que o personagem troque de arma, consumivel e armadura sem perder estes itens.

- O método attackCharacter recebe um Board onde ocorre o ataque. A partir do Board é possivel verificar a distancia entre os personagens e também se eles estão no Board, para permitir um ataque.

- a classe Character possui um atributo Color para identificar qual é a cor do personagem e assim identificar a qual time pertence.

- useConsumable() e giveItem() só podem ser aplicado entre personagens do mesmo time.

- attackCharacter(), dropItem(), giveItem(), setConsumable(), setWeapon(), setArmor(), useConsumable(), são métodos que só podem ser realizados por Characters que estiverem vivos.

- toString() do Character também informa se o personagem está morto.

- giveItem() retorna um inteiro que representa a chave do item no inventário do personagem que recebeu o item, ou retorna um número negativo caso não tenha cedido o item com sucesso.
