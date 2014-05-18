package modelo;

public class Pasabordo 
{
	String fecha, idViaje, origen, destino, vueloId, clase, cliente, vendedor, ventaId;
	
	public Pasabordo(String fecha, String ventaId, String idViaje, String origen, String destino,
			String vueloId, String clase, String cliente, String vendedor)
	{
		this.fecha = fecha;
		this.ventaId = ventaId;
		this.idViaje = idViaje;
		this.origen = origen;
		this.destino = destino;
		this.vueloId = vueloId;
		this.clase = clase;
		this.cliente = cliente;
		this.vendedor = vendedor;
	}
	
	public String toString()
	{
		return "Id Viaje: " + idViaje + ", Cliente: " + cliente;
	}
	
	public String darVentaId()
	{
		return this.ventaId;
	}
	
	public String darVendedor()
	{
		return this.vendedor;
	}
	
	public String darCliente()
	{
		return this.cliente;
	}
	
	public String darClase()
	{
		return this.clase;
	}
	
	public String darVueloId()
	{
		return this.vueloId;
	}
	
	public String darFecha()
	{
		return this.fecha;
	}
	
	public String darIdViaje()
	{
		return this.idViaje;
	}
	
	public String darOrigeb()
	{
		return this.origen;
	}
	
	public String darDestino()
	{
		return this.destino;
	}
}
