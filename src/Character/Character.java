package Character;
import java.util.*;

import Item.*;
import Overview.Board;
import Utilities.Pair;

/**
 * Esta classe representa um personagem do jogo.
 *
 */
public class Character {
	
	//Atributos
	private String mAlias;			///< Nome do personagem
	private int mHP; 				///< Pontos de Vida do personagem.
	private int mMaxHP;			///< Número máximo de pontos de vida que o personagem terá.
	protected int mXP;				///< Pontos de Experiência do personagem						
	protected int mStrength;		///< Força do personagem
	protected int mDexterity;		///< Destreza do personagem
	protected int mSpeed;			///< Velocidade do personagem
	protected int mConstitution;	///< Constituição do personagem
	
	private Map<Integer, Item> mInventory;		///< Inventário de itens do personagem.
	private Consumable mConsumableItem;
	private Weapon mWeapon;
	private Armor mArmor;
	
	
	/**
	 * Construtor que recebe o nome do personagem
	 * @param alias nome do personagem
	 */
	public Character(String alias){
		mAlias = alias;
		mInventory = new HashMap<Integer, Item>();
		mStrength = 0;
		mSpeed = 0;
		mDexterity = 0;
		mConstitution = 0;
		mHP = 100;
		mMaxHP = 100;
		mXP = 1;
		mConsumableItem = null;
		mWeapon = null;
		mArmor = null;
	}//fim do construtor
	
	/**
	 * 
	 * @return Retorna nome do personagem
	 */
	public String getName(){
		return mAlias;
	}
	
	/**
	 * 
	 * @return Retorna pontos de Defesa levando em conta todos os itens no inventario do personagem
	 */
	protected int getDefensePoints(){
		return  (int) ((mConstitution*0.6+ mDexterity*0.1 + mSpeed*0.3 + getInventoryDefense())*(mXP)/6);
	}
	
	/**
	 * 
	 * @return Retorna pontos de Ataque levando em conta todos os itens no inventario do personagem
	 */
	protected int getAttackPoints(){
		return (int) ((mStrength*.06 + mDexterity*0.4 + getInventoryAttack())*(mXP)/2);
	}
	
	/**
	 * Método para atacar outro personagem.
	 * @param victim Recebe o personagem que sera atacado
	 * @return true se o ataque foi efetuado. False quando a vitima já esta morta.
	 */
	public boolean attackCharacter(Character victim, Board board){
		
		//tentativa de ataque feita por alguem ou para alguem que esta fora do tabuleiro
		if(board.getCharacterXY(victim) == null || board.getCharacterXY(this) == null)
		{
			return false;
		}
		
		//TODO verificar distancia de ataque
		Pair<Integer, Integer> attackerPosition = board.getCharacterXY(this);
		Pair<Integer, Integer> victimPosition = board.getCharacterXY(victim);
		
		int distance = (int) Math.pow(attackerPosition.getFirst() - victimPosition.getFirst(), 2) + 
				(int) Math.pow(attackerPosition.getSecond() - victimPosition.getSecond(), 2);
		distance = (int) Math.sqrt(distance);
		
		System.out.println("Distancia: " + distance);
				
		if(victim.mHP == 0){
			System.out.println(victim.mAlias + " is dead!");
			return false; // tentativa de atacar alguem q já está morto.
		}
		
		
		double chance = Math.random();
		//Calculo do dano que o personagem sofrera
		int dano =  ((getAttackPoints() - victim.getDefensePoints()) + (int)rnd(-5,5));
		if (dano < 1)
		{
			dano = 1;
		}
		//Chance de 0.02*XP/2 do personagem dar um ataque critico
		if(chance < 0.02*(mXP)/2)
		{
			dano = dano*2;
			//System.out.println("Critical Hit!: "+dano);
			System.out.println(mAlias +" Critical Hit " + victim.mAlias + " | Damage: " +dano);
		}
		else
		{
			System.out.println(mAlias +" Attacks "+ victim.mAlias + " | Damage: " +dano);
		}
		victim.mHP -= dano; // finalmente diminui o HP da vitima.
		if(victim.mHP <= 0){
			System.out.println(victim.mAlias + " was killed by "+ mAlias+"!");
			victim.mHP = 0;
			addXP(1);
		}
		return true;
	}
	
	/**
	 * Adiciona pontos de experiencia ao personagem.
	 * @param XP XP que sera adicionado
	 */
	public void addXP(int xp){

		mXP += xp;
		mMaxHP = (int) (mMaxHP*1.3);
		
		if (mXP > 100)
		{
			mXP = 100;
		}
		
	}
	
	/**
	 * 
	 * @return o máximo de pontos de vida do personagem
	 */
	public int getMaxHP(){
		return mMaxHP;
	}

	
	/**
	 * 
	 * @return os pontos de vida do personagem.
	 */
	public int getHP(){
		return mHP;
	}
	
	/**
	 * Aumenta o HP do personagem não deixando o com mais que sua vida máxima.
	 * @param hp pontos de vida que serão adicionados
	 * @return true se foi possivel aumentar os pontos de vida.
	 */
	public boolean addHP(int hp){
		if(mHP == mMaxHP)
		{
			return false;
		}
		if(mHP+hp > mMaxHP)
		{
			mHP = mMaxHP;
		}
		else
		{
			mHP += hp;
		}
		return true;
	}
	
	/**
	 * Atualiza a força do personagem
	 * @param str Novo valor de força
	 */
	public void setStrength(int str){
		//soma dos atributos nao pode passar de cem
		if(mSpeed + str + mDexterity + mConstitution <= 100)
		{
			mStrength = str;
		}
	}
	
	/**
	 * 
	 * @param spd Novo valor de velocidade
	 */
	public void setSpeed(int spd){
		//soma dos atributos nao pode passar de cem
		if(spd + mStrength + mDexterity + mConstitution <= 100)
		{
			mSpeed = spd;
		}
	}
	
	/**
	 * 
	 * @param dex Novo valor para destreza
	 */
	public void setDexterity(int dex){
		//soma dos atributos nao pode passar de cem
		if(mSpeed + mStrength + dex + mConstitution <= 100)
		{
			mDexterity = dex;
		}
	}
	
	/**
	 * 
	 * @param cst Novo valor de constituicao
	 */
	public void setConstitution(int cst){	
		//soma dos atributos nao pode passar de cem
		if(mSpeed + mStrength + mDexterity + cst <= 100)
		{
			mConstitution = cst;
		}
	}
	
	/**
	 * Calcula total de pontos de defesa dos itens do inventário.
	 * @return Pontos de Defesa total pelos itens
	 */
	public int getInventoryDefense(){
		int item_def_pts = 0;

		Iterator<Item> it = mInventory.values().iterator();
		while(it.hasNext())
		{
			Item itemTemp = it.next();
			item_def_pts += itemTemp.getDefensepts();
		}
		return item_def_pts;
	}
	
	/**
	 * Calcula total de pontos de ataque dos itens do inventário.
	 * @return Pontos de Ataque total pelos itens
	 */
	public int getInventoryAttack(){
		int item_att_pts = 0;
		
		Iterator<Item> it = mInventory.values().iterator();
		while(it.hasNext())
		{
			Item itemTemp = it.next();
			item_att_pts += itemTemp.getAttackpts();
		}
		return item_att_pts;
	}
	
	/**
	 * Adiciona um item ao inventário do personagem.
	 * @param key numero inteiro que é a chave do item no inventário.
	 * @param item item a ser adicionado ao inventário.
	 */
	public void addItem(int key, Item item){
		mInventory.put(key, item);
	}
	
	/**
	 * É o método chamado quando um personagem doa um item a outro através do método giveItem.
	 * @param item o item doado por outro personagem
	 * @see giveItem
	 */
	public void addItem(Item item){
		int key = mInventory.size()+1;
		addItem(key, item);
	}
	
	/**
	 * Dropa um item do inventário.
	 * @param key chave para o item no inventário.
	 * @return null se o item não está no inventário ou o item que foi removido.
	 */
	public Item dropItem(int key){
		return mInventory.remove(key);
	}
	
	/**
	 * Pega um item do inventário a partir de sua chave e doa para outro jogador.
	 * @param key chave do item no inventário
	 * @param chr jogador que receberá o item
	 * @return true se o item foi cedido com sucesso.
	 */
	public boolean giveItem(int key, Character chr){
		Item it = mInventory.remove(key);
		if(it == null || chr == null)
		{
			return false;
		}
		chr.addItem(it);
		System.out.println(mAlias + " gives "+ it.getName()+ " to "+ chr.mAlias);
		return true;
	}
	
	/**
	 * Seta o item consumível do personagem.
	 * Este deve vir do próprio inventário.
	 * @param key chave do item consumível no inventário.
	 * @return true se o item foi setado com sucesso. False se não encontrar o item no inventário ou ele não for consumível.
	 */
	public boolean setConsumable(int key){
		Item it = mInventory.get(key);
		if(it == null)
		{
			System.out.println("Item not found on Inventory!");
			return false;
		}
		if(it instanceof Consumable)
		{
			Item i = (Item) mConsumableItem;
			mConsumableItem = (Consumable) it;
			System.out.println("Now "+ it.getName() + " can be consumed by "+mAlias);
			mInventory.remove(key);
			
			//verificando se já tem consumable setado.
			if(i != null)
			{
				this.addItem(i);
				//item consumable anterior foi guardado no inventário ao trocar de item.
			}
			return true;
		}
		else
		{
			System.out.println(it.getName() + " is not consumable!");
			return false;
		}
	}
	
	/**
	 * Usa o item consumível carregado pelo personagem.
	 * @return true se o item foi usado com sucesso. False caso o item não pode ser usado ou não possui um item consumível.
	 */
	public boolean useConsumable(){
		if(mConsumableItem == null)
		{
			System.out.println(mAlias + " does not have an consumable item selected.");
			return false;
		}
		if(mConsumableItem.consumableBy(this))
		{
			mConsumableItem.consume(this);
			System.out.println(mAlias + " consumed a "+ ((Item)mConsumableItem).getName()+".");
			mConsumableItem = null;
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Usa o item consumível carregado pelo personagem em outro personagem desde que a distancia entre ele seja no máximo 2.
	 * @param chr personagem no qual o consumível será aplicado.
	 * @return true se o item foi usado com sucesso. False caso o item não pode ser usado, não possui um item consumível, ou ainda o personagem escolhido não pode usar o item.
	 */
	public boolean useConsumable(Character chr, Board board){
		
		//tentativa de usar item feita por alguem ou para alguem que esta fora do tabuleiro
		if(board.getCharacterXY(chr) == null || board.getCharacterXY(this) == null)
		{
			return false;
		}
		// verificar a distancia
		Pair<Integer, Integer> attackerPosition = board.getCharacterXY(this);
		Pair<Integer, Integer> victimPosition = board.getCharacterXY(chr);
		
		int distance = (int) Math.pow(attackerPosition.getFirst() - victimPosition.getFirst(), 2) + 
				(int) Math.pow(attackerPosition.getSecond() - victimPosition.getSecond(), 2);
		distance = (int) Math.sqrt(distance);
		
		// caso a distancia entre os personagens seja mario que 2, então nao pode usar item
		if(distance > 2)
		{
			return false;
		}
		
		if(mConsumableItem == null)
		{
			return false;
		}

		if(mConsumableItem.consumableBy(chr))
		{
			mConsumableItem.consume(chr);
			System.out.println(mAlias + " did "+ chr.mAlias+ " consume a "+ ((Item)mConsumableItem).getName()+".");
			mConsumableItem = null;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Imprime as características do personagem tais como: 
	 * Nome, HP, Velocidade, Força, Destreza, Constituição e Força.
	 * E imprime todos os itens que o personagem possui no seu inventário.
	 */
	public void print(){
		System.out.print(toString());
		System.out.println(":");
		Iterator<Item> it = mInventory.values().iterator();
		Iterator<Integer> it2 = mInventory.keySet().iterator();
		while(it.hasNext())
		{
			Item itemTemp = it.next();
			int x = it2.next();
			System.out.print(x + ": ");
			itemTemp.print();
		}
	}
	
	public String toString(){
		return "Name: " + mAlias + " HP: "+mHP + " XP: "+mXP+"\n"+
	"STR: " + mStrength + " SPD: " + mSpeed + " DEX: " + mDexterity + " CST: "+mConstitution+"\n" +
		mInventory.size()+" items";
	}
	
	/**
	 * Função para gerar um número aleatório dentro de um intervalo
	 * @param minimo menor valor aleatório possível
	 * @param maximo maior valor aleatório possivel
	 * @return o valor aleatório gerado dentro do intervalo em Double.
	 */
	public static double rnd(int minimo, int maximo) {  
		  return Math.random() * (maximo - minimo) + minimo;  
	}
	
	/**
	 * Verifica se o personagem está morto.
	 * @return true se estiver morto.
	 */
	public boolean isDead(){
		if(mHP == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}//fim da classe Character
