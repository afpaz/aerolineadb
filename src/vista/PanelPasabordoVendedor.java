package vista;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelPasabordoVendedor extends JPanel
{
	//Componentes UI
	JTable pasabordos;
	JComboBox vuelos;
	
	public PanelPasabordoVendedor()
	{
		iniciarUI();
		eventos();

		GridLayout layout = new GridLayout(2,1);
		setLayout(layout);
		
		JPanel panelSup = new JPanel();
		GridLayout layoutPanelSup = new GridLayout(1,2);
		panelSup.setLayout(layoutPanelSup);
		JLabel etiqueta = new JLabel("Vuelo");
		panelSup.add(etiqueta);
		panelSup.add(vuelos);
		
		
		add(panelSup);
		add(new JScrollPane(pasabordos));
		
		
	}
	
	public void iniciarUI()
	{
		String [] columnas ={"Nro. Tiquete", "Fecha", "Origen", "Destino", "Hora Salida", "Cliente"};
		DefaultTableModel model = new DefaultTableModel(columnas,1);
		pasabordos = new JTable(model);
		
		vuelos = new JComboBox();
	}
	
	public void eventos()
	{
		
	}

}
