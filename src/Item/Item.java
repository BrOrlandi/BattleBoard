package Item;

public class Item {
	
	//Atributos 
	private String mName;		///< Nome do item
	private double mPrice;		///< Preco do item
	protected int mAttackPts; 	///< Pontos de Ataque do Item
	protected int mDefensePts;	///< Pontos de defesa do Item
	
	/**
	 * Construtor de Item
	 * @param name	Nome do item
	 * @param price	 Preco do item
	 * @param atk	Pontos de ataque do item
	 * @param def	Pontos de defesa do item
	 */
	public Item(String name, double price, int atk, int def){	
		mName = name;
		mPrice = price;
		//pontos de ataque devem estar entre 5 e 50
		if (atk > 50)
		{
			mAttackPts = 50;
		}else if (atk < 5)
		{
			mAttackPts = 5;
		}
		else
		{
			mAttackPts = atk;
		}
		
		
		//pontos de defesa devem estar entre 1 e 30
		if (def > 30)
		{
			mDefensePts = 30;
		}else if (def < 1)
		{
			mDefensePts = 1;
		}
		else
		{
			mDefensePts = def;
		}
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
		return mAttackPts;
	}
	
	/**
	 * @return Pontos de defesa do item
	 */
	public int getDefensepts(){
		return mDefensePts;
	}
	
	public void print(){
		System.out.println(mName + " : ATK: "+ mAttackPts + ", DEF: " + mDefensePts + ", Price: $" + mPrice);
	}
}
