package modelo;

import java.util.Date;

public class Ruta
{
	
	private int id;
	private Date fecha;
	private String idViaje;
	private String idTarifa;
	
	public Ruta()
	{
		// TODO Auto-generated constructor stub
	}
	
	

	public Ruta(int id, Date fecha, String idViaje, String idTarifa)
	{
		this.id = id;
		this.fecha = fecha;
		this.idViaje = idViaje;
		this.idTarifa = idTarifa;
	}



	public int getId()
	{
		return id;
	}

	public void setId(int id)
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

	public String getIdViaje()
	{
		return idViaje;
	}

	public void setIdViaje(String idViaje)
	{
		this.idViaje = idViaje;
	}

	public String getIdTarifa()
	{
		return idTarifa;
	}

	public void setIdTarifa(String idTarifa)
	{
		this.idTarifa = idTarifa;
	}
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return getId()+":"+ getFecha().toString() + " | " + getIdViaje() + "-" + getIdTarifa();
	}
	
	

}
