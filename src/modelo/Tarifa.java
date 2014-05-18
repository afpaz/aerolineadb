package modelo;

public class Tarifa 
{
	private String id;
	private int valorKm;
	private double limInfKm;
	private double limSupKm;
	
	public Tarifa(String id, int valorKm, double limInfKm, double limSupKm) {
		super();
		this.id = id;
		this.valorKm = valorKm;
		this.limInfKm = limInfKm;
		this.limSupKm = limSupKm;
	}	
	public String getid() {
		return id;
	}	
	public int getValorKm() {
		return valorKm;
	}
	public double getLimInfKm() {
		return limInfKm;
	}
	public double getLimSupKm() {
		return limSupKm;
	}
	public String toString()
	{
		return  id+ "   "+ valorKm+"";
	}
}
