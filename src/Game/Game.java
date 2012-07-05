package Game;

import java.awt.Color;
import java.io.IOException;

import Item.*;
import Overview.*;
import BattleBoardExceptions.CharacterCanNotConsumeItemException;
import BattleBoardExceptions.CharacterFromSameTeamException;
import BattleBoardExceptions.CharacterNotFoundOnBoardException;
import BattleBoardExceptions.DeadCharacterException;
import BattleBoardExceptions.ItemNotFoundException;
import BattleBoardExceptions.OpposingTeamCharacterException;
import BattleBoardExceptions.OutOfRangeCharacterException;
import Character.Character;
import Utilities.*;

/**
 * @package Game
 * Classes gerais que ir�o manusear o andamento do jogo. Equivalente ao Controle no paradigma MVC.
 */

/**
 * Esta classe ser� a principal da parte de Controle do paradigma MVC, gerencia todo o jogo e � a esta classe que a interface gr�fica do usu�rio ir� acessar os m�todos.
 *
 */
public class Game {

	public static final double INITIAL_MONEY = 10000.0; ///< Dinheiro inicial que os jogadores ir�o come�ar.
	public static final String ITEM_STORE_FILE = "ItemStore.xml"; ////< Nome do arquivo que cont�m a loja de itens padr�o do jogo.
	public static final int BOARD_HEIGHT = 10; ///< Altura padr�o do tabuleiro.
	public static final int BOARD_WIDTH = 10; ///< Largura padr�o do tabuleiro.
	public static final double CHARACTER_PRICE = 1000.0; ///< Pre�o padr�o de um personagem.	

	public Player mJ1; ///< Jogador 1.
	public Player mJ2; ///< Jogador 2.
	public Board mBoard; ///< Tabuleiro do jogo.
	public ItemStore mItemStore; ///< Loja de Items do jogo.

	/**
	 * Construtor da Classe game, dar� inicio ao jogo.
	 * Tenta carregar a loja de itens do jogo e cria o tabuleiro com as dimens�es padr�es.
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
	public void setPlayerOne(String name, String teamName, Color teamColor){
		mJ1 = new Player(name, INITIAL_MONEY, teamName, teamColor);
	}

	/**
	 * Cria o jogador 2.
	 * @param name nome do jogador.
	 * @param teamName nome do time.
	 * @param teamColor cor do time.
	 */
	public void setPlayerTwo(String name, String teamName, Color teamColor){
		mJ2 = new Player(name, INITIAL_MONEY, teamName, teamColor);
	}

	/**
	 * Usado para comprar um item da loja de itens.
	 * Lan�a uma exce��o de dinheiro insuficiente.
	 * @param p jogador que est� comprando.
	 * @param posItem posi��o do item na loja de itens.
	 * @param posCharacter posi��o do personagem no time do jogador.
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

	public void buyCharacter(Player p) throws NotEnoughMoneyException{
		p.subtractMoney(1000);
	}

	public void startGame() throws IOException{
		if(mItemStore == null)
		{
			throw new IOException("Item Store not found! Create a store on the main menu.");
		}

	}

	public static void main(String args[]){

	}

	/// Op��es de a��es dos personagens em cada turno:
	/**
	 * Realiza um ataque entre dois personagens.
	 * @param attacker
	 * @param victim
	 * @return Pair de valores, no qual o primeiro � o tipo de evento do ataque. E o segundo valor � o dano causado pelo ataque.
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
	 * Usar um item consum�vel entre os personagens.
	 * @param giver Personagem que possui o item a ser consumido.
	 * @param receiver Personagem que consumir� o item.
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
	 * Deve ser usado para adicionar itens � loja de itens.
	 * @param it item a ser adicionado.
	 * @throws Exception
	 * @warning N�o deve usar diretamente o addItem da Item Store, este verifica se a Store foi criada.
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