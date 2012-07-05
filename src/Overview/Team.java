package Overview;
import java.awt.Color;
import java.util.*;
import Character.Character; //JA EXISTE UMA CLASSE EM JAVA COM O NOME DE CHARACTER


/**
 * 
 * Representa um time do jogo, possui seus personagens e sua cor.
 * Possui informações de quantas vezes o time ganhou, perdeu ou empatou uma batalha.
 *
 */
public class Team {
	
	//Atributos
	private String mName;	///< Nome do time
	private Color mColor;	///< Cor do time
	private int mWin;		///< Numero de vitorias do time		
	private int mLose;		///< Numero de derrotas do time
	private int mDraw;		///< Numero de empates do time
	
	private ArrayList<Character> mCharacters; ///< Lista de personagens do time
	
	/**
	 * Construtor do time
	 * @param name Nome do time
	 * @param color Cor do time
	 */
	public Team(String name, Color color){
		mName = name;
		mColor = color;
		mWin = 0;
		mLose = 0;
		mDraw = 0;
		mCharacters = new ArrayList<Character>();
	}//fim do construtor
	
	//GETTERS
	/**
	 * 
	 * @return Nome do time
	 */
	public String getName(){
		return mName;
	}
	
	/**
	 * 
	 * @return Cor do time
	 */
	public Color getColor(){
		return mColor;
	}
	/**
	 * 
	 * @return String com os resultados do time no formato (Wins: 0, Draws: 0, Defeats: 0)
	 */
	public String getResults(){
		String result = "Wins: " + mWin + ", Draws: " + mDraw + ", Defeats: " +  mLose;
		return result;
	}
	
	@Override
	/**
	 * @return String com as caracteristica do time(nome e cor e resultados) e cada personagem.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Team: " + mName + ", Color: "+ mColor+ "\n");
		sb.append(getResults() + ", Characters:\n");
		Iterator<Character> it = mCharacters.iterator();
		while(it.hasNext())
		{
			sb.append(it.next().getName() + "\n");
		}
		return sb.toString();
	}

	/**
	 * Dá mais uma Vitória ao time.
	 */
	public void victory(){
		mWin++;
		//System.out.println(mName + " Team won the battle!");
	}

	/**
	 * Dá mais uma Derrota ao time.
	 */
	public void defeat(){
		mLose++;
		//System.out.println(mName + " Team was defeated!");
	}

	/**
	 * Dá mais um Empate ao time.
	 */
	public void draw(){
		mDraw++;
		//System.out.println(mName + " Team drew!");
	}
	//
	
	/**
	 * Adiciona um personagem ao time.
	 * @param chr Personagem que sera adicionado ao time
	 */
	public void addCharacter(Character chr){
		if(chr == null)
			throw new NullPointerException();
		chr.setColor(mColor);
		mCharacters.add(chr);
	}
	
	/**
	 * Remove personagem do time.
	 * @param chr Personagem que sera removido do time
	 * @return true se o personagem foi removido.
	 */
	public boolean removeCharacter(Character chr)
	{
		chr.setColor(null);
		return mCharacters.remove(chr);
	}
	
	/**
	 * Remove personagem do time.
	 * @param index indice do personagem que sera removido do time
	 * @return true se o personagem foi removido.
	 */
	public void removeCharacter(int index)
	{
		Character chr = mCharacters.remove(index);
		chr.setColor(null);
	}
	
	/**
	 * Método para verificar se todos os personagens de um time foram mortos. Podendo assim dar a vitória para o outro time.
	 * @return true se todos estão mortos ou false se ainda há personagens vivos.
	 * @see attackCharacter
	 */
	public boolean allDead(){
		Iterator<Character> it = mCharacters.iterator();
		while(it.hasNext()){
			Character c = it.next();
			if(!c.isDead())
			{
				return false;
			}
		}
		return true;
	}
	
	public int numCharacter(){
		return mCharacters.size();
	}
	
	public Character getCharacter(int pos)
	{
		return mCharacters.get(pos);
	}
	/**
	 * 
	 * @return Array de Characters do Time.
	 */
	public Character[] getCharactersArray(){
		
		Character chrs[] = new Character[1];
		chrs = mCharacters.toArray(chrs);
		return chrs;

	}
}

