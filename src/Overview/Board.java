package Overview;
import java.util.*;

import BattleBoardExceptions.CharacterNotFoundOnBoardException;
import BattleBoardExceptions.OccupiedBoardPositionException;
import Character.Character;
import Utilities.Pair;

/**
 * @package Overview
 * Incorporam os outros pacotes para o jogo funcionar em um tabuleiro onde os personagens estão divididos em times.
 */

/**
 *  É um tabuleiro do jogo onde podem ocorrer batalhas entre times e seus personagens.
 */

public class Board {
	
	//Atributos
	private int mWidth;				///< Largura do tabuleiro	
	private int mHeight;				///< Altura do tabuleiro
	private Set<BoardPosition> mPositions; ///< Conjunto ordenado de BoardPositions com os personagens.
	private Map<Color, Team> mTeams; ///< Mapa de times, a chave é a cor do time.
	
	/**
	 *Construtor padrao de tabuleiro, altura e largura recebem 5 
	 */
	public Board(){
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
	public Board(int width, int height){
		mWidth = width;
		mHeight = height;
		mTeams = new HashMap<Color, Team>();
		mPositions = new TreeSet<BoardPosition>();
	}
	
	/**
	 * Adiciona time ao jogo.
	 * @param team Time que sera adicionao ao jogo
	 */
	public void addTeam(Team team){
		mTeams.put(team.getColor(), team);
	}
	
	/**
	 * Permite obter o time corresponde a uma cor no tabuleiro
	 * @param c cor do time que está buscando
	 * @return o time se este existe e está no tabuleiro.
	 */
	public Team getTeam(Color c){
		return mTeams.get(c);
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
	 * @throws OccupiedBoardPositionException 
	 */
	public boolean setCharacterPosition(int x, int y, Character character) throws OccupiedBoardPositionException{
		Character chr = getCharacter(x, y);
		if(chr != null)
		{
			throw new OccupiedBoardPositionException(chr,"Board position occupied by "+ chr.getName()+"!");
		}
		if(x < mWidth && y < mHeight)
		{
			BoardPosition bp = new BoardPosition(character, mWidth, x,y);

			return mPositions.add(bp);
		}
		return false;
	}
	
	/**
	 * @param x linha do personagem requerido.
	 * @param y coluna do personagem requerido.
	 * @return Caso nao encontre o character buscado, entao retorna null.
	 */
	public Character getCharacter(int x, int y){	
		int pos = x*mWidth + y;
		//character que sera retornado
		Character character = null;
		
		Iterator<BoardPosition> it = mPositions.iterator();
		BoardPosition boardPosition = null;
		
		int posAux = 0;
		//enquato nao chegou ao fim e posAux é menor (pois set é ordenado)
		while(it.hasNext() && posAux < pos)
		{	
			boardPosition = it.next();
			posAux = boardPosition.getPos();
		}

		if(pos == posAux && boardPosition != null)
		{		
			character = boardPosition.getOccup();
		}	
		return character;
	}
	
	/**
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
				break;
			}
		}
		return XY;
	}
	
	/**
	 * Permite checar a distancia a distancia entre dois personagens no tabuleiro.
	 * @param c1 o primeiro personagem.
	 * @param c2 o segundo personagem.
	 * @return a distancia entre os personagens.
	 * @throws CharacterNotFoundOnBoardException 
	 */
	public int getDistance(Character c1, Character c2) throws CharacterNotFoundOnBoardException{
		Pair<Integer, Integer> c1Pos = getCharacterXY(c1);
		if(c1Pos == null)
		{
			throw new CharacterNotFoundOnBoardException(c1, c1.getName() + " was not found on Board.");
		}
		Pair<Integer, Integer> c2Pos = getCharacterXY(c2);
		if(c2Pos == null)
		{
			throw new CharacterNotFoundOnBoardException(c2, c2.getName() + " was not found on Board.");
		}
		
		//se algum dos personagens não está no tabuleiro.
		
		int distance = (int)Math.pow(c1Pos.getFirst() - c2Pos.getFirst(), 2) + 
				(int)Math.pow(c1Pos.getSecond() - c2Pos.getSecond(), 2);
		distance = (int)Math.sqrt(distance);
		
		//System.out.println("Distancia: " + distance);
		return distance;
	}
	
}//fim da classe Board
