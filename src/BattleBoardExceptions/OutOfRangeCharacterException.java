package BattleBoardExceptions;

/**
 * 
 * Exceção de personagens fora do alcance.
 *
 */
public class OutOfRangeCharacterException extends Exception{
	public OutOfRangeCharacterException(String str){
		super(str);
	}
}
