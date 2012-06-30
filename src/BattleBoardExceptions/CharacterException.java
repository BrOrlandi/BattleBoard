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
	 * Construtor da exception
	 * Recebe uma string que pode ser recuperada pelo método getMessage().
	 * @param character um Character que causou a Exception
	 * @param string uma mensagem.
	 */
	public CharacterException(Character character, String string) {
		super(string);
	}

	/**
	 * 
	 * @return o Character que causou a exceção.
	 */
	public Character getCharacter(){
		return mCharacter;
	}

	
}
