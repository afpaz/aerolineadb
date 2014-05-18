package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import control.ControladoraBD;


public class ItemSet 
{
	private ArrayList<String> listaCiudades, listaClases;
	
	public ItemSet()
	{
		listaCiudades = new ArrayList<String>();
		listaClases = new ArrayList<String>();
	}
	
	
	public ArrayList<String> llenarListaCiudades() throws ClassNotFoundException, SQLException
	{
		String[] con = ControladoraBD.getLugares();
		
		if(!con[0].equals("No hay ciudades!!!"))
			for(String var : con)
				listaCiudades.add(var);
		
		else
			listaCiudades.add("No hay ciudades!!!");
		
		return listaCiudades;
	}
	
	public ArrayList<String> llenarListaClases() throws ClassNotFoundException, SQLException
	{
		String[] con = ControladoraBD.getClases();
		
		if(!con[0].equals("No hay clases!!!"))
			for(String var : con)
				listaClases.add(var);
		
		else
			listaClases.add("No hay clases!!!");
		
		return listaClases;
	}
	

}
