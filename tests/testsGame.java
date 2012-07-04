import Game.Game;
import junit.framework.TestCase;
import Item.*;
import BattleBoardExceptions.EmptyBoardPositionException;
import BattleBoardExceptions.OccupiedBoardPositionException;
import Character.*;
import Character.Character;
import Overview.Color;
import Utilities.XML;


public class testsGame extends TestCase {
	
	/*
	public void test1(){
		Game G = new Game();
		
		// Items
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
	    
	    boolean pegou = false;
	    try{
	    	G.addItemToStore(w1);
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    	pegou = true;
	    }
	    
	    //assertEquals(true, pegou);
	    
	    pegou = false;
	    G.createItemStore("Loja de Itens de Teste!");
	    try{
	    G.addItemToStore(w1);
	    G.addItemToStore(w2);
	    G.addItemToStore(w3);
	    G.addItemToStore(w4);
	    G.addItemToStore(w5);
	    G.addItemToStore(w6);
	    G.addItemToStore(w7);
	    G.addItemToStore(w8);
	    G.addItemToStore(a1);
	    G.addItemToStore(a2);
	    G.addItemToStore(a3);
	    G.addItemToStore(a4);
	    G.addItemToStore(a5);
	    G.addItemToStore(a6);
	    G.addItemToStore(a7);
	    G.addItemToStore(a8);
	    G.addItemToStore(i1);
	    G.addItemToStore(i2);
	    G.addItemToStore(hp1);
	    G.addItemToStore(hp2);
	    G.addItemToStore(hp3);
	    G.addItemToStore(hp4);
	    G.addItemToStore(hp5);
	    G.addItemToStore(hp6);
	    G.addItemToStore(rp1);
	    G.addItemToStore(rp2);
	    G.addItemToStore(rp3);
	    G.addItemToStore(rp4);
	    G.addItemToStore(rp5);
	    G.addItemToStore(rp6);
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    	pegou = true;
	    }
	    //assertEquals(false,pegou);
	    
	}
	//*/
	
	
	public void test2(){
		Game G = new Game();
		G.setPlayerOne("Bruno", "Bravo", Color.Blue);
		G.setPlayerTwo("Vinicius", "Alpha", Color.Red);
		
		Item[] its = G.mItemStore.getItemArray();
		assertNotNull(its);
		for(int i=0;i<its.length;i++){
			System.out.println(its[i]);
		}
		
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

	    G.mJ1.addCharacter(h1);
	    G.mJ1.addCharacter(f1);
	    G.mJ1.addCharacter(r1);
	    G.mJ2.addCharacter(h2);
	    G.mJ2.addCharacter(f2);
	    G.mJ2.addCharacter(r2);

	    System.out.println("Personagens do time "+G.mJ1.getTeam().getName()+"("+G.mJ1.getTeam().getColor()+ ") de "+G.mJ1.getName());
	    Character[] chrs1 = G.mJ1.getCharactersArray();
	    for(int i=0;i<chrs1.length;i++){
	    	System.out.println(chrs1[i]);
	    }
	    System.out.println("Personagens do time "+G.mJ2.getTeam().getName()+"("+G.mJ2.getTeam().getColor()+ ") de "+G.mJ2.getName());
	    Character[] chrs2 = G.mJ1.getCharactersArray();
	    for(int i=0;i<chrs2.length;i++){
	    	System.out.println(chrs2[i]);
	    }

	    try {
			G.mBoard.setCharacterPosition(0, 0, h1);
		    G.mBoard.setCharacterPosition(1, 0, f1);
		    G.mBoard.setCharacterPosition(2, 0, r1);
			G.mBoard.setCharacterPosition(0, 4, h2);
		    G.mBoard.setCharacterPosition(1, 4, f2);
		    G.mBoard.setCharacterPosition(2, 4, r2);
		} catch (OccupiedBoardPositionException e) {
			e.printStackTrace();
		}
	    
	    try {
			G.mBoard.moveUp(0, 0);
		} catch (OccupiedBoardPositionException e) {
			System.out.println("Posição ocupada!");
		} catch (EmptyBoardPositionException e) {
			System.out.println("Posição vazia!");
		}
	}
}
