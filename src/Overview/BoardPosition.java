package Overview;

import Character.Character;
import Utilities.Pair;

public class BoardPosition implements Comparable<BoardPosition>
{
	/**
	 * Matriz e suas posicoes
	 * Exemplo:
	 * 0 1 2
	 * 3 4 5
	 * 
	 * Posição (1,2) = 5 -> x*largura + y.
	 */
	
	private int mPosition; 	///< Posicao na matriz, usado para comparação na ordenação do Set
	private int mMod;  		///< Largura do tabuleiro, usado pra encontrar o pos a partir de X e Y
	private Pair<Integer, Integer> mXY; ///< Coordenadas X e Y do personagem no Tabuleiro.
	//personagem que ocupa a posicao x, y
	private Character mOccupied; ///< Personagem que ocupa esta posição.
	
	/**
	 * Construtor de BoardPosition
	 * @param pCharacter Character que ocupara a posicao
	 * @param larguraBoard	Largura do tabuleiro (para calculo exato da posicao do personagem na matriz
	 * @param x linha do tabuleiro.
	 * @param y coluna do tabuleiro.
	 */
	public BoardPosition(Character pCharacter, int larguraBoard, int x, int y){	
		mXY = new Pair<Integer, Integer>(x, y);
		mPosition = x*larguraBoard + y;
		mMod = larguraBoard;
		//System.out.println("["+x+","+y+"]pos = "+mPosition);
		//Personagem que ocupa essa posicao
		mOccupied = pCharacter;
	}	
	
	/**
	 * É usado para encontrar o personagem no tabuleiro, dada sua posição.
	 * @return o inteiro corresponde a posição ordenada no tabuleiro.
	 */
	public int getPos(){
		return mPosition;
	}


	/**
	 * Seta uma nova posição.
	 * @param x linha do tabuleiro.
	 * @param y coluna do tabuleiro.
	 */
	public void setXY(int x, int y){
		mXY.setFirst(x);
		mXY.setSecond(y);
		mPosition = x*mMod + y;
		
	}
	
	/**
	 * 
	 * @return par de coordenadas X e Y que correspondem a posição no tabuleiro.
	 */
	public Pair<Integer, Integer> getXY(){
		return mXY;
	}

	/**
	 * 
	 * @return Caracter que ocupa a posicao desta BoardPosition
	 */
	public Character getOccup(){
		return mOccupied;
	}
	
	@Override
	/**
	 * Método de comparação de BoardPosition.
	 * É usado na Board para ordenar um conjunto de posições ocupadas.
	 * @see Board
	 */
	public int compareTo(BoardPosition o) {
		return mPosition - o.mPosition;
	}
	
}
