//package main;

import Character.*;
import Item.*;
import Overview.*;

public class Main {
	
	public static void main (String args[])
	{
		Team alpha = new Team("Alpha",Color.Red);
		Team bravo = new Team("Bravo",Color.Blue);

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
		
	    alpha.addCharacter(f1);
	    alpha.addCharacter(r1);
	    bravo.addCharacter(f2);
	    bravo.addCharacter(r2);
		
	    
		
		//Items
	    Weapon w1 = new Weapon("Iron Sword",100.0, 30,2);
	    Weapon w2 = new Weapon("Iron Spear",70.0, 25,3);
	    Weapon w3 = new Weapon("Bow",50.0, 20,10);
	    Weapon w4 = new Weapon("Silver Axe",200.0, 40,2);
	    Armor a1 = new Armor("Bronze Armor", 150.0, 15, 5);
	    Armor a2 = new Armor("Iron Armor", 90.0, 10, 2);
	    Armor a3 = new Armor("Wooden Shield", 50.0, 5, 10);
	    Armor a4 = new Armor("Bronze Shield", 120.0, 10, 12);
	    Item i1 = new Item("Medal of Luck",30.0);
	    Item i2 = new Item("Gold Bar",50.0);
	    HealthPotion hp1 = new HealthPotion("HP+30", 30.0, 30);
	    HealthPotion hp2 = new HealthPotion("HP+20", 20.0, 20);
	    HealthPotion hp3 = new HealthPotion("HP+20", 20.0, 20);
	    HealthPotion hp4 = new HealthPotion("HP+40", 40.0, 40);
	    RevivePotion rp1 = new RevivePotion("Full Revive", 200.0, 100);
	    RevivePotion rp2 = new RevivePotion("Half Revive", 100.0, 50);
	    RevivePotion rp3 = new RevivePotion("Undead Potion", 20.0, 30);
	    RevivePotion rp4 = new RevivePotion("Undead Potion", 20.0, 30);

	    f1.addItem(1,w1);
	    f1.addItem(2,a1);
	    f1.addItem(3,hp1);
	    f1.addItem(4,rp3);
	    f1.addItem(5,i1);
	    
	    r1.addItem(1,w2);
	    r1.addItem(2,a2);
	    r1.addItem(3,hp2);
	    r1.addItem(4,rp1);

	    f2.addItem(1,w3);
	    f2.addItem(2,a3);
	    f2.addItem(3,hp3);
	    f2.addItem(4,rp4);
	    f2.addItem(5,i2);

	    r2.addItem(1, w4);
	    r2.addItem(2, a4);
	    r2.addItem(3, hp4);
	    r2.addItem(4, rp2);
	    
		
	    Board board = new Board(2,2);
	    board.addTeam(alpha);
	    board.addTeam(bravo);

	    pline();
	    alpha.print();
	    pline();
	    bravo.print();
	    pline();

	    f1.print();
	    pline();
	    r1.print();
	    pline();
	    f2.print();
	    pline();
	    r2.print();
	    pline();
	    
	    pline();
	    f1.setConsumable(3);
	    r1.setConsumable(4);
	    f2.setConsumable(3);
	    r2.setConsumable(4);
	    pline();	    
	    
	    while(f1.getHP() > 50)
	    {
	    	r2.attackCharacter(f1);
	    }
	    pline();
	    System.out.println(f1);
	    pline();
	    f1.useConsumable();
	    //f1.useConsumable();
	    pline();
	    System.out.println(f1);
	    while(!f1.isDead())
	    {
	    	r2.attackCharacter(f1);
	    }
//	    pline();
//	    System.out.println(f1);
//	    pline();
//	    r1.useConsumable(f1);
//	    pline();
//	    System.out.println(f1);
	    
	    board.setCharacterPosition(0, f1);
	    board.setCharacterPosition(1, f2);
	    board.setCharacterPosition(2, r1);
	    board.setCharacterPosition(3, r2);
	    pline();
	    
	    System.out.println("TESTE RETORNA CHARACTER######");
	    System.out.println(board.getCharacter(0));
	    System.out.println(board.getCharacter(1));
	    System.out.println(board.getCharacter(2));
	    System.out.println(board.getCharacter(3));
	    
	    System.out.println("TESTE RETORNA Pair<X, Y> ######");
	    System.out.println(board.getCharacterXY(f1));
	    System.out.println(board.getCharacterXY(f2));
	    System.out.println(board.getCharacterXY(r1));
	    System.out.println(board.getCharacterXY(r2));
	    pline();
	    /*
	    //Muita briga!
	    pline();
	    f1.attackCharacter(f2);
	    f1.attackCharacter(r2);
	    r1.attackCharacter(f2);
	    r1.attackCharacter(r2);
	    f2.attackCharacter(f1);
	    f2.attackCharacter(r1);
	    r2.attackCharacter(f1);
	    r2.attackCharacter(r1);
	    pline();
	    f1.attackCharacter(f2);
	    f1.attackCharacter(r2);
	    r1.attackCharacter(f2);
	    r1.attackCharacter(r2);
	    f2.attackCharacter(f1);
	    f2.attackCharacter(r1);
	    r2.attackCharacter(f1);
	    r2.attackCharacter(r1);
	    pline();
	    f1.attackCharacter(f2);
	    f1.attackCharacter(r2);
	    r1.attackCharacter(f2);
	    r1.attackCharacter(r2);
	    f2.attackCharacter(f1);
	    f2.attackCharacter(r1);
	    r2.attackCharacter(f1);
	    r2.attackCharacter(r1);
	    pline();
	    f1.attackCharacter(f2);
	    f1.attackCharacter(r2);
	    r1.attackCharacter(f2);
	    r1.attackCharacter(r2);
	    f2.attackCharacter(f1);
	    f2.attackCharacter(r1);
	    r2.attackCharacter(f1);
	    r2.attackCharacter(r1);
	    pline();
	    //*/
	    
	    
	    
		/**
		//time 1
		Team time1 = new Team("Humans", Color.Blue);
		
		//Personagem e seus itens, time 1
		Fighter fighter1 = new Fighter("Lee Adama", 50);
		fighter1.setConstitution(30);
		fighter1.setDexterity(30);
		fighter1.setSpeed(20);
		fighter1.setStrength(20);
		Weapon weapon1 = new Weapon("HandsA", 50.0, 20, 10);
		Armor armor1 = new Armor("KevlarA", 140.0, 20, 10);
		fighter1.addItem(1,weapon1);
		fighter1.addItem(2,armor1);
		
		//Personagem e seus itens, time1
		Ranger ranger1 = new Ranger("Starbuck", 60);
		ranger1.setConstitution(25);
		ranger1.setDexterity(30);
		ranger1.setSpeed(25);
		ranger1.setStrength(20);
		Weapon weapon2 = new Weapon("MP-334A", 100.0, 30, 10);
		Armor armor2 = new Armor("KevlarB", 100.0, 20, 10);
		ranger1.addItem(1,weapon2);
		ranger1.addItem(2,armor2);
		
		//adicionando personagens ao time1
		time1.addCharacter(fighter1);
		time1.addCharacter(ranger1);
			
		//time2
		Team time2 = new Team("Cylons", Color.Red);
		
		//Personagem e seus itens, time2
		Fighter fighter2 = new Fighter("Toaster", 20);
		fighter2.setConstitution(30);
		fighter2.setDexterity(20);
		fighter2.setSpeed(20);
		fighter2.setStrength(30);
		Weapon weapon3 = new Weapon("HandsB", 50.0, 10, 10);
		Armor armor3 = new Armor("KevlarC", 140.0, 5, 10);
		fighter2.addItem(1,weapon3);
		fighter2.addItem(2,armor3);
		fighter2.addItem(3,new HealthPotion("HP+20 Potion",30.0,20));
		fighter2.setConsumable(3);
		
		//Personagem e seus itens, time2
		Ranger ranger2 = new Ranger("Boomer", 30);
		ranger2.setConstitution(25);
		ranger2.setDexterity(30);
		ranger2.setSpeed(30);
		ranger2.setStrength(15);
		Weapon weapon4 = new Weapon("MP-334B", 120.0, 40, 10);
		Armor armor4 = new Armor("KevlarD", 140.0, 15, 10);
		ranger2.addItem(1,weapon4);
		ranger2.addItem(2,armor4);
		ranger2.addItem(3,new HealthPotion("HP+50 Potion",30.0,50));
		ranger2.setConsumable(3);
		
		//adicionando personagens ao time2
		time2.addCharacter(fighter2);
		time2.addCharacter(ranger2);
		
		//Declarando tabuleiro
		Board Galactica = new Board(100, 100);
		
		//adicionando times ao tabuleiro
		Galactica.addTeam(time1);
		Galactica.addTeam(time2);
		
		time1.print();
		System.out.println("------------------------");
		time2.print();
		System.out.println("------------------------");
		//fighter1 ataca fighter2
		fighter1.print();
		fighter2.print();
		System.out.println("------------------------");
		fighter1.attackCharacter(fighter2);
		fighter1.attackCharacter(fighter2);
		fighter1.attackCharacter(fighter2);
		System.out.println("------------------------");
		fighter2.useConsumable();
		System.out.println("------------------------");
		System.out.println(fighter2);
		System.out.println("------------------------");
		fighter1.attackCharacter(fighter2);
		fighter1.attackCharacter(fighter2);
		fighter1.attackCharacter(fighter2);
		System.out.println("------------------------");
		ranger2.print();
		System.out.println("------------------------");
		System.out.println(fighter2);
		System.out.println("------------------------");
		ranger2.useConsumable(fighter2);
		ranger2.useConsumable(fighter2);
		System.out.println("------------------------");
		ranger2.print();
		System.out.println("------------------------");
		System.out.println(fighter2);
		System.out.println("------------------------");
		System.out.println(fighter2);
		System.out.println("------------------------");
		fighter2.print();
		System.out.println("------------------------");
		
		//ranger2 ataca ranger1
		ranger1.print();
		ranger2.print();
		System.out.println("------------------------");
		ranger2.attackCharacter(ranger1);
		ranger2.attackCharacter(ranger1);
		ranger2.attackCharacter(ranger1);
		ranger2.attackCharacter(ranger1);
		ranger2.attackCharacter(ranger1);
		ranger2.attackCharacter(ranger1);
		ranger2.attackCharacter(ranger1);
		ranger2.attackCharacter(ranger1);
		System.out.println("------------------------");
		ranger1.print();

		System.out.println("------------------------");
	//*/	
		
		//testes das potions
//		RevivePotion hpotion = new RevivePotion("HP++", 20.0, 10);
//		hpotion.print();
//		System.out.println(hpotion.consumableBy(ranger2));
//		System.out.println(hpotion.consumableBy(ranger1));
//		System.out.println(hpotion.consume(ranger1));
//		System.out.println(ranger1);
		
		//testes da função random
//		System.out.println((int)Character.rnd(-5, 5));
//		System.out.println((int)Character.rnd(-5, 5));
//		System.out.println((int)Character.rnd(-5, 5));
//		System.out.println((int)Character.rnd(-5, 5));
		
	}
	
	public static void pline(){
		System.out.println("------------------------------------------------------");
	}

}
