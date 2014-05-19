package vista;


import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modelo.ItemSet;
import modelo.Pasabordo;
public class PanelClientes extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private PanelPasabordo panelPasabordo;
	private PanelRealizarCompra panelRealizarCompra;
	
	public PanelClientes()
	{
		try
		{	
			setLayout(null);
			
			JTabbedPane tabbe = new JTabbedPane(JTabbedPane.TOP);	
			tabbe.setBounds(0, 0, 680, 450);
			tabbe.setBackground(Color.GRAY);
			this.add(tabbe);
			
			panelRealizarCompra = new PanelRealizarCompra(this);
			tabbe.addTab("Compar", null, panelRealizarCompra, "Permite comprar vuelos de la aerolinea");
			
			panelPasabordo = new PanelPasabordo(this);
			tabbe.addTab("Pasabordo", null, panelPasabordo, "Permite obtener los pasabordos");
			
			llenarCombos();			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void llenarCombos() throws ClassNotFoundException, SQLException
	{
		ArrayList<String> arreglo = new ItemSet().llenarListaCiudades();
		ArrayList<String> arregloClases = new ItemSet().llenarListaClases();
		
		for(String var : arreglo)
		{
			panelRealizarCompra.getComboOringen().addItem(var);
			panelRealizarCompra.getComboDestino().addItem(var);
		}
		
		for(String var : arregloClases)
			panelRealizarCompra.getComboClases().addItem(var);
	}
	
	public ArrayList<Pasabordo> darListaPasabordos() throws ClassNotFoundException, SQLException
	{
		return new ItemSet().llenarListaPasabordos();
	}
}
