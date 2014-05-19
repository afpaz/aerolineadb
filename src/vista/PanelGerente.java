package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelGerente extends JPanel 
{
	private PanelClases panelClases;
	private PanelDestinos panelDestinos;
	private JButton btnGenerarReporte;
	private PanelTarifa panelTarifa;
	private PanelDescuento panelDescuento;
	private PanelRutas panelRutas;
	
	/**
	 * Create the panel.
	 */
	public PanelGerente( ) 
	{
		setBackground( new Color(184, 207, 229) );
		setLayout(null);
		
		btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.setBounds(536, 382, 134, 23);
		add(btnGenerarReporte);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBounds(0, 0, 680, 383);
		add(tabbedPane);
		
		panelTarifa = new PanelTarifa();
		tabbedPane.addTab("Tarifas", null, panelTarifa, null);
		
		panelDestinos = new PanelDestinos();
		tabbedPane.addTab("Destinos", null, panelDestinos, null);
		
		panelDescuento = new PanelDescuento();
		tabbedPane.addTab("Descuentos", null, panelDescuento, null);
		
		panelClases = new PanelClases();
		tabbedPane.addTab("Clases", null, panelClases, null);
		
		panelRutas = new PanelRutas();
		tabbedPane.addTab("Rutas", null, panelRutas, null);
	}

	public PanelClases getPanelClases( )
	{
		return panelClases;
	}
	
	public PanelDestinos getPanelDestinos()
	{
		return panelDestinos;
	}

	public JButton getBtnGenerarReporte( )
	{
		return btnGenerarReporte;
	}

	public PanelTarifa getPanelTarifa( )
	{
		return panelTarifa;
	}

	public PanelDescuento getPanelDescuento( )
	{
		return panelDescuento;
	}	
}
