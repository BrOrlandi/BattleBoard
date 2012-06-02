package Overview;
import java.util.*;

/**
 *  Ã‰ um tabuleiro do jogo onde podem ocorrer batalhas entre times e seus personagens.
 */

//TODO Editar classe board, é um set
public class Board {
	
	//Atributos
	private int mWidth;				///< Altura do tabuleiro	
	private int mHeight;				///< Largura do tabuleiro
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
	 * @param color	 Cor do time
	 * @param index Personagem
	 */
	public void setCharacterPosition(int x, int y, Color color, int index)
	{	
		//TODO
	}
	
}//fim da classe Board
