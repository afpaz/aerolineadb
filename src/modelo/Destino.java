package modelo;

public class Destino
{
	private String id;
	private double latitud;
	private double longitud;
	private String descripcion;
	public Destino(String id, double latitud, double longitud,
			String descripcion) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
		this.descripcion = descripcion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
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
