package Overview;
import java.util.*;

import javax.swing.text.Position;

/**
 *  Ã‰ um tabuleiro do jogo onde podem ocorrer batalhas entre times e seus personagens.
 */

//TODO Editar classe board, é um set
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
	 */
	public void setCharacterPosition(int x, int y, Character character)
	{	
		BoardPosition bp = new BoardPosition(character, mWidth, x, y);
		mPositions.add(bp);
	}
	
	public Character getCharacter(int x, int y)
	{
	
		Iterator it = mPositions.iterator();
		while(it.hasNext())
		{	
			it.
		}
	}
	
}//fim da classe Board
