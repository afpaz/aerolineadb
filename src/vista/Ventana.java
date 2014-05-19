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
import modelo.Venta;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private PanelConsultaViajes panelConsultaViajes;
	private PanelGerente panelGerente;
	private PanelReporteVentas panelReporteVentas;
	private PanelPasabordoVendedor panelPasabordoVendedor;
	private PanelClientes panelClientes;
	private PanelDemanda panelDemanda;
	private PanelVendedores panelVendedores;
	private PanelAnulaciones panelAnulaciones;
	/**
	 * Create the frame.
	 */
	public Ventana() {
		setTitle("Aerolinea");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 675, 483);
		contentPane.add(tabbedPane);
		
		panelGerente = new PanelGerente();
		panelGerente.getPanelTarifa().setBounds(2, 2, 708, 355);
		panelGerente.getBtnGenerarReporte().setLocation(526, 400);
		tabbedPane.addTab("Gerente", null, panelGerente, null);
		
		
		panelConsultaViajes = new PanelConsultaViajes();
		tabbedPane.addTab("Vuelos", null, panelConsultaViajes, null);
		
		panelReporteVentas = new PanelReporteVentas();
		tabbedPane.addTab("Reporte de Ventas", null, panelReporteVentas, null);
		
		panelPasabordoVendedor = new PanelPasabordoVendedor();
		tabbedPane.addTab("Pasabordos - Vendedor",null,panelPasabordoVendedor,null);
		
		panelClientes = new PanelClientes();
		tabbedPane.addTab("Clientes", null, panelClientes, null);
		
		panelDemanda = new PanelDemanda();
		tabbedPane.addTab("Reporte de demanda", null, panelDemanda, null);
		
		panelVendedores = new PanelVendedores();
		tabbedPane.addTab("Vendedores", null, panelVendedores, null);
		
		panelAnulaciones = new PanelAnulaciones();
		tabbedPane.addTab("Anulaciones", null, panelAnulaciones, null);
	}
	
	public PanelVendedores getPanelVendedores() {
		return panelVendedores;
	}

	public void setPanelVendedores(PanelVendedores panelVendedores) {
		this.panelVendedores = panelVendedores;
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
	
	public PanelAnulaciones getPanelAnulaciones()
	{
		return this.panelAnulaciones;
	}
	
	public void actualizarPanelClases(ArrayList< Clase > clases)
	{
		getPanelGerente( ).getPanelClases( ).getListClases( ).setListData( clases.toArray( ) );
	}
	
	
	public void actualizarListaDestinos (ArrayList<Destino> destinos)
	{
		panelGerente.getPanelDestinos().getListDestinos().setListData(destinos.toArray());	
	}
	public void listaVentas(ArrayList<Venta> ventas)
	{
		panelVendedores.getListVentas().setListData(ventas.toArray());
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
