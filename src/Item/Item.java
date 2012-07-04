package Item;
/**
 * @package Item
 * Representações de Itens do jogo e suas características e especialidades.
 */

/**
 * 
 * Representa um item do jogo que pode ser carregado pelos personagens.
 *
 */
public class Item implements Comparable<Item>{
	
	//Atributos 
	private String mName;		///< Nome do item
	private double mPrice;		///< Preco do item
	
	/**
	 * Construtor de Item
	 * @param name	Nome do item
	 * @param price	 Preco do item
	 * @param atk	Pontos de ataque do item
	 * @param def	Pontos de defesa do item
	 */
	public Item(String name, double price){	
		mName = name;
		mPrice = price;
	}
	
	
	//GETTERS	
	
	/**
	 * @return Nome do item
	 */
	public String getName(){
		return mName;
	}
	
	/**
	 * @return Preco do item
	 */
	public double getPrice(){
		return mPrice;
	}
	
	/**
	 * @return Pontos de ataque do item
	 */
	public int getAttackpts(){
		return 0;
	}
	
	/**
	 * @return Pontos de defesa do item
	 */
	public int getDefensepts(){
		return 0;
	}
	
	/**
	 * @return String com o nome e preço do item.
	 */
	public String toString(){
		return "Item: " + mName + ", Price: $" + mPrice;
	}


	@Override
	public int compareTo(Item o) {
		int compare = 0;
		
		if(this instanceof Armor)
			compare -= 5;
		else if(this instanceof Weapon)
			compare -= 4;
		else if(this instanceof HealthPotion)
			compare -= 3;
		else if(this instanceof RevivePotion)
			compare -= 2;
		else
			compare -= 1;
		
		if(o instanceof Armor)
			compare += 5;
		else if(o instanceof Weapon)
			compare += 4;
		else if(o instanceof HealthPotion)
			compare += 3;
		else if(o instanceof RevivePotion)
			compare += 2;
		else
			compare += 1;
		if(compare == 0)
			compare += this.mName.compareTo(o.mName);

		//System.out.println(this.mName + " compare to " + o.mName + " = " + compare);
		
		return compare;
	}
}
