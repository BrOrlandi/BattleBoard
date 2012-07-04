package BattleBoardExceptions;

/**
 * Exceção de posição vazia no tabuleiro.
 *
 */
public class EmptyBoardPositionException extends Exception{

	public EmptyBoardPositionException(String str){
		super(str);
	}
}
