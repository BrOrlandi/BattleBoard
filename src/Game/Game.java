package Game;

import java.io.IOException;

import Item.*;
import Overview.*;
import Character.*;
import Utilities.*;

/**
 * @package Game
 * Classes gerais que irão manusear o andamento do jogo. Equivalente ao Controle no paradigma MVC.
 */

/**
 * Esta classe será a principal da parte de Controle do paradigma MVC, gerencia todo o jogo e é a esta classe que a interface gráfica do usuário irá acessar os métodos.
 *
 */
public class Game {
	
	public static final double INITIAL_MONEY = 5000.0; ///< Dinheiro inicial que os jogadores irão começar.
	public static final String ITEM_STORE_FILE = "ItemStore.xml"; ////< Nome do arquivo que contém a loja de itens padrão do jogo.
	public static final int BOARD_HEIGHT = 5; ///< Altura padrão do tabuleiro.
	public static final int BOARD_WIDTH = 5; ///< Largura padrão do tabuleiro.
	

	public Player mJ1; ///< Jogador 1.
	public Player mJ2; ///< Jogador 2.
	public Board mBoard; ///< Tabuleiro do jogo.
	public ItemStore mItemStore; ///< Loja de Items do jogo.
	
	/**
	 * Construtor da Classe game, dará inicio ao jogo.
	 * Tenta carregar a loja de itens do jogo e cria o tabuleiro com as dimensões padrões.
	 */
	public Game(){
		//carrega a loja de itens.
		try{
			mItemStore = (ItemStore) XML.fromXML(ITEM_STORE_FILE);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			mItemStore = null;
		}
		
		//inicializa board
		mBoard = new Board(BOARD_WIDTH, BOARD_HEIGHT);
		
		//jogadores como null
		mJ1 = null;
		mJ2 = null;
	}
	
	/**
	 * Define o jogador 1.
	 * @param p jogador que será o 1.
	 */
	public void setPlayerOne(Player p){
		mJ1 = p;
	}

	/**
	 * Define o jogador 2.
	 * @param p jogador que será o 2.
	 */
	public void setPlayerTwo(Player p){
		mJ2 = p;
	}

	public void startGame() throws IOException{
		if(mItemStore == null)
		{
			throw new IOException("Item Store not found! Create a store on the main menu.");
		}
	}
	
}
