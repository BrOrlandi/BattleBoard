package Overview;
import java.util.*;

import javax.swing.text.Position;

import Character.Character;
import Utilities.Pair;

/**
 *  É um tabuleiro do jogo onde podem ocorrer batalhas entre times e seus personagens.
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
	
	/**
	 * 
	 * @param pos Posicao do carater requerido
	 * @return Caso nao encontre o character buscado, entao retorna null
	 */
	public Character getCharacter(int pos)
	{
		Character character = null;
		
		Iterator<BoardPosition> it = mPositions.iterator();
		while(it.hasNext() && pos < it.next().getPos())
		{	
			if(pos == it.next().getPos())
			{
				character = it.next().getOccup();
				return character;
			}
		}
		return character;
	}
	
}//fim da classe Board
