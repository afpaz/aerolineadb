package modelo;

import java.util.Date;

public class Vuelo
{
	
	private String id;
	private Date fecha;
	private int cupoMax;
	private String origen;
	private String destino;
	private int cupoActual;
	
	public Vuelo(String id, Date fecha, int cupoMax, String origen, String destino, int cupoActual)
	{
		this.id = id;
		this.fecha = fecha;
		this.cupoMax = cupoMax;
		this.origen = origen;
		this.destino = destino;
		this.cupoActual = cupoActual;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Date getFecha()
	{
		return fecha;
	}

	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

	public int getCupoMax()
	{
		return cupoMax;
	}

	public void setCupoMax(int cupoMax)
	{
		this.cupoMax = cupoMax;
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

	public int getCupoActual()
	{
		return cupoActual;
	}

	public void setCupoActual(int cupoActual)
	{
		this.cupoActual = cupoActual;
	}
	
	
	
	

}
