//package main;

import Character.*;
import Item.*;
import Overview.*;

public class Main {
	
	public static void main (String args[])
	{
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

}
