package vista;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import modelo.Clase;
import modelo.Descuento;
import modelo.Destino;
import modelo.Tarifa;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private PanelConsultaViajes panelConsultaViajes;
	private PanelGerente panelGerente;
	private PanelReporteVentas panelReporteVentas;
	private PanelClientes panelClientes;
	private PanelDemanda panelDemanda;
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
		tabbedPane.addTab("Viajes", null, panelConsultaViajes, null);
		
		panelReporteVentas = new PanelReporteVentas();
		tabbedPane.addTab("Reporte de Ventas", null, panelReporteVentas, null);
		
		panelClientes = new PanelClientes();
		tabbedPane.addTab("Clientes", null, panelClientes, null);
		
		panelDemanda = new PanelDemanda();
		tabbedPane.addTab("Reporte de demanda", null, panelDemanda, null);
	}
	
	public PanelDemanda getPanelDemanda() {
		return panelDemanda;
	}

	public void setPanelDemanda(PanelDemanda panelDemanda) {
		this.panelDemanda = panelDemanda;
	}

	public PanelConsultaViajes getPanelConsultaViajes()
	{
		return this.panelConsultaViajes;
	}

	public PanelGerente getPanelGerente( )
	{
		return panelGerente;
	}	
	
	public PanelReporteVentas getPanelReporteVentas()
	{
		return this.panelReporteVentas;
	}
	
	public void actualizarPanelClases(ArrayList< Clase > clases)
	{
		getPanelGerente( ).getPanelClases( ).getListClases( ).setListData( clases.toArray( ) );
	}
	
	
	public void actualizarListaDestinos (ArrayList<Destino> destinos)
	{
		panelGerente.getPanelDestinos().getListDestinos().setListData(destinos.toArray());
		
		panelDemanda.getCbxOrigen().setModel(new javax.swing.DefaultComboBoxModel(destinos.toArray()));
		panelDemanda.getCbxDestino().setModel(new javax.swing.DefaultComboBoxModel(destinos.toArray()));
		
		panelDemanda.getCbxOrigen().setSelectedIndex(-1);
		panelDemanda.getCbxDestino().setSelectedIndex(-1);
	}

	public void actualizarListaTarifas(ArrayList<Tarifa> tarifas) 
	{
		panelGerente.getPanelTarifa().getListTarifas().setListData(tarifas.toArray());
	}

	public void actualizarListaDescuentos( ArrayList< Descuento > descuentos )
	{
		panelGerente.getPanelDescuento( ).getList( ).setListData(descuentos.toArray());
	}	
}
