package vista;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCiudades extends JPanel {
	private JTextField txtId;
	private JTextField txtLatitud;
	private JTextField txtLongitud;
	private JTextArea textAreaDescripcion;
	public PanelCiudades()
	{
		setLayout(null);
		setBackground(new Color(184, 207, 229));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 267, 317);
		add(scrollPane);
		
		JList listCiudades = new JList();
		scrollPane.setViewportView(listCiudades);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(300, 83, 46, 14);
		add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(441, 77, 158, 20);
		add(txtId);
		txtId.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(300, 188, 108, 14);
		add(lblDescripcin);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBounds(300, 213, 299, 77);
		add(textAreaDescripcion);
		
		JLabel lblLatitud = new JLabel("Latitud:");
		lblLatitud.setBounds(300, 118, 89, 14);
		add(lblLatitud);
		
		txtLatitud = new JTextField();
		txtLatitud.setBounds(441, 112, 158, 20);
		add(txtLatitud);
		txtLatitud.setColumns(10);
		
		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setBounds(300, 149, 89, 14);
		add(lblLongitud);
		
		txtLongitud = new JTextField();
		txtLongitud.setBounds(441, 143, 158, 20);
		add(txtLongitud);
		txtLongitud.setColumns(10);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(372, 305, 89, 23);
		add(btnEditar);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				limpiarCampos();
			}

			private void limpiarCampos() 
			{
				txtId.setText("");
				txtLatitud.setText("");
				txtLongitud.setText("");
				textAreaDescripcion.setText("");
			}
		});
		btnCrear.setBounds(467, 305, 89, 23);
		add(btnCrear);
		
		ImageIcon image = new ImageIcon(getClass().getResource("delete.png"));
		JButton butEliminar = new JButton(image);
		butEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		butEliminar.setBounds(566, 305, 33, 23);
		add(butEliminar);
	}
}
