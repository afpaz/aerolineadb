package vista;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class PanelClases extends JPanel 
{

	private Ventana principal;
	private JTextField txtNombreClase;
	private JTextField txtMultiplicador;
	/**
	 * Create the panel.
	 */
	public PanelClases(Ventana principal) 
	{
		setBackground( new Color(184, 207, 229) );
		this.principal = principal;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 322, 332);
		add(scrollPane);
		
		JList listClases = new JList();
		scrollPane.setViewportView(listClases);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(373, 122, 81, 14);
		add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(373, 191, 81, 14);
		add(lblDescripcion);
		
		JLabel lblMultiplicador = new JLabel("Multiplicador:");
		lblMultiplicador.setBounds(373, 155, 85, 14);
		add(lblMultiplicador);
		
		txtNombreClase = new JTextField();
		txtNombreClase.setBounds(479, 119, 133, 20);
		add(txtNombreClase);
		txtNombreClase.setColumns(10);
		
		txtMultiplicador = new JTextField();
		txtMultiplicador.setBounds(479, 152, 133, 20);
		add(txtMultiplicador);
		txtMultiplicador.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(523, 299, 89, 23);
		add(btnGuardar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(479, 186, 133, 92);
		add(textArea);
		
	}
}
