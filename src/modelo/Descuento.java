package modelo;

public class Descuento
{
	private int id;
	private String fechaLimiteInferior;
	private String fechaLimiteSuperior;
	private String ocupacionLimiteInferior;
	private String ocupacionLimiteSuperior;
	private double porcentajeDescuento;
	
	public Descuento( int id, String fechaLimiteInferior, String fechaLimiteSuperior, String ocupacionLimiteInferior, 
			String ocupacionLimiteSuperior, double porcentajeDescuento )
	{
		this.id = id;
		this.fechaLimiteInferior = fechaLimiteInferior;
		this.fechaLimiteSuperior = fechaLimiteSuperior;
		this.ocupacionLimiteInferior = ocupacionLimiteInferior;
		this.ocupacionLimiteSuperior = ocupacionLimiteSuperior;
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public String getFechaLimiteInferior( )
	{
		return fechaLimiteInferior;
	}

	public void setFechaLimiteInferior( String fechaLimiteInferior )
	{
		this.fechaLimiteInferior = fechaLimiteInferior;
	}

	public String getFechaLimiteSuperior( )
	{
		return fechaLimiteSuperior;
	}

	public void setFechaLimiteSuperior( String fechaLimiteSuperior )
	{
		this.fechaLimiteSuperior = fechaLimiteSuperior;
	}

	public String getOcupacionLimiteInferior( )
	{
		return ocupacionLimiteInferior;
	}

	public void setOcupacionLimiteInferior( String ocupacionLimiteInferior )
	{
		this.ocupacionLimiteInferior = ocupacionLimiteInferior;
	}

	public String getOcupacionLimiteSuperior( )
	{
		return ocupacionLimiteSuperior;
	}

	public void setOcupacionLimiteSuperior( String ocupacionLimiteSuperior )
	{
		this.ocupacionLimiteSuperior = ocupacionLimiteSuperior;
	}

	public double getPorcentajeDescuento( )
	{
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento( double porcentajeDescuento )
	{
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public int getId( )
	{
		return id;
	}
	
	public String toString()
	{
		return "( " + fechaLimiteInferior + " - " + fechaLimiteSuperior + " ), ( " + ocupacionLimiteInferior + " - " + ocupacionLimiteSuperior + " ), " + porcentajeDescuento;
	}
}
