package Item;

import Character.Character;
/**
 * É um item e é Consumível. Possui pontos de vida para reviver o personagem.
 *
 */
public class RevivePotion extends Item implements Consumable{

	private int mRevivePts; ///< Pontos de vida que o personagem terá quando reviver.
	
	/**
	 * Construtor de RevivePotion
	 * @param name nome do item
	 * @param price preço do item
	 * @param restore pontos de vida que a poção restaurará ao reviver.
	 */
	public RevivePotion(String name, double price, int revivepts) {
		super(name, price);
		mRevivePts = revivepts;
	}

	/**
	 * @return os pontos de vida que dará ao reviver o personagem.
	 */
	public int revive() {
		return mRevivePts;
	}

	/**
	 * @return 0(zero) pois não é uma poção de restauração.
	 */
	public int restore() {
		return 0;
	}

	@Override
	public boolean consumableBy(Character chr) {
		int hp = chr.getHP();
		// Verifica se o personagem está morto para poder reviver com a poção.
		if(hp == 0)
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
			chr.addHP(mRevivePts);
			return true;
		}
		return false;
	}

	/**
	 * Sobreescrita para conter também os pontos de cura.
	 */
	public String toString(){
		return "RevivePotion:"+ getName() + ", HPrevive:"+ mRevivePts+", Price: $" + getPrice();
	}
}
