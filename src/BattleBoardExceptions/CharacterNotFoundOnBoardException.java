package BattleBoardExceptions;
import Character.Character;

/**
 * 
 * Quando um personagem não foi encontrado na Board.
 *
 */
public class CharacterNotFoundOnBoardException extends CharacterException {

	public CharacterNotFoundOnBoardException(Character chr, String str){
		super(chr,str);
	}
}
