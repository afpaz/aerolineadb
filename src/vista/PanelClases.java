package vista;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import modelo.Clase;

public class PanelClases extends JPanel 
{
	private JTextField txtNombreClase;
	private JTextField txtMultiplicador;
	private JTextArea txtDescripcion;
	private JButton btnGuardar;
	private JList listClases;
	private DefaultListModel listModel;
	private JButton btnLimpiar;
	private JButton btnEliminar;
	/**
	 * Create the panel.
	 */
	public PanelClases() 
	{
		setBackground( new Color(184, 207, 229) );
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 271, 298);
		add(scrollPane);
				
		listModel = new DefaultListModel();		
		listClases = new JList(listModel);
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
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(502, 287, 89, 23);
		add(btnGuardar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(342, 186, 270, 90);
		add(scrollPane_1);
		
		txtDescripcion = new JTextArea();
		scrollPane_1.setViewportView(txtDescripcion);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(362, 287, 89, 23);
		add(btnLimpiar);
		
		btnEliminar = new JButton( new ImageIcon( getClass().getResource("delete.png") ) );
		btnEliminar.setBounds(119, 320, 38, 23);
		add(btnEliminar);
		
	}
	public JButton getBtnGuardar( )
	{
		return btnGuardar;
	}
	public JList getListClases( )
	{
		return listClases;
	}
	public JTextField getTxtNombreClase( )
	{
		return txtNombreClase;
	}
	public JTextField getTxtMultiplicador( )
	{
		return txtMultiplicador;
	}
	public JTextArea getTxtDescripcion( )
	{
		return txtDescripcion;
	}
	public JButton getBtnLimpiar( )
	{
		return btnLimpiar;
	}
	public DefaultListModel getListModel( )
	{
		return listModel;
	}
	public JButton getBtnEliminar( )
	{
		return btnEliminar;
	}
	
}
