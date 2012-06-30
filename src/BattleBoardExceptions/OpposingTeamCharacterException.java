package BattleBoardExceptions;
/**
 * 
 * Exceção de personagem de time diferente.
 *
 */
public class OpposingTeamCharacterException extends Exception{
	public OpposingTeamCharacterException(String str){
		super(str);
	}
}
