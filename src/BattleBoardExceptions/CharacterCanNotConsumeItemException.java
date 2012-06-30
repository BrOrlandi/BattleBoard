package BattleBoardExceptions;

/**
 * 
 * Exceção quando um personagem não pode consumir um item.
 *
 */
public class CharacterCanNotConsumeItemException extends Exception{
	public CharacterCanNotConsumeItemException(String str){
		super(str);
	}

}
