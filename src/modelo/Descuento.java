package modelo;

public class Descuento
{
	private String id;
	private String fechaLimiteInferior;
	private String fechaLimiteSuperior;
	private int ocupacionLimiteInferior;
	private int ocupacionLimiteSuperior;
	private int porcentajeDescuento;
	
	public Descuento( String id, String fechaLimiteInferior, String fechaLimiteSuperior, int ocupacionLimiteInferior, 
			int ocupacionLimiteSuperior, int porcentajeDescuento )
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

	public int getOcupacionLimiteInferior( )
	{
		return ocupacionLimiteInferior;
	}

	public void setOcupacionLimiteInferior( int ocupacionLimiteInferior )
	{
		this.ocupacionLimiteInferior = ocupacionLimiteInferior;
	}

	public int getOcupacionLimiteSuperior( )
	{
		return ocupacionLimiteSuperior;
	}

	public void setOcupacionLimiteSuperior( int ocupacionLimiteSuperior )
	{
		this.ocupacionLimiteSuperior = ocupacionLimiteSuperior;
	}

	public int getPorcentajeDescuento( )
	{
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento( int porcentajeDescuento )
	{
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public String getId( )
	{
		return id;
	}
	
	public String toString()
	{
		return id;
	}
}
