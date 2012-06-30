package BattleBoardExceptions;

/**
 * 
 * Exceção de posição do tabuleiro ocupada por outro personagem.
 *
 */
public class OccupiedBoardPositionException extends Exception {

	public OccupiedBoardPositionException(){}
	public OccupiedBoardPositionException(String str){super(str);}
}
