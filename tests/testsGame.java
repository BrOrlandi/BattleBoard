import java.io.IOException;

import com.thoughtworks.xstream.XStreamException;

import Game.Game;
import junit.framework.TestCase;
import Item.*;
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
	}
}
