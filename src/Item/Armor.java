package Item;

/**
 * É um item específico do jogo.
 * Possui Pontos de Defesa e Flexibilidade.
 */
public class Armor extends Item{
	
	//Atritubos de Armadura
	protected int mFlexibility;	///< Flexibilidade da Armadura
	protected int mDefensePts;	///< Pontos de defesa do Item
	
	/**
	 * Construtor da classe Armor
	 * @param name Nome da armadura
	 * @param price Preco da armadura
	 * @param def Pontos de defesa
	 * @param flex Flexibilidade da armadura
	 */
	public Armor(String name, double price, int def, int flex) {
		super(name, price);
		mFlexibility = flex;
		
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
	 * 
	 * @return Flexibilidade
	 */
	public int getFlexibility(){
		return mFlexibility;
	}

	/**
	 * @return Pontos de defesa do item
	 */
	public int getDefensepts(){
		return mDefensePts;
	}
	
	/**
	 * Sobreescrita para imprimir também a Flexibilidade da Armadura.
	 */
	public void print(){
		System.out.println(toString());
	}
	
	public String toString(){
		return "Armor:"+ getName() + ", DEF: "+ mDefensePts + ", FLEX: " + mFlexibility+", Price: $" + getPrice();
	}
}
