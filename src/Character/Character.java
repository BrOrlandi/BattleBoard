package Character;
import java.util.*;

import javax.xml.bind.ValidationEvent;

import Item.*;

//JA EXISTE UMA CLASSE EM JAVA COM O NOME CHARACTER
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
	 */
	public void attackCharacter(Character victim){
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
	}
	
	/**
	 * Adiciona pontos de experiencia ao personagem.
	 * @param XP XP que sera adicionado
	 */
	public void addXP(int xp){
		mXP += xp;
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
	 * Imprime as características do personagem tais como: 
	 * Nome, HP, Velocidade, Força, Destreza, Constituição e Força.
	 * E imprime todos os itens que o personagem possui no seu inventário.
	 */
	public void print(){
		System.out.println("Name: " + mAlias + " HP: "+mHP + " XP: "+mXP);
		System.out.println("STR: " + mStrength + " SPD: " + mSpeed + " DEX: " + mDexterity + " CST: "+mConstitution);
		System.out.println("Items:");
		Iterator<Item> it = mInventory.values().iterator();
		while(it.hasNext())
		{
			Item itemTemp = it.next();
			itemTemp.print();
		}
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
	
}//fim da classe Character
