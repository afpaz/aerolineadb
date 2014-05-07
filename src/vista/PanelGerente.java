package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PanelGerente extends JPanel 
{
	private Ventana principal;
	private PanelClases panelClases;
	private PanelDestinos panelDestinos;
	
	/**
	 * Create the panel.
	 */
	public PanelGerente( Ventana principal ) 
	{
		this.principal = principal;
		setBackground( new Color(184, 207, 229) );
		setLayout(null);
		
		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DialogGenerarReporte dialog = new DialogGenerarReporte(PanelGerente.this.principal);
				dialog.setVisible(true);
			}
		});
		btnGenerarReporte.setBounds(536, 382, 134, 23);
		add(btnGenerarReporte);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBounds(0, 0, 680, 383);
		add(tabbedPane);
		
		PanelTarifa panelTarifa = new PanelTarifa();
		tabbedPane.addTab("Tarifas", null, panelTarifa, null);
		
		panelDestinos = new PanelDestinos();
		tabbedPane.addTab("Destinos", null, panelDestinos, null);
		
		PanelDescuento panelDescuento = new PanelDescuento();
		tabbedPane.addTab("Descuentos", null, panelDescuento, null);
		
		panelClases = new PanelClases();
		tabbedPane.addTab("Clases", null, panelClases, null);
	}

	public PanelClases getPanelClases( )
	{
		return panelClases;
	}
	
	public PanelDestinos getPanelDestinos()
	{
		return panelDestinos;
	}
	
}
