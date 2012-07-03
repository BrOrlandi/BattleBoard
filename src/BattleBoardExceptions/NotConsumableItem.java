package BattleBoardExceptions;

/**
 * 
 * Exceção quando o item não é um Consumable.
 *
 */
public class NotConsumableItem extends Exception{
	public NotConsumableItem(String str){
		super(str);
	}

}