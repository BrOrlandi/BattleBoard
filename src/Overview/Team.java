package Overview;
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
	
	private ArrayList<Character> mCharacters; //Lista de personagens do time
	
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
		String result = "Wins: " + mWin + ", Draws: " + mDraw + ",d Defeats: " +  mLose;
		return result;
	}
	
	/**
	 * Imprime o time e suas caracteristicas
	 */
	public void print(){
		System.out.println(this.toString());
	}
	
	@Override
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
	}

	/**
	 * Dá mais uma Derrota ao time.
	 */
	public void defeat(){
		mLose++;
	}

	/**
	 * Dá mais um Empate ao time.
	 */
	public void draw(){
		mDraw++;
	}
	//
	
	/**
	 * Adiciona um personagem ao time.
	 * @param chr Personagem que sera adicionado ao time
	 */
	public void addCharacter(Character chr){
		mCharacters.add(chr);
	}
	
	/**
	 * Remove personagem do time.
	 * @param chr Personagem que sera removido do time
	 * @return true se o personagem foi removido.
	 */
	public boolean removeCharacter(Character chr)
	{
		return mCharacters.remove(chr);
	}
	
	/**
	 * Remove personagem do time.
	 * @param index indice do personagem que sera removido do time
	 * @return true se o personagem foi removido.
	 */
	public void removeCharacter(int index)
	{
		mCharacters.remove(index);
	}

}

