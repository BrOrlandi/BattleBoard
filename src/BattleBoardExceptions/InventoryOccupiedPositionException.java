package BattleBoardExceptions;
/**
 * 
 * Exceção de posição do inventário ocupada.
 *
 */
public class InventoryOccupiedPositionException extends Exception {
	public InventoryOccupiedPositionException(String str){
		super(str);
	}
}
