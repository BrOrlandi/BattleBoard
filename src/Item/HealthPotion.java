package Item;

import Character.Character;
/**
 * É um item e é consumível. Este item é uma poção de HP que restaura os pontos de vida de um personagem.
 *
 */
public class HealthPotion extends Item implements Consumable{

	private int mRestorePts; ///< HP a ser restaurado por esta poção.
	
	/**
	 * Construtor de HealthPotion
	 * @param name nome do item
	 * @param price preço do item
	 * @param restore pontos de vida que a poção restaura.
	 */
	public HealthPotion(String name, double price, int restore) {
		super(name, price);
		mRestorePts = restore;
	}

	/**
	 * @return 0(zero) pois não é uma poção de reviver.
	 */
	public int revive() {
		return 0;
	}

	/**
	 * @return os pontos de vida que a poção irá restaurar.
	 */
	public int restore() {
		return mRestorePts;
	}

	@Override
	public boolean consumableBy(Character chr) {
		int hp = chr.getHP();
		int maxHp = chr.getMaxHP();
		// Verifica se o personagem está vivo e se não esta com o HP máximo.
		if(hp > 0 && hp < maxHp)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean consume(Character chr) {
		if(consumableBy(chr)){
			chr.addHP(mRestorePts);
			return true;
		}
		return false;
	}
	
	/**
	 * Sobreescrita para imprimir também os pontos de cura.
	 */
	public void print(){
		System.out.println(toString());
	}
	
	public String toString(){
		return "HealthPotion:"+ getName() + ", HPrestore:"+ mRestorePts+", Price: $" + getPrice();
	}
}
