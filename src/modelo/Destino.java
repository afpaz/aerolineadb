package modelo;

public class Destino
{
	private int id;
	private String latitud;
	private String longitud;
	private String descripcion;
	public Destino(int id, String latitud, String longitud,
			String descripcion) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.descripcion = descripcion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString()
	{
		return id+"";
	}

}
