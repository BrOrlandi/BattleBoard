package Character;

import Overview.Board;

/**
 * 
 * É um personagem específico do jogo.
 * Possui o atributo Accuracy.
 *
 */
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
	 * @return true se o ataque foi efetuado. False quando a vitima já esta morta.
	 */
	public boolean attackCharacter(Character victim, Board board){
			
		if(victim.getHP() == 0){
			System.out.println(victim.getName() + " is dead!");
			return false; // tentativa de atacar alguem q já está morto.
		}
		double chance = Math.random();
		if (chance > (0.07)/(this.mXP))
		{			
			return super.attackCharacter(victim, board);
		}
		else
		{
			System.out.println(getName() + " MISS " + victim.getName());
			return true;
		}
	}
	
	public String toString(){
		return "Ranger, ACC: "+ mAccuracy+"\n"+super.toString();
	}
}