package modelo;

import java.util.Date;

public class PasabordoVendedor
{
	String tiqueteId;
	Date fecha;
	String origen;
	String destino;
	String hora;
	String comprador;

	public PasabordoVendedor(String tiqueteId, Date fecha, String origen, String destino, String hora, String comprador)
	{
		this.tiqueteId = tiqueteId;
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;
		this.hora = hora;
		this.comprador = comprador;
	}

	public String getTiqueteId()
	{
		return tiqueteId;
	}

	public void setTiqueteId(String tiqueteId)
	{
		this.tiqueteId = tiqueteId;
	}

	public Date getFecha()
	{
		return fecha;
	}

	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
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

	public String getHora()
	{
		return hora;
	}

	public void setHora(String hora)
	{
		this.hora = hora;
	}

	public String getComprador()
	{
		return comprador;
	}

	public void setComprador(String comprador)
	{
		this.comprador = comprador;
	}

}
