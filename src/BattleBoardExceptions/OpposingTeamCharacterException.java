package BattleBoardExceptions;
import Character.Character;
/**
 * 
 * Exceção de personagem de time diferente.
 *
 */
public class OpposingTeamCharacterException extends CharacterException{

	public OpposingTeamCharacterException(Character chr, String str) {
		super(chr,str);
	}
}
