package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import control.ControladoraBD;


public class ItemSet 
{
	private ArrayList<String> listaCiudades, listaClases;
	private ArrayList<Pasabordo> listaPasabordos;
	
	public ItemSet()
	{
		listaCiudades = new ArrayList<String>();
		listaClases = new ArrayList<String>();
		listaPasabordos = new ArrayList<Pasabordo>();
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
	
	public ArrayList<Pasabordo> llenarListaPasabordos() throws ClassNotFoundException, SQLException
	{
		String con[] = ControladoraBD.generarPasabordos();
		
		if(!con[0].equals("No hay pasabordos disponibles!!!"))
			for(String var : con)
			{
				String arreglo[] = var.split("%");
				Pasabordo pasabordo = new Pasabordo(arreglo[0], arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5], arreglo[6], arreglo[7], arreglo[8]);
				
				listaPasabordos.add(pasabordo);
			}
		
		return listaPasabordos;
	}	
}
