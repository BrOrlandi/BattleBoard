package Utilities;

public class Pair<A, B> {
	
	private A first;
	private B second;
	
	public Pair(A first, B second)
	{
		this.first = first;
		this.second = second;
	}
	
	public void setFirst(A first)
	{
		this.first = first;
	}
	
	public void setSecond(B second)
	{
		this.second = second;
	}
	
	public A getFirst()
	{
		return first;
	}
	
	public B getSecond()
	{
		return second;
	}
	
	public boolean equal(Pair pair)
	{
		if(this.first == pair.getFirst() && this.second == pair.getSecond())
		{
			return true;
		}
		else return false;
	}
	
	@Override
	public String toString()
	{
		
		return "Pair<" + getFirst() + "," + getSecond() + ">";
	}
}
