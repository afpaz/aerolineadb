package vista;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 666, 434);
		contentPane.add(tabbedPane);
		
		Panel panel = new Panel();
		tabbedPane.addTab("Gerente", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTiquetes = new JLabel("Tiquetes:");
		lblTiquetes.setBounds(10, 11, 92, 14);
		panel.add(lblTiquetes);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(615, 65, 46, 14);
		panel.add(lblCosto);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(517, 83, 134, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnEditarCosto = new JButton("Editar Costo");
		btnEditarCosto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				DialogEditarCosto dialog = new DialogEditarCosto(Ventana.this);
				dialog.setVisible(true);
			}
		});
		btnEditarCosto.setBounds(517, 127, 134, 23);
		panel.add(btnEditarCosto);
		
		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DialogGenerarReporte dialog = new DialogGenerarReporte(Ventana.this);
				dialog.setVisible(true);
			}
		});
		btnGenerarReporte.setBounds(517, 161, 134, 23);
		panel.add(btnGenerarReporte);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 497, 359);
		panel.add(scrollPane);
		JList listTiquetes = new JList();
		scrollPane.setViewportView(listTiquetes);
		
		PanelConsultaViajes panelConsultaViajes = new PanelConsultaViajes();
		tabbedPane.addTab("Panel Consuta", null, panelConsultaViajes, null);
	}
}
