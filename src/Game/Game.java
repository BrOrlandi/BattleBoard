package Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import Item.*;
import Overview.*;
import BattleBoardExceptions.CharacterCanNotConsumeItemException;
import BattleBoardExceptions.CharacterFromSameTeamException;
import BattleBoardExceptions.CharacterNotFoundOnBoardException;
import BattleBoardExceptions.DeadCharacterException;
import BattleBoardExceptions.ItemNotFoundException;
import BattleBoardExceptions.OpposingTeamCharacterException;
import BattleBoardExceptions.OutOfRangeCharacterException;
import Character.*;
import Character.Character;
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
	public static final int BOARD_HEIGHT = 10; ///< Altura padrão do tabuleiro.
	public static final int BOARD_WIDTH = 10; ///< Largura padrão do tabuleiro.
	public static final double CHARACTER_PRICE = 1000.0; ///< Preço padrão de um personagem.	

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
			//System.out.println(e.getMessage());
			mItemStore = null;
		}
		
		//inicializa board
		mBoard = new Board(BOARD_WIDTH, BOARD_HEIGHT);
		
		//jogadores como null
		mJ1 = null;
		mJ2 = null;
	}
	
	/**
	 * Cria o jogador 1.
	 * @param name nome do jogador.
	 * @param teamName nome do time.
	 * @param teamColor cor do time.
	 */
	public void setPlayerOne(String name, String teamName, GameColor teamColor){
		mJ1 = new Player(name, INITIAL_MONEY, teamName, teamColor);
	}

	/**
	 * Cria o jogador 2.
	 * @param name nome do jogador.
	 * @param teamName nome do time.
	 * @param teamColor cor do time.
	 */
	public void setPlayerTwo(String name, String teamName, GameColor teamColor){
		mJ2 = new Player(name, INITIAL_MONEY, teamName, teamColor);
	}

	/**
	 * Usado para comprar um item da loja de itens.
	 * Lança uma exceção de dinheiro insuficiente.
	 * @param p jogador que está comprando.
	 * @param posItem posição do item na loja de itens.
	 * @param posCharacter posição do personagem no time do jogador.
	 * @throws NotEnoughMoneyException
	 * @throws ItemNotFoundException
	 */
	public void buyItem(Player p, int posItem, int posCharacter) throws NotEnoughMoneyException, ArrayIndexOutOfBoundsException{
		Item[] its = mItemStore.getItemArray();
		Character[] chars = p.getTeam().getCharactersArray();
		if(posItem < 0 || posItem >= its.length || posCharacter < 0 || posCharacter >= chars.length)
		{
			throw new ArrayIndexOutOfBoundsException("Item not found on Item Store.");
		}
		Item it = its[posItem];
		Character chr = chars[posCharacter];
		p.subtractMoney(it.getPrice());
		chr.addItem(it);
	}
	
	public void startGame() throws IOException{
		if(mItemStore == null)
		{
			throw new IOException("Item Store not found! Create a store on the main menu.");
		}
		
	}
	
	public static void main(String args[]){

	}
	
	/// Opções de ações dos personagens em cada turno:
	/**
	 * Realiza um ataque entre dois personagens.
	 * @param attacker
	 * @param victim
	 * @return Pair de valores, no qual o primeiro é o tipo de evento do ataque. E o segundo valor é o dano causado pelo ataque.
	 * @throws OutOfRangeCharacterException 
	 * @throws CharacterFromSameTeamException 
	 * @throws DeadCharacterException 
	 * @throws CharacterNotFoundOnBoardException 
	 * @see NORMAL_ATTACK
	 */
	public Pair<Integer, Integer> attackCharacter(Character attacker, Character victim) throws DeadCharacterException, CharacterFromSameTeamException, OutOfRangeCharacterException, CharacterNotFoundOnBoardException{
		int distance = mBoard.getDistance(attacker, victim);
		Pair<Integer, Integer> ret = attacker.attackCharacter(victim, distance);
		if(ret.getFirst() == Character.KILL_ATTACK){
			//System.out.println(victim.getName() + " was killed by "+ attacker.getName()+"!");
			//verificar se todos do time foram mortos.
			Team t1 = mBoard.getTeam(victim.getColor());
			if(t1.allDead())
			{
				t1.defeat();
				Team t2 = mBoard.getTeam(attacker.getColor());
				t2.victory();
				ret.setFirst(Character.WIN_ATTACK);
			}
		}
		return ret;
	}
	
	/**
	 * Usar um item consumível entre os personagens.
	 * @param giver Personagem que possui o item a ser consumido.
	 * @param receiver Personagem que consumirá o item.
	 * @throws CharacterNotFoundOnBoardException
	 * @throws CharacterCanNotConsumeItemException
	 * @throws DeadCharacterException
	 * @throws OpposingTeamCharacterException
	 * @throws OutOfRangeCharacterException
	 * @throws ItemNotFoundException
	 */
	public void useConsumable(Character giver, Character receiver) throws CharacterNotFoundOnBoardException, CharacterCanNotConsumeItemException, DeadCharacterException, OpposingTeamCharacterException, OutOfRangeCharacterException, ItemNotFoundException{
		int distance = mBoard.getDistance(giver, receiver);
		giver.useConsumable(receiver, distance);
	}
	
	/**
	 * Deve ser usado para adicionar itens à loja de itens.
	 * @param it item a ser adicionado.
	 * @throws Exception
	 * @warning Não deve usar diretamente o addItem da Item Store, este verifica se a Store foi criada.
	 */
	public void addItemToStore(Item it) throws Exception{
		if(mItemStore == null){
			throw new Exception("Item Store not created yet!");
		}
		mItemStore.addItem(it);
	}
	
	/**
	 * Cria a loja de itens do jogo a partir do nome.
	 * @param storeName nome da loja de itens.
	 */
	public void createItemStore(String storeName){
		mItemStore = new ItemStore(storeName);
	}
	
	
}
