package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PanelGerente extends JPanel 
{
	private JTextField textField;
	private Ventana principal;
	
	/**
	 * Create the panel.
	 */
	public PanelGerente( Ventana principal ) 
	{
		this.principal = principal;
		setBackground( new Color(184, 207, 229) );
		setLayout(null);
		JLabel lblTiquetes = new JLabel("Tiquetes:");
		lblTiquetes.setBounds(10, 11, 92, 14);
		add(lblTiquetes);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(615, 65, 46, 14);
		add(lblCosto);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(517, 83, 134, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnEditarCosto = new JButton("Editar Costo");
		btnEditarCosto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				DialogEditarCosto dialog = new DialogEditarCosto(PanelGerente.this.principal);
				dialog.setBackground( new Color(184, 207, 229) );
				dialog.setVisible(true);
			}
		});
		btnEditarCosto.setBounds(517, 127, 134, 23);
		add(btnEditarCosto);
		
		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DialogGenerarReporte dialog = new DialogGenerarReporte(PanelGerente.this.principal);
				dialog.setVisible(true);
			}
		});
		btnGenerarReporte.setBounds(517, 161, 134, 23);
		add(btnGenerarReporte);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 497, 359);
		add(scrollPane);
		JList listTiquetes = new JList();
		scrollPane.setViewportView(listTiquetes);
	}

}
