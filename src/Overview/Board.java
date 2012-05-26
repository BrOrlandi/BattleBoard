package Overview;
import java.util.*;

public class Board {
	
	//Atributos
	private int mWidth;				///< Altura do tabuleiro	
	private int mHeight;				///< Largura do tabuleiro
	
	private ArrayList<Team> mTeams; ///< Times no tabuleiro
	
	/**
	 *Construtor padrao de tabuleiro, altura e largura recebem 5 
	 */
	public Board()
	{
		mWidth = 5;
		mHeight = 5;
		mTeams = new ArrayList<Team>();
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
		mTeams = new ArrayList<Team>();
	}
	
	/**
	 * Adiciona time ao jogo.
	 * @param team Time que sera adicionao ao jogo
	 */
	public void addTeam(Team team)
	{
		mTeams.add(team);
	}
	
	/**
	 * Remove time do jogo
	 * @param team Time que sera removido
	 * @return true se o time foi removido com sucesso. 
	 */
	public boolean removeTeam(Team team)
	{
		return mTeams.remove(team);
	}
	
	/**
	 * Remove time do jogo
	 * @param color Cor do time que sera removido
	 * @return true se o time foi removido com sucesso. 
	 */
	public boolean removeTeam(Color color)
	{
		Iterator<Team> it = mTeams.iterator();
		while(it.hasNext())
		{
			Team t = it.next();
			if(t.getColor() == color)
			{
				return mTeams.remove(t);
			}
		}
		return false;
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
