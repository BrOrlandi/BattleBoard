package Utilities;

/**
 * @package Utilities
 * Pacote com classes úteis para o funcionamento do projeto.
 */

/**
 * Representa um par de valores. É uma classe genérica.
 */
public class Pair<A, B> {
	
	private A mFirst; ///< primeiro elemento do par.
	private B mSecond; ///< segundo elemento do par.
	
	/**
	 * Construtor do par de valores.
	 * @param first primeiro valor do par.
	 * @param second segundo valor do par.
	 */
	public Pair(A first, B second)
	{
		mFirst = first;
		mSecond = second;
	}
	
	/**
	 * Seta o primeiro valor do par.
	 * @param first primeiro valor do par.
	 */
	public void setFirst(A first)
	{
		mFirst = first;
	}

	/**
	 * Seta o segundo valor do par.
	 * @param second segundo valor do par.
	 */
	public void setSecond(B second)
	{
		mSecond = second;
	}
	
	/**
	 * Pega o primeiro valor do par
	 * @return o primeiro valor do par
	 */
	public A getFirst()
	{
		return mFirst;
	}

	/**
	 * Pega o segundo valor do par
	 * @return o segundo valor do par
	 */
	public B getSecond()
	{
		return mSecond;
	}
	
	@Override
	public String toString()
	{
		return "(" + getFirst() + "," + getSecond() + ")";
	}
}
