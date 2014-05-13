package modelo;

public class Tarifa 
{
	private int id;
	private int valorKm;
	private int limInfKm;
	private int limSupKm;
	
	public Tarifa(int id, int valorKm, int limInfKm, int limSupKm) {
		super();
		this.id = id;
		this.valorKm = valorKm;
		this.limInfKm = limInfKm;
		this.limSupKm = limSupKm;
	}	
	public int getid() {
		return id;
	}	
	public int getValorKm() {
		return valorKm;
	}
	public int getLimInfKm() {
		return limInfKm;
	}
	public int getLimSupKm() {
		return limSupKm;
	}
	public String toString()
	{
		return  id+ "   "+ valorKm+"";
	}
}
