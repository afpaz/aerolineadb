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
		scrollPane.setBounds(10, 11, 271, 319);
		add(scrollPane);
		
		JList listClases = new JList();
		scrollPane.setViewportView(listClases);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(342, 92, 81, 14);
		add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(342, 161, 81, 14);
		add(lblDescripcion);
		
		JLabel lblMultiplicador = new JLabel("Multiplicador:");
		lblMultiplicador.setBounds(342, 122, 85, 23);
		add(lblMultiplicador);
		
		txtNombreClase = new JTextField();
		txtNombreClase.setBounds(461, 89, 151, 20);
		add(txtNombreClase);
		txtNombreClase.setColumns(10);
		
		txtMultiplicador = new JTextField();
		txtMultiplicador.setBounds(461, 122, 151, 20);
		add(txtMultiplicador);
		txtMultiplicador.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(523, 287, 89, 23);
		add(btnGuardar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(342, 186, 270, 90);
		add(scrollPane_1);
		
		JTextArea txtDescripcion = new JTextArea();
		scrollPane_1.setViewportView(txtDescripcion);
		
	}
}
