package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class Ventana extends JFrame {

	private JPanel contentPane;

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
		
		PanelGerente panelGerente = new PanelGerente(this);
		tabbedPane.addTab("Gerente", null, panelGerente, null);
		
		
		PanelConsultaViajes panelConsultaViajes = new PanelConsultaViajes();
		tabbedPane.addTab("Panel Consuta", null, panelConsultaViajes, null);
	}
}
