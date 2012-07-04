package BattleBoardExceptions;

/**
 * 
 * Exceção quando o item não é uma Armadura.
 *
 */
public class NotArmorItemException extends Exception{
	public NotArmorItemException(String str){
		super(str);
	}

}