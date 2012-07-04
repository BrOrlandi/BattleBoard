package BattleBoardExceptions;
import Character.Character;
/**
 * 
 * Exceção de posição do tabuleiro ocupada por outro personagem.
 *
 */
public class OccupiedBoardPositionException extends CharacterException {

	public OccupiedBoardPositionException(Character chr, String string) {
		super(chr,string);
	}
}
