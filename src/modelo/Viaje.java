package modelo;

import java.util.Date;

public class Viaje
{

	String id;
	String sentido;
	String clase_id;
	String descuento_id;
	String origen;
	String destino;
	Date fecha;

	public Viaje(String id, String sentido, String clase_id, String descuento_id, String origen, String destino, Date fecha)
	{
		this.id = id;
		this.sentido = sentido;
		this.clase_id = clase_id;
		this.descuento_id = descuento_id;
		this.origen = origen;
		this.destino = destino;
		this.fecha = fecha;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getSentido()
	{
		return sentido;
	}

	public void setSentido(String sentido)
	{
		this.sentido = sentido;
	}

	public String getClase_id()
	{
		return clase_id;
	}

	public void setClase_id(String clase_id)
	{
		this.clase_id = clase_id;
	}

	public String getDescuento_id()
	{
		return descuento_id;
	}

	public void setDescuento_id(String descuento_id)
	{
		this.descuento_id = descuento_id;
	}

	public String getOrigen()
	{
		return origen;
	}

	public void setOrigen(String origen)
	{
		this.origen = origen;
	}

	public String getDestino()
	{
		return destino;
	}

	public void setDestino(String destino)
	{
		this.destino = destino;
	}

	public Date getFecha()
	{
		return fecha;
	}

	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

}
