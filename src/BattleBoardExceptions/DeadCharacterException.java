package BattleBoardExceptions;
import Character.Character;

/**
 * 
 * Representa uma exceção de personagem morto.
 *
 */
public class DeadCharacterException extends CharacterException{
	public DeadCharacterException(Character chr){
		super(chr);
	}	
}
