package Character;

import BattleBoardExceptions.CharacterFromSameTeamException;
import BattleBoardExceptions.DeadCharacterException;
import BattleBoardExceptions.OutOfRangeCharacterException;
import Overview.Board;
import Utilities.Pair;

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
		//return (int)(((mStrength*0.6 + mDexterity*0.4 + mPower*0.1 + getInventoryAttack())*(mXP))/2);
		if(mWeapon == null)
		{
			return (int)(((mStrength*0.6 + mDexterity*0.4 + mPower*0.1)*(mXP))/2);
		}
		return (int)(((mStrength*0.6 + mDexterity*0.4 + mPower*0.1 + mWeapon.getAttackpts())*(mXP))/2);
	}
	
	/**
	 * Sobreescrita do método da classe pai, pois para Fighter o cálculo da defesa é diferente.
	 * @return Retorna pontos de Defesa levando em conta todos os itens no inventario do personagem.
	 */
	protected int getDefensePoints(){	
		//return (int)((mConstitution*0.6 + mDexterity*0.1 + mSpeed*0.3 + getInventoryDefense())*(mXP)/6);
		if(mArmor == null)
		{
			return (int)((mConstitution*0.6 + mDexterity*0.1 + mSpeed*0.3)*(mXP)/6);
		}
		return (int)((mConstitution*0.6 + mDexterity*0.1 + mSpeed*0.3 + mArmor.getDefensepts())*(mXP)/6);
	}
	
	/**
	 * Sobreescrita do método para atacar outro personagem, pois calcula uma chance de Erro do ataque.
	 * @param victim o personagem que sera atacado.
	 * @param distance distancia entre os personagens.
	 * @return Pair de valores, no qual o primeiro é o tipo de evento do ataque. E o segundo valor é o dano causado pelo ataque.
	 * @throws DeadCharacterException 
	 * @throws CharacterFromSameTeamException 
	 * @throws OutOfRangeCharacterException 
	 * @see NORMAL_ATTACK
	 */
	public Pair<Integer, Integer> attackCharacter(Character victim, int distance) throws DeadCharacterException, CharacterFromSameTeamException, OutOfRangeCharacterException{
			if(isDead())
		{
			//System.out.println(getName() + " is dead and can't attack.");
			//return false;
			throw new DeadCharacterException(this,getName() + " is dead and can't attack.");
		}
		
		double chance = Math.random();
		if (chance > (0.1)/(this.mXP))
		{			
			return super.attackCharacter(victim, distance);
		}
		else
		{
			//System.out.println(getName() + " MISS!");
			return new Pair<Integer, Integer>(Character.MISS_ATTACK, 0);
		}
	}
	
	public String toString(){
		return "Fighter, POW: "+ mPower+"\n"+super.toString();
	}
}