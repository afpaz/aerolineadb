package modelo;

import java.util.Date;



public class Venta
{
	
	private String venta_id;
	private String fecha;
	private String vendedor;
	private String comprador;
	
	public Venta()
	{
		// TODO Auto-generated constructor stub
	}

	public Venta(String venta_id,	String fecha, String vendedor, String comprador)
	{
		this.venta_id = venta_id;
		this.fecha = fecha;
		this.vendedor = vendedor;
		this.comprador = comprador;
	}


	public String getVenta_id() {
		return venta_id;
	}


	public void setVenta_id(String venta_id) {
		this.venta_id = venta_id;
	}
	
	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getVendedor() {
		return vendedor;
	}



	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}



	public String getComprador() {
		return comprador;
	}



	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	
	

}
