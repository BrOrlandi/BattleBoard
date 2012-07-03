package BattleBoardExceptions;

/**
 * 
 * Exceção de item não encontrado no inventário de um personagem.
 *
 */
public class ItemNotFoundException extends Exception{
	public ItemNotFoundException(String str){
		super(str);
	}
}
