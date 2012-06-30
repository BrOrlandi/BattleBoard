package BattleBoardExceptions;
/**
 * @package Este pacote com tem diversas Exceções úteis para tratamento de erros do modelo do jogo.
 */

/**
 * 
 * Representa uma exceção de personagem morto.
 * Possui um método getCharacter que permite obter o Character que está morto.
 *
 */
public class DeadCharacterException extends Exception{

	private Character mCharacter; ///< o Character que está morto que causou a exceção
	
	/**
	 * Construtor da exceção que deve receber um Character.
	 * @param chr Character morto.
	 */
	public DeadCharacterException(Character chr){
		mCharacter = chr;
	}
	
	/**
	 * 
	 * @return o Character morto que causou a exceção.
	 */
	public Character getCharacter(){
		return mCharacter;
	}

	
}
