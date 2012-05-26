package Item;

public class Armor extends Item{
	
	//Atritubos de Armadura
	protected int mFlexibility;	///< Flexibilidade da Armadura
	
	/**
	 * Construtor da classe Armor
	 * @param name Nome da armadura
	 * @param price Preco da armadura
	 * @param def Pontos de defesa
	 * @param flex Flexibilidade da armadura
	 */
	public Armor(String name, double price, int def, int flex) {
		super(name, price, 0, def);
		mFlexibility = flex;
		mAttackPts = 0;
	}

	//GETTERS
	/**
	 * 
	 * @return Flexibilidade
	 */
	public int getFlexibility(){
		return mFlexibility;
	}
	
	/**
	 * Sobreescrita para imprimir também a Flexibilidade da Armadura.
	 */
	public void print(){
		System.out.print("Armor FLEX: " + mFlexibility+", ");
		super.print();
	}
}
