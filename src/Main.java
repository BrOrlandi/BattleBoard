//package main;

import BattleBoardExceptions.OccupiedBoardPositionException;
import Character.*;
import Item.*;
import Overview.*;

/** @mainpage Decisões de projeto.
* @section intro_sec Alterações das especificações originais do projeto:
* Tais decisões foram tomadas visando solucionar questões de acesso aos dados de classes diferentes. E também quanto à futuras funcionalidades a serem implementadas.
* - adicionado o atributo MaxHP ao Character pois permite ver se o personagem esta com HP máximo.

- há um método print() para várias classes que imprime suas características. O método toString() foi sobrescrito nestas classes e é usado pelo print().

- o método attackCharacter() retorna true se o ataque foi realizado e false se o ataque não foi realizado devido a um dos motivos:

	- o atacante não está vivo.
	- os personagens são do mesmo time.
	- a vítima já está morta.
	- o atacante não pode alcançar a vítima com sua arma.
O miss é considerado uma tentativa de ataque apesar do atacante ter errado a vítima.
@see Character.Character.attackCharacter()
- o método dropItem() retorna o Item que foi removido ou null se o item não está no inventário.

- o método giveItem() recebe como parâmetro a chave do Item e também um Character que é o personagem que receberá o item. Retorna boolean, true se o item foi cedido com sucesso ou false caso o item não esteja no inventário.

- foi feito uma sobrecarga do método addItem() do Character permitindo que o personagem receba um item de outro sem que tenha que ser setada uma chave pelo doador. Neste caso a chave do item será a primeira chave livre no inventário.

- o método setConsumable() recebe um inteiro, que é a chave do item, como parâmetro, e seta um objeto do inventário, se ele é Consumable, como item consumível do personagem. Ou seja, o item consumível deve vir obrigatoriamente do inventário. Retorna true se foi setado com sucesso ou false no caso do objeto não estar no inventário ou não ser consumível.

- o método useConsumable() foi sobrecarregado na classe Character, ele pode ser usado sem parâmetros, o item consumível será aplicado ao próprio personagem, e pode receber um Character como parâmetro, neste caso o consumível será aplicado ao outro personagem com verificação de distância entre os jogadores.

- método isDead() para a classe Character para verificar se o personagem está morto.

- A classe Character possui um atributo Weapon e Armor, ambos são a arma e a armadura que o personagem está usando em combate, o ataque dependerá do alcance desta arma e dos seus pontos de ataque, similarmente a defesa dependerá da armadura que o personagem estiver usando. Dessa forma os pontos de ataque não são mais calculados somando todos os itens do inventário. Se o personagem não estiver usando arma ou armadura, seus pontos de ataque e defesa serão calculados apenas pelos seus atributos.

- Ao setar um item consumível, ou arma, ou armadura, se o Character já possuir um item deste tipo setado, este item retorna ao inventário e o novo item, do inventário, é setado. Permitindo que o personagem troque de arma, consumível e armadura sem perder estes itens.

- O método attackCharacter recebe um Board onde ocorre o ataque. A partir do Board é possível verificar a distancia entre os personagens e também se eles estão no Board, para permitir um ataque.

- a classe Character possui um atributo Color para identificar qual é a cor do personagem e assim identificar a qual time pertence.

- useConsumable() e giveItem() só podem ser aplicado entre personagens do mesmo time.

- attackCharacter(), dropItem(), giveItem(), setConsumable(), setWeapon(), setArmor(), useConsumable(), são métodos que só podem ser realizados por Characters que estiverem vivos.

- toString() do Character também informa se o personagem está morto.

- giveItem() retorna um inteiro que representa a chave do item no inventário do personagem que recebeu o item, ou retorna um número negativo caso não tenha cedido o item com sucesso.*/

public class Main {
	
	public static void main (String args[])
	{	
		
		Team alpha = new Team("Alpha",Color.Red);
		Team bravo = new Team("Bravo",Color.Blue);

		Fighter h1 = new Fighter("Hero Fighter",80);
		h1.setConstitution(10);
		h1.setStrength(30);
		h1.setDexterity(50);
		h1.setSpeed(10);
		Fighter f1 = new Fighter("Fighter 1",50);
		f1.setConstitution(30);
		f1.setDexterity(30);
		f1.setSpeed(20);
		f1.setStrength(20);
	    Ranger r1 = new Ranger("Ranger 1", 50);
	    r1.setStrength(40);
	    r1.setSpeed(40);
	    r1.setDexterity(10);
	    r1.setConstitution(10);

		Ranger h2 = new Ranger("Hero Ranger",80);
		h2.setConstitution(20);
		h2.setStrength(20);
		h2.setDexterity(50);
		h2.setSpeed(10);
		Fighter f2 = new Fighter("Fighter 2",40);
		f2.setConstitution(10);
		f2.setDexterity(10);
		f2.setSpeed(40);
		f2.setStrength(40);
	    Ranger r2 = new Ranger("Ranger 2", 40);
	    r2.setStrength(20);
	    r2.setSpeed(20);
	    r2.setDexterity(30);
	    r2.setConstitution(30);

	    alpha.addCharacter(h1);
	    alpha.addCharacter(f1);
	    alpha.addCharacter(r1);
	    bravo.addCharacter(h2);
	    bravo.addCharacter(f2);
	    bravo.addCharacter(r2);
		
	    
		
		//Items
	    Weapon w1 = new Weapon("Iron Sword",100.0, 30,2);
	    Weapon w2 = new Weapon("Iron Spear",70.0, 25,3);
	    Weapon w3 = new Weapon("Bow",50.0, 20,10);
	    Weapon w4 = new Weapon("Silver Axe",200.0, 40,2);
	    
	    Weapon w5 = new Weapon("Silver Sword",200.0, 50,2);
	    Weapon w6 = new Weapon("Silver Spear",180.0, 40,3);
	    Weapon w7 = new Weapon("Long Bow",90.0, 30,10);
	    Weapon w8 = new Weapon("Golden Axe",300.0, 70,2);

	    Armor a1 = new Armor("Bronze Armor", 150.0, 15, 5);
	    Armor a2 = new Armor("Iron Armor", 90.0, 10, 2);
	    Armor a3 = new Armor("Wooden Shield", 50.0, 5, 10);
	    Armor a4 = new Armor("Bronze Shield", 120.0, 10, 12);
	    
	    Armor a5 = new Armor("Silver Armor", 200.0, 22, 8);
	    Armor a6 = new Armor("Golden Armor", 260.0, 30, 7);
	    Armor a7 = new Armor("Black Shield", 100.0, 20, 10);
	    Armor a8 = new Armor("Silver Shield", 120.0, 23, 12);
	    
	    Item i1 = new Item("Medal of Luck",30.0);
	    Item i2 = new Item("Gold Bar",50.0);
	    
	    HealthPotion hp1 = new HealthPotion("HP+30", 30.0, 30);
	    HealthPotion hp2 = new HealthPotion("HP+20", 20.0, 20);
	    HealthPotion hp3 = new HealthPotion("HP+20", 20.0, 20);
	    HealthPotion hp4 = new HealthPotion("HP+40", 40.0, 40);
	    HealthPotion hp5 = new HealthPotion("HP+60", 60.0, 60);
	    HealthPotion hp6 = new HealthPotion("HP+60", 60.0, 60);
	    RevivePotion rp1 = new RevivePotion("Full Revive", 200.0, 100);
	    RevivePotion rp2 = new RevivePotion("Half Revive", 100.0, 50);
	    RevivePotion rp3 = new RevivePotion("Undead Potion", 20.0, 30);
	    RevivePotion rp4 = new RevivePotion("Undead Potion", 20.0, 30);
	    RevivePotion rp5 = new RevivePotion("Hero Revive", 300.0, 200);
	    RevivePotion rp6 = new RevivePotion("Hero Revive", 300.0, 200);

	    try{
	    h1.addItem(1,w5);
	    h1.addItem(2,a5);
	    h1.addItem(3,i1);
	    h1.addItem(4,hp5);
	    h1.addItem(5,rp5);
	    h1.setWeapon(1);
	    h1.setArmor(2);
	    h1.setConsumable(4);
	    pline();
	    
	    f1.addItem(1,w1);
	    f1.addItem(2,a1);
	    f1.addItem(3,hp1);
	    f1.addItem(4,rp3);
	    f1.addItem(5, a7);
	    
	    f1.setWeapon(1);
	    f1.setArmor(2);
	    f1.setConsumable(3);
	    pline();
	    
	    r1.addItem(1,w2);
	    r1.addItem(2,a2);
	    r1.addItem(3,hp2);
	    r1.addItem(4,rp1);
	    r1.addItem(5, w6);
	    r1.setWeapon(1);
	    r1.setArmor(2);
	    r1.setConsumable(4);
	    pline();
	    
	    h2.addItem(1, w7);
	    h2.addItem(2, a8);
	    h2.addItem(3,i2);
	    h2.addItem(4,hp6);
	    h2.addItem(5,rp6);
	    h2.setWeapon(1);
	    h2.setArmor(2);
	    h2.setConsumable(4);
	    pline();

	    f2.addItem(1,w3);
	    f2.addItem(2,a3);
	    f2.addItem(3,hp3);
	    f2.addItem(4,rp4);
	    f2.addItem(5, w8);
	    f2.setWeapon(1);
	    f2.setArmor(2);
	    f2.setConsumable(3);
	    pline();
	    
	    r2.addItem(1, w4);
	    r2.addItem(2, a4);
	    r2.addItem(3, hp4);
	    r2.addItem(4, rp2);
	    r2.addItem(5, a6);
	    r2.setWeapon(1);
	    r2.setArmor(2);
	    r2.setConsumable(4);
	    pline();
	    }catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    
	    Board board = new Board(4,4);
	    board.addTeam(alpha);
	    board.addTeam(bravo);
	    

	    pline();
	    alpha.print();
	    pline();
	    bravo.print();
	    pline();
	    
	    pline();
	    pline();	    

	    h1.print();
	    f1.print();
	    r1.print();
	    h2.print();
	    f2.print();
	    r2.print();

	    try{
	    board.setCharacterPosition(0,3, h1);
	    board.setCharacterPosition(0,0, f1);
	    board.setCharacterPosition(0,2, r1);
	    board.setCharacterPosition(2,2, h2);
	    board.setCharacterPosition(3,3, f2);
	    board.setCharacterPosition(2,0, r2);
	    }catch (OccupiedBoardPositionException e) {
			System.out.println(e.getMessage());
		}
/**
	    pline();
	    System.out.println("Todos tentam atacar os inimigos = 9 ataques.");
	    pline();

	    try{
	    h1.attackCharacter(h2, board);
	    h1.attackCharacter(f2, board);
	    h1.attackCharacter(r2, board);

	    f1.attackCharacter(h2, board);
	    f1.attackCharacter(f2, board);
	    f1.attackCharacter(r2, board);

	    r1.attackCharacter(h2, board);
	    r1.attackCharacter(f2, board);
	    r1.attackCharacter(r2, board);

	    h2.attackCharacter(h1, board);
	    h2.attackCharacter(f1, board);
	    h2.attackCharacter(r1, board);

	    f2.attackCharacter(h1, board);
	    f2.attackCharacter(f1, board);
	    f2.attackCharacter(r1, board);

	    r2.attackCharacter(h1, board);
	    r2.attackCharacter(f1, board);
	    r2.attackCharacter(r1, board);
	    
	    pline();
	    System.out.println("|||| Hero Fighter atacará Hero Ranger até a morte: ||||");
	    while(!h2.isDead() && h1.attackCharacter(h2, board));
	    System.out.println("|||| Hero Fighter ganhou XP por matar e agora será mais forte ||||");
	    pline();
	    System.out.println("|||| Ranger 2 usa uma poção para reviver Hero Ranger ||||");
	    r2.useConsumable(h2, board);
	    h2.useConsumable();
	    pline();
	    System.out.println("|||| Hero Ranger não possui armas fortes para combater Hero Fighter ||||");
	    h2.print();
	    int w = f2.giveItem(5, h2);
	    int a = r2.giveItem(5, h2);
	    h2.setWeapon(w);
	    h2.setArmor(a);
	    pline();
	    System.out.println("|||| Fighter 2 e Ranger 2, companheiros de Hero Ranger lhe deram itens mais fortes  ||||");
	    pline();
	    h2.attackCharacter(h1, board);
	    h1.useConsumable();
	    while(!h1.isDead() && h2.attackCharacter(h1, board));
	    pline();
	    System.out.println("|||| Hero Ranger se vingou de Hero Fighter  ||||");
	    pline();
	    System.out.println("|||| Rangers e Fighters continuam lutando entre si  ||||");
	    while(!alpha.allDead() && !bravo.allDead())
	    {
		    f1.attackCharacter(f2, board);
		    f1.attackCharacter(r2, board);

		    r1.attackCharacter(f2, board);
		    r1.attackCharacter(r2, board);

		    f2.attackCharacter(f1, board);
		    f2.attackCharacter(r1, board);

		    r2.attackCharacter(f1, board);
		    r2.attackCharacter(r1, board);
		    pline();
	    }
	    System.out.println("|||| A batalha acabou!  ||||");
	    System.out.println("|||| Alpha Team:  ||||");
	    System.out.println(alpha.getResults());
	    System.out.println("|||| Bravo Team:  ||||");
	    System.out.println(bravo.getResults());
	    }catch(Exception e)
	    {
	    	System.out.println(e.getMessage());
	    }
	    
	    //*/
	}
	//*/
	public static void pline(){
		System.out.println("------------------------------------------------------");
	}

}
