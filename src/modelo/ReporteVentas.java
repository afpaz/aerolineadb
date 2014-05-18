package modelo;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReporteVentas {

	private double valorTotal;
	private int cantidad;
	private ArrayList<String> ranking;
	
	public ReporteVentas(double valorTotal, int cantidad, ArrayList<String> ranking) {
		super();
		this.valorTotal = valorTotal;
		this.cantidad = cantidad;
		this.ranking = ranking;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<String> getRanking() {
		return ranking;
	}

	public void setRanking(ArrayList<String> ranking) {
		this.ranking = ranking;
	}
	
	
	
	
}
