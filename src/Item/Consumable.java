package Item;

import Character.Character;

/**
 * É uma interface que caracteriza um objeto como consumível.
 * Pode restaurar pontos de vida de um personagem ou revive-lo dependendo da classe que o implementa.
 *
 */
public interface Consumable {
	public int revive();
	public int restore();
	public boolean comsumableBy(Character chr);
	public boolean comsume(Character chr);
}
