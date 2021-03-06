package Game;

import Overview.Color;
import Overview.Team;

/**
 * @package Game
 * Classes gerais que irão manusear o andamento do jogo. Equivalente ao Controle no paradigma MVC.
 */

/**
 * 
 * Representa um jogador, que possui o seu time com seus personagens, dinheiro e nome.
 *
 */
public class Player {
	private String mName; ///< Nome do Jogador.
	private Team mTeam; ///< Time do jogador com seus personagens.
	private double mMoney; ///< Quantia de dinheiro que o jogador dispõe.
	
	/**
	 * Construtor de jogador.
	 * @param name Nome do Jogador
	 * @param initialMoney Dinheiro inicial do jogador
	 * @param teamName Nome do Time do jogador.
	 * @param teamColor Cor do time do jogador.
	 */
	public Player(String name, double initialMoney, String teamName, Color teamColor){
		mName = name;
		mMoney = initialMoney;
		mTeam = new Team(teamName, teamColor);
	}
	
	/**
	 * 
	 * @return o dinheiro atual do jogador.
	 */
	public double getMoney(){
		return mMoney;
	}
	
	/**
	 * Adiciona dinheiro ao jogador.
	 * @param money quantia de dinheiro a ser adicionado.
	 */
	public void addMoney(double money){
		mMoney += money;
	}

	/**
	 * Subtrai dinheiro do jogador.
	 * @param money quantia de dinheiro a ser subtraida.
	 */
	public void subtractMoney(double money){
		mMoney -= money;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}
	
	public Team getTeam(){
		return mTeam;
	}
}
