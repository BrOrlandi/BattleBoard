package Character;
import java.util.*;

import Item.*;
import Overview.Board;
import Overview.Color;
import Overview.Team;
import Utilities.Pair;

/**
 * @package Character
 * Representações de personagens do jogo.
 */

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
	private Color mColor;					///< Cor que o personagem está usando. Identifica o time que o personagem pertence.
	
	private Map<Integer, Item> mInventory;		///< Inventário de itens do personagem.
	private Consumable mConsumableItem;
	protected Weapon mWeapon;
	protected Armor mArmor;
	
	
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
		mColor = null;
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
		//return  (int) ((mConstitution*0.6+ mDexterity*0.1 + mSpeed*0.3 + getInventoryDefense())*(mXP)/6);
		if(mArmor == null)
		{
			return (int) ((mConstitution*0.6+ mDexterity*0.1 + mSpeed*0.3)*(mXP)/6);
		}
		return  (int) ((mConstitution*0.6+ mDexterity*0.1 + mSpeed*0.3 + mArmor.getDefensepts())*(mXP)/6);
	}
	
	/**
	 * 
	 * @return Retorna pontos de Ataque levando em conta todos os itens no inventario do personagem
	 */
	protected int getAttackPoints(){
		//return (int) ((mStrength*.06 + mDexterity*0.4 + getInventoryAttack())*(mXP)/2);
		if(mWeapon == null)
		{
			return (int) ((mStrength*.06 + mDexterity*0.4)*(mXP)/2);
		}
		return (int) ((mStrength*.06 + mDexterity*0.4 + mWeapon.getAttackpts())*(mXP)/2);
	}
	
	/**
	 * Método para atacar outro personagem.
	 * @param victim Recebe o personagem que sera atacado.
	 * @param board Recebe o tabuleiro onde ocorre a batalha.
	 * @return true se o ataque foi efetuado. False quando a vitima já esta morta ou não está no tabuleiro, ou a distancia não é suficiente para o ataque.
	 */
	public boolean attackCharacter(Character victim, Board board){
		
		if(isDead())
		{
			System.out.println(mAlias + " is dead and can't attack.");
			return false;
		}
		
		if(this.mColor == victim.mColor){
			System.out.println(mAlias + " and " + victim.mAlias + " are friends!");
			return false;
		}
		
		if(victim.mHP == 0){
			System.out.println(victim.mAlias + " is dead!");
			return false; // tentativa de atacar alguem q já está morto.
		}
		
		int distance = board.getDistance(this, victim);
		if(mWeapon != null && distance > mWeapon.getRange())
		{
			System.out.println(mAlias+" with "+mWeapon.getName()+" can't reach "+victim.mAlias+".\tDistance: "+distance+" WeaponRange: "+mWeapon.getRange());
			return false;
		}
		if(mWeapon == null && distance > 2)
		{
			System.out.println(mAlias+" you are too far from "+victim.mAlias+" to attack with your hands.");
			return false;
		}

		
		double chance = Math.random();
		//Calculo do dano que o personagem sofrera
		int dano =  ((getAttackPoints() - victim.getDefensePoints()) + (int)rnd(-5,5));
		
		//reduçao de dano se o personagem for Fighter
		if(this instanceof Fighter)
		{
			dano = dano - (int)distance*2; // reduz o ataque pelo dobro da distancia
		}
		
		if (dano <= 0)
		{
			dano = 1;
		}
		//Chance de 0.02*XP/2 do personagem dar um ataque critico
		if(chance < 0.02*(mXP)/2)
		{
			dano = dano*2;
			System.out.println(mAlias +" CRITICAL ATTACK " + victim.mAlias + "\t | Damage: " +dano);
		}
		else
		{
			System.out.println(mAlias +" Attacks "+ victim.mAlias + "\t | Damage: " +dano);
		}
		victim.mHP -= dano; // finalmente diminui o HP da vitima.
		if(victim.mHP <= 0){
			System.out.println(victim.mAlias + " was killed by "+ mAlias+"!");
			victim.mHP = 0;
			addXP(1);
			//verificar se todos do time foram mortos.
			Team t1 = board.getTeam(victim.mColor);
			if(t1.allDead())
			{
				t1.defeat();
				Team t2 = board.getTeam(mColor);
				t2.victory();
			}
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
	 * @deprecated Não é mais usado pois os pontos de Defesa não são mais calculados usando todos os itens do inventário.
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
	 * @deprecated Não é mais usado pois os pontos de Ataque não são mais calculados usando todos os itens do inventário.
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
	 * @return false se a posição do inventário já estiver ocupada ou true se foi adicionado com sucesso.
	 */
	public boolean addItem(int key, Item item){
		Item it = mInventory.get(key);
		if(it != null)
		{
			System.out.println(mAlias+" already has an item in this position.");
			return false;
		}
		mInventory.put(key, item);
		return true;
	}
	
	/**
	 * É o método chamado quando um personagem doa um item a outro através do método giveItem.
	 * A chave do item é calculada para o primeiro indíce vázio no inventário.
	 * @param item o item doado por outro personagem
	 * @return a chave para o item no inventário.
	 * @see giveItem
	 */
	public int addItem(Item item){
		int key = 0;
		Item a;
		do
		{
			key++;
			a = mInventory.get(key);
		}while(a != null);
		addItem(key,item);
		return key;
	}
	
	/**
	 * Dropa um item do inventário.
	 * @param key chave para o item no inventário.
	 * @return null se o item não está no inventário ou o item que foi removido.
	 */
	public Item dropItem(int key){
		if(isDead())
		{
			System.out.println(mAlias + " is dead and can't drop an item.");
			return null;
		}		
		return mInventory.remove(key);
	}
	
	/**
	 * Pega um item do inventário a partir de sua chave e doa para outro jogador.
	 * @param key chave do item no inventário
	 * @param chr jogador que receberá o item
	 * @return a chave para o item no inventário do outro personagem, ou um número negativo se falhou.
	 */
	public int giveItem(int key, Character chr){
		if(isDead())
		{
			System.out.println(mAlias + " is dead and can't give an item.");
			return -1;
		}
		if(mColor != chr.mColor)
		{
			System.out.println(mAlias + " and "+chr.mAlias + " aren't friends to give an item!");
			return -1;
		}
		Item it = mInventory.remove(key);
		if(it == null || chr == null)
		{
			return -1;
		}
		int k2 = chr.addItem(it);
		System.out.println(mAlias + " gives "+ it.getName()+ " to "+ chr.mAlias);
		return k2;
	}
	
	/**
	 * Seta o item consumível do personagem.
	 * Este deve vir do próprio inventário.
	 * @param key chave do item consumível no inventário.
	 * @return true se o item foi setado com sucesso. False se não encontrar o item no inventário ou ele não for consumível.
	 */
	public boolean setConsumable(int key){
		if(isDead())
		{
			System.out.println(mAlias + " is dead and can't set a consumable.");
			return false;
		}
		Item it = mInventory.get(key);
		if(it == null)
		{
			System.out.println("Item not found on Inventory!");
			return false;
		}
		if(it instanceof Consumable)
		{
			Item back = (Item) mConsumableItem;
			mConsumableItem = (Consumable) it;
			System.out.println("Now "+ it.getName() + " can be consumed by "+mAlias);
			mInventory.remove(key);
			
			//verificando se já tem consumable setado.
			if(back != null)
			{
				this.addItem(back);
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
	 * Seta a arma principal do personagem.
	 * Esta deve vir do próprio inventário.
	 * @param key chave do item arma no inventário.
	 * @return true se o item foi setado com sucesso. False se não encontrar o item no inventário ou ele não for uma arma.
	 */
	public boolean setWeapon(int key){
		if(isDead())
		{
			System.out.println(mAlias + " is dead and can't set a weapon.");
			return false;
		}
		Item it = mInventory.get(key);
		if(it == null)
		{
			System.out.println("Item not found on Inventory!");
			return false;
		}
		if(it instanceof Weapon)
		{
			Item back = (Item) mWeapon;
			mWeapon = (Weapon) it;
			System.out.println("Now "+ mAlias + " is carryng a "+it.getName());
			mInventory.remove(key);
			
			//verificando se já tem item setado.
			if(back != null)
			{
				this.addItem(back);
				//item anterior foi guardado no inventário ao trocar de item.
			}
			return true;
		}
		else
		{
			System.out.println(it.getName() + " is not a Weapon!");
			return false;
		}
	}

	/**
	 * Seta a armadura principal do personagem.
	 * Esta deve vir do próprio inventário.
	 * @param key chave do item armadura no inventário.
	 * @return true se o item foi setado com sucesso. False se não encontrar o item no inventário ou ele não for uma armadura.
	 */
	public boolean setArmor(int key){
		if(isDead())
		{
			System.out.println(mAlias + " is dead and can't set an armor.");
			return false;
		}
		Item it = mInventory.get(key);
		if(it == null)
		{
			System.out.println("Item not found on Inventory!");
			return false;
		}
		if(it instanceof Armor)
		{
			Item back = (Item) mArmor;
			mArmor = (Armor) it;
			System.out.println("Now "+ mAlias + " is wearing a "+it.getName());
			mInventory.remove(key);
			
			//verificando se já tem item setado.
			if(back != null)
			{
				this.addItem(back);
				//item anterior foi guardado no inventário ao trocar de item.
			}
			return true;
		}
		else
		{
			System.out.println(it.getName() + " is not an Armor!");
			return false;
		}
	}

	/**
	 * Usa o item consumível carregado pelo personagem.
	 * @return true se o item foi usado com sucesso. False caso o item não pode ser usado ou não possui um item consumível.
	 */
	public boolean useConsumable(){
		if(isDead())
		{
			System.out.println(mAlias + " is dead and can't use an consumable.");
			return false;
		}
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
			System.out.println(mAlias + " can't consume a "+ ((Item)mConsumableItem).getName()+".");
			return false;
		}
	}

	/**
	 * Usa o item consumível carregado pelo personagem em outro personagem desde que a distancia entre ele seja no máximo 2.
	 * @param chr personagem no qual o consumível será aplicado.
	 * @param board tabuleiro onde os personagens estão.
	 * @return true se o item foi usado com sucesso. False caso o item não pode ser usado, não possui um item consumível, ou ainda o personagem escolhido não pode usar o item.
	 */
	public boolean useConsumable(Character chr, Board board){
		if(this == chr)
		{
			return useConsumable();
		}
		
		if(isDead())
		{
			System.out.println(mAlias + " is dead and can't use an consumable.");
			return false;
		}
		
		if(mConsumableItem == null)
		{
			System.out.println(mAlias + " don't have a consumable item set.");
			return false;
		}
		
		if(this.mColor != chr.mColor){
			System.out.println(mAlias + " and " + chr.mAlias + " aren't friends to use a consumable!");
			return false;
		}
		
		int distance = board.getDistance(this, chr);
		
		// caso a distancia entre os personagens seja maior que 2, então não pode usar item
		if(distance > 2)
		{
			System.out.println(mAlias + " are too far from "+chr.mAlias+", min distance to use this is 2.");;
			return false;
		}

		if(mConsumableItem.consumableBy(chr))
		{
			mConsumableItem.consume(chr);
			System.out.println(mAlias + " did "+ chr.mAlias+ " consume a "+ ((Item)mConsumableItem).getName()+".");
			if(mConsumableItem instanceof RevivePotion){
				System.out.println(chr.mAlias+" is alive again!");
			}
			mConsumableItem = null;
			return true;
		}
		else
		{
			System.out.println(chr.mAlias + " can't consume a "+ ((Item)mConsumableItem).getName()+".");
			return false;
		}
	}
	
	/**
	 * Imprime as características do personagem tais como: 
	 * Nome, HP, Velocidade, Força, Destreza, Constituição e Força.
	 * E imprime todos os itens que o personagem possui no seu inventário.
	 */
	public void print(){
		System.out.println("------------------------------------------------------");
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
		System.out.println("------------------------------------------------------");
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(isDead())
		{
			sb.append("DEAD!\n");
		}
		sb.append("~={ "+ mAlias + " }=~\n HP: "+mHP + " XP: "+mXP+"\n"+
				"STR: " + mStrength + " SPD: " + mSpeed + " DEX: " + mDexterity + " CST: "+mConstitution+"\n");
		if(mWeapon != null)
		{
			sb.append("{ "+mWeapon+" }\n");
		}
		if(mArmor != null)
		{
			sb.append("{ "+mArmor+" }\n");
		}
		if(mConsumableItem != null)
		{
			sb.append("{ "+mConsumableItem+" }\n");
		}
		sb.append(mInventory.size()+" items");
		return sb.toString();
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
	
	public void setColor(Color cor){
		mColor = cor;
	}
	
	public Color getColor(){
		return mColor;
	}
	
}//fim da classe Character
