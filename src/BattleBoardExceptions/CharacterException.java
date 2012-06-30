package BattleBoardExceptions;
import Character.Character;
/**
 * @package Este pacote com tem diversas Exceções úteis para tratamento de erros do modelo do jogo.
 */

/**
 * 
 * Representa uma exceção de Character.
 * Este tipo de exceção armazena o Character que gerou a exceção.
 */
public class CharacterException extends Exception{

	private Character mCharacter; ///< o Character que está morto que causou a exceção
	
	/**
	 * Construtor da exceção que deve receber um Character.
	 * @param chr Character que gerou a exceção.
	 */
	public CharacterException(Character chr){
		mCharacter = chr;
	}
	
	/**
	 * Construtor que recebe uma String que vai para a Exception.
	 * Pode ser recuperada pelo método getMessage().
	 * @param str 
	 */
	public CharacterException(String str) {
		super(str);
	}
	
	/**
	 * 
	 * @return o Character que causou a exceção.
	 */
	public Character getCharacter(){
		return mCharacter;
	}

	
}
