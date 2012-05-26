package Item;

import Character.Character;

public interface Consumable {
	public int revive();
	public int restore();
	public boolean comsumableBy(Character chr);
	public boolean comsume(Character chr);
}
