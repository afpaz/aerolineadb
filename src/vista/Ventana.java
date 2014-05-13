package vista;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import modelo.Clase;
import modelo.Destino;
import modelo.Tarifa;

public class Ventana extends JFrame {

	private JPanel contentPane;
	PanelConsultaViajes panelConsultaViajes;
	private PanelGerente panelGerente;
	/**
	 * Create the frame.
	 */
	public Ventana() {
		setTitle("Aerolinea");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 676, 444);
		contentPane.add(tabbedPane);
		
		panelGerente = new PanelGerente();
		tabbedPane.addTab("Gerente", null, panelGerente, null);
		
		
		panelConsultaViajes = new PanelConsultaViajes();
		tabbedPane.addTab("Panel Consuta", null, panelConsultaViajes, null);
	}
	
	public PanelConsultaViajes getPanelConsultaViajes()
	{
		return this.panelConsultaViajes;
	}

	public PanelGerente getPanelGerente( )
	{
		return panelGerente;
	}	
	
	public void actualizarPanelClases(ArrayList< Clase > clases)
	{
		getPanelGerente( ).getPanelClases( ).getListClases( ).setListData( clases.toArray( ) );
	}
	
	
	public void actualizarListaDestinos (ArrayList<Destino> destinos)
	{
		panelGerente.getPanelDestinos().getListDestinos().setListData(destinos.toArray());
	}

	public void actualizarListaTarifas(ArrayList<Tarifa> tarifas) 
	{
		panelGerente.getPanelTarifa().getListTarifas().setListData(tarifas.toArray());
	}
}
