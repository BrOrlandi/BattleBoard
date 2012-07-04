package BattleBoardExceptions;
import Character.Character;

/**
 * 
 * Representa uma exceção de personagens do mesmo time.
 *
 */
public class CharacterFromSameTeamException extends CharacterException {

	public CharacterFromSameTeamException(Character chr, String str) {
		super(chr,str);
	}
}
