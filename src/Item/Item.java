package Item;

/**
 * 
 * Representa um item do jogo que pode ser carregado pelos personagens.
 *
 */
public class Item {
	
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
	
	public void print(){
		System.out.println(mName + ", Price: $" + mPrice);
	}
}
