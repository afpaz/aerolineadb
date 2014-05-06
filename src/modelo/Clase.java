package modelo;

public class Clase
{
	private String nombre;
	private String descripcion;
	private int multiplicador;
	
	public Clase( String nombre, String descripcion, int multiplicador )
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.multiplicador = multiplicador;
	}

	public String getNombre( )
	{
		return nombre;
	}

	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}

	public String getDescripcion( )
	{
		return descripcion;
	}

	public void setDescripcion( String descripcion )
	{
		this.descripcion = descripcion;
	}

	public int getMultiplicador( )
	{
		return multiplicador;
	}

	public void setMultiplicador( int multiplicador )
	{
		this.multiplicador = multiplicador;
	}
	
	public String toString()
	{
		return nombre;
	}
}
