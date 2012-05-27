package Character;

/**
 * 
 * É um personagem especifico do jogo.
 * Possui um atributo Power.
 *
 */
public class Fighter extends Character {
	
	//atributos
	protected int mPower; ///< Power do personagem Fighter
	
	/**
	 * Construtor de Fighter com 2 parametros.
	 * @param alias Nome do personagem
	 * @param power	 Poder do personagem
	 */
	public Fighter(String alias, int power){
		super(alias);
		if(power < 30)
		{
			this.mPower = 30;
		}
		else if(power > 100)
		{
			this.mPower = 100;
		}
		else
		{
			this.mPower = power;
		}
			
		
	}
	
	/**
	 * Sobreescrita do método da classe pai, pois para Fighter o cálculo do ataque depende também do atributo Power.
	 * @return Retorna pontos de Ataque levando em conta todos os itens no inventario do personagem.
	 */
	protected int getAttackPoints(){
		return (int)(((mStrength*0.6 + mDexterity*0.4 + mPower*0.1 + getInventoryAttack())*(mXP))/2);
	}
	
	/**
	 * Sobreescrita do método da classe pai, pois para Fighter o cálculo da defesa é diferente.
	 * @return Retorna pontos de Defesa levando em conta todos os itens no inventario do personagem.
	 */
	protected int getDefensePoints(){	
		return (int)((mConstitution*0.6 + mDexterity*0.1 + mSpeed*0.3 + getInventoryDefense())*(mXP)/6);
	}
	
	/**
	 * Sobreescrita do método para atacar outro personagem, pois calcula uma chance de Erro do ataque.
	 * @param victim Recebe o personagem que sera atacado
	 * @return true se o ataque foi efetuado. False quando a vitima já esta morta.
	 */
	public boolean attackCharacter(Character victim){
		if(victim.getHP() == 0){
			System.out.println(victim.getName() + " is dead!");
			return false; // tentativa de atacar alguem q já está morto.
		}
		double chance = Math.random();
		if (chance > (0.1)/(this.mXP))
		{			
			return super.attackCharacter(victim);
		}
		else
		{
			System.out.println(getName() + " MISS " + victim.getName());
			return true;
		}
	}
	
	public String toString(){
		return "Fighter, POW: "+ mPower+"\n"+super.toString();
	}
}