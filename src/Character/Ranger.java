package Character;

public class Ranger extends Character{
	
	protected int mAccuracy; ///< Precisão do personagem Ranger
	
	/**
	 * Construtor de Ranger que recebe 2 parametros.
	 * @param alias Nome do personagem
	 * @param acc precisão do ranger
	 */
	public Ranger(String alias, int acc){
		super(alias);
		if(acc < 30)
	    {
	        mAccuracy = 30;
	    }
	    else if(acc > 100)
	    {
	        mAccuracy = 100;
	    }
	    else
	    {
	        mAccuracy = acc;
	    }
	}
	
	/**
	 * Sobreescrita do método da classe pai, pois para Ranger o cálculo do ataque depende também do atributo Accuracy.
	 * @return Retorna pontos de Ataque levando em conta todos os itens no inventario do personagem.
	 * @see mAccuracy
	 */
	protected int getAttackPoints(){
		return (int)((mStrength*0.5 + mDexterity*0.5 + mAccuracy*0.1 + getInventoryAttack())*(mXP)/2);
	}
	
	/**
	 * Sobreescrita do método da classe pai, pois para Fighter o cálculo da defesa é diferente.
	 * @return Retorna pontos de Defesa levando em conta todos os itens no inventario do personagem.
	 */
	protected int getDefensePoints(){	
		return (int)((mConstitution*0.5 + mDexterity*0.2 + mSpeed*0.3 + getInventoryDefense())*(mXP)/6);
	}
	
	/**
	 * Sobreescrita do método para atacar outro personagem, pois calcula uma chance de Erro do ataque.
	 * @param victim Recebe o personagem que sera atacado
	 */
	public void attackCharacter(Character victim){
		double chance = Math.random();
		if (chance > (0.07)/(this.mXP))
		{			
			super.attackCharacter(victim);
		}
		else
		{
			System.out.println(getName() + " MISS " + victim.getName());
		}
	}
	
	/**
	 * Sobreescrita do método print para imprimir também o Accuracy do Ranger.
	 */
	public void print(){
		System.out.println("Ranger, ACC: "+ mAccuracy);
		super.print();
	}
}