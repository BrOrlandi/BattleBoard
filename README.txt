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

- foi feito uma sobrecarga do método addItem do personagem permitindo que o personagem receba um item doado por outro jogador. Neste caso a chave do item será o tamanho do iventário + 1.

- o método setConsumable() recebe um int como parametro, e seta um objeto do inventário, se ele é consumível, como item consumivel do personagem. Ou seja, o item consumível deve vir obrigatoriamente do inventário. Retorna true se foi setado com sucesso ou false no caso do objeto não estar no inventário ou não ser consumível.

