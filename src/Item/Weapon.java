package Item;

public class Weapon extends Item {
	
	//Atributos
	protected int mRange; 	///< Alcance da arma no tabuleiro
	
	/**
	 * Construtor subclasse Weapon
	 * @param name Nome da Arma
	 * @param price Preco da Arma
	 * @param atk Pontos de Ataque da arma
	 * @param def Pontos de defesa da arma
	 * @param range Alcance da arma no tabuleiro
	 */
	public Weapon(String name, double price, int atk, int def, int range) {
		super(name, price, atk, def);

		//pontos de defesa da subclasse weapon nao pode ser maior que 5
		if (def > 5)
		{
			super.mDefensePts = 5;
		}
		
		//range nao pode ser menor que 1
		if (range < 1)
		{
			mRange = 1;
		}
		else
		{
			mRange = range;
		}
	}
	
	//GETTERS
	/**
	 * 
	 * @return Alcance da arma
	 */
	public int getRange()
	{
		return mRange;
	}
	
	/**
	 * Sobreescrita para imprimir também o Alcance da Arma.
	 */
	public void print(){
		System.out.print("Weapon RNG: " + mRange+", ");
		super.print();
	}
}
