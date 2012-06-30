package BattleBoardExceptions;

/**
 * 
 * Representa uma exceção de personagens do mesmo time.
 *
 */
public class CharacterFromSameTeamException extends Exception {
	public CharacterFromSameTeamException(String str) {
		super(str);
	}
}
