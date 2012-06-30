package BattleBoardExceptions;
import Character.Character;

/**
 * 
 * Exceção quando um personagem não pode consumir um item.
 *
 */
public class CharacterCanNotConsumeItemException extends CharacterException{

	/**
	 * Construtor da exceção que deve receber um Character.
	 * @param chr Character que não pode usar item consumivel.
	 */
	public CharacterCanNotConsumeItemException(Character chr){
		super(chr);
	}

	public CharacterCanNotConsumeItemException(Character chr,
			String str) {
		super(chr,str);
	}

	
}

