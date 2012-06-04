package Overview;
import java.util.*;

import Character.Character;
import Utilities.Pair;

/**
 *  Ã‰ um tabuleiro do jogo onde podem ocorrer batalhas entre times e seus personagens.
 */

public class Board {
	
	//Atributos
	private int mWidth;				///< Largura do tabuleiro	
	private int mHeight;				///< Altura do tabuleiro
	private Set<BoardPosition> mPositions;
	private Map<Color, Team> mTeams;
	
	/**
	 *Construtor padrao de tabuleiro, altura e largura recebem 5 
	 */
	public Board()
	{
		mWidth = 5;
		mHeight = 5;
		mTeams = new HashMap<Color, Team>();
		mPositions = new TreeSet<BoardPosition>();
	}
	
	/**
	 * Cosntrutor com parametros
	 * @param width	 Altura do tabuleiro
	 * @param height	Largura do tabuleiro
	 */
	public Board(int width, int height)
	{
		mWidth = width;
		mHeight = height;
		mTeams = new HashMap<Color, Team>();
		mPositions = new TreeSet<BoardPosition>();
	}
	
	/**
	 * Adiciona time ao jogo.
	 * @param team Time que sera adicionao ao jogo
	 */
	public void addTeam(Team team)
	{
		mTeams.put(team.getColor(), team);
	}
	
	/**
	 * Remove time do jogo
	 * @param color Cor do time que sera removido
	 * @return true se o time foi removido com sucesso. 
	 */
	public boolean removeTeam(Color color)
	{
		if(mTeams.remove(color) == null)
		{
			return false;
		}
		else return true;
	}
	
	/**
	 * 
	 * @param x Posicao x do personagem no tabuleiro
	 * @param y Posicao y do personagem do tabuleiro
	 * @param character Personagem que sera inserido
	 * @return false caso nao tenha sido inserido com sucesso (caso posicao especificada nao existir no tabuleiro)
	 */
	public boolean setCharacterPosition(int pos, Character character)
	{	
		int y = pos/mWidth;
		
		if(y < mWidth)
		{
			BoardPosition bp = new BoardPosition(character, mWidth, pos);
			mPositions.add(bp);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param pos Posicao do carater requerido
	 * @return Caso nao encontre o character buscado, entao retorna null
	 */
	public Character getCharacter(int pos)
	{	
		//character que sera retornado
		Character character = null;
		
		Iterator<BoardPosition> it = mPositions.iterator();
		//System.out.println("posAux: " + posAux);
		BoardPosition boardPosition = null;
		
		int posAux = 0;
		//enquato nao chegou ao fim e posAux é menor (pois set é ordenado)
		while(it.hasNext() && posAux <= pos)
		{	
			
			//Necessario criação de boardPosition, como com o iteradot nao é possivel
			//pegar o elemento atual, apenas o proximo da lista entao não é possivel usar o
			//iterador no "if", foi preciso criar um boardPosition para pegar a posicao do 
			//personagem e comparar com a posicao desejada
			boardPosition = it.next();
			posAux = boardPosition.getPos();
			
			//System.out.println("PosAux: " + posAux);
			if(pos == posAux)
			{		
				character = boardPosition.getOccup();
				return character;
			}	
		}
		
		return character;
	}
	
	/**
	 * 
	 * @param character Personagem que esta sendo buscado
	 * @return Pair de posicao X e Y do personagem no tabuleiro (para calculo da distancia de ataque)
	 */
	public Pair<Integer, Integer> getCharacterXY(Character character)
	{
		Pair<Integer, Integer> XY = null;
		BoardPosition boardPosition = null;
		
		Iterator<BoardPosition> it = mPositions.iterator();
		while(it.hasNext())
		{	
			boardPosition = it.next();
			if(boardPosition.getOccup() == character)
			{
				XY = boardPosition.getXY();
			}
		}
		return XY;
	}
	
}//fim da classe Board
