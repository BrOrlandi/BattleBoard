package Item;

/**
 * É um item específico do jogo.
 * Possui Pontos de Ataque e Alcance.
 */
public class Weapon extends Item {
	
	//Atributos
	protected int mRange; 	///< Alcance da arma no tabuleiro
	protected int mAttackPts; 	///< Pontos de Ataque do Item
	
	/**
	 * Construtor subclasse Weapon
	 * @param name Nome da Arma
	 * @param price Preco da Arma
	 * @param atk Pontos de Ataque da arma
	 * @param def Pontos de defesa da arma
	 * @param range Alcance da arma no tabuleiro
	 */
	public Weapon(String name, double price, int atk, int range) {
		super(name, price);

		//pontos de ataque devem estar entre 5 e 50
		if (atk > 50)
		{
			mAttackPts = 50;
		}
		else if (atk < 5)
		{
			mAttackPts = 5;
		}
		else
		{
			mAttackPts = atk;
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
	 * @return Pontos de ataque do item
	 */
	public int getAttackpts(){
		return mAttackPts;
	}
	
	/**
	 * Sobreescrita para imprimir também o Alcance da Arma.
	 */
	public void print(){
		System.out.println(toString());
	}
	
	public String toString(){
		return "Weapon:"+ getName() + ", ATK: "+ mAttackPts + ", RNG: " + mRange+", Price: $" + getPrice();
	}
}
