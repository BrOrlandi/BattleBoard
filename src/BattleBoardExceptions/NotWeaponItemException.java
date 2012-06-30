package BattleBoardExceptions;

/**
 * 
 * Exceção quando o item não é uma Arma.
 *
 */
public class NotWeaponItemException extends Exception{
	public NotWeaponItemException(String str){
		super(str);
	}

	public NotWeaponItemException() {}

}
