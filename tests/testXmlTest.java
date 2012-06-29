import java.io.IOException;

import Item.*;
import Utilities.XML;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;

import junit.framework.TestCase;


public class testXmlTest extends TestCase {
	public void test() {
		ItemStore is = new ItemStore("Loja de Items", "Bem-vind o a loja de itens!");
		
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

	    is.addItem(w1);
	    is.addItem(w2);
	    is.addItem(w3);
	    is.addItem(w4);
	    is.addItem(w5);
	    is.addItem(w6);
	    is.addItem(w7);
	    is.addItem(w8);
	    is.addItem(a1);
	    is.addItem(a2);
	    is.addItem(a3);
	    is.addItem(a4);
	    is.addItem(a5);
	    is.addItem(a6);
	    is.addItem(a7);
	    is.addItem(a8);
	    is.addItem(i1);
	    is.addItem(i2);
	    is.addItem(hp1);
	    is.addItem(hp2);
	    is.addItem(hp3);
	    is.addItem(hp4);
	    is.addItem(hp5);
	    is.addItem(hp6);
	    is.addItem(rp1);
	    is.addItem(rp2);
	    is.addItem(rp3);
	    is.addItem(rp4);
	    is.addItem(rp5);
	    is.addItem(rp6);
	    //*/
	    String[] items = is.getItemsString();
	    for(int i=0;i<items.length;i++)
	    {
	    	System.out.println(items[i]);
	    }

		try {
			XML.toXML(is, "ItemStore.xml");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		ItemStore is2 = null;
		try {
			is2 = (ItemStore) XML.fromXML("ItemStore.xml");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		String[] items2 = is2.getItemsString();
	    for(int i=0;i<items2.length;i++)
	    {
	    	System.out.println(items2[i]);
	    }
	}
}
