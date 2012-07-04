package BattleBoardExceptions;
import Character.Character;
/**
 * 
 * Exceção de personagens fora do alcance.
 * Possui um método que permite obter a distancia necessária para que um ataque ou uso de consumable fosse realizado.
 */
public class OutOfRangeCharacterException extends CharacterException{
	private int mRange;
	
	public OutOfRangeCharacterException(Character chr, int range,
			String str) {
		super(chr,str);
		mRange = range;
	}

	public int getRange(){
		return mRange;
	}
}
