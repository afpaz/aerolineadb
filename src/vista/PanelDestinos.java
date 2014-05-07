package vista;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import modelo.Destino;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class PanelDestinos extends JPanel {
	private JTextField txtId;
	private JTextField txtLatitud;
	private JTextField txtLongitud;
	private JTextArea textAreaDescripcion;
	private JButton btnCrear;
	private JButton butEliminar;
	private JButton btnEditar;
	private JList listDestinos;
	private JButton btnListo;
	
	private boolean crear;
	public PanelDestinos()
	{
		crear = false;
		setLayout(null);
		setBackground(new Color(184, 207, 229));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 267, 317);
		add(scrollPane);
		
		listDestinos = new JList();
		scrollPane.setViewportView(listDestinos);
		
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
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(332, 305, 89, 23);
		add(btnEditar);
		
		btnCrear = new JButton("Crear");
		btnCrear.setBounds(427, 305, 89, 23);
		add(btnCrear);
		
		ImageIcon image = new ImageIcon(getClass().getResource("delete.png"));
		butEliminar = new JButton(image);
		butEliminar.setBounds(566, 305, 33, 23);
		add(butEliminar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(300, 213, 299, 77);
		add(scrollPane_1);
		
		textAreaDescripcion = new JTextArea();
		scrollPane_1.setViewportView(textAreaDescripcion);
		
		btnListo = new JButton("");
		btnListo.setBounds(526, 305, 33, 23);
		btnListo.setEnabled(false);
		add(btnListo);
		
		deshabilitarCampos();
	}
	public JTextField getTxtId() {
		return txtId;
	}
	public JTextField getTxtLatitud() {
		return txtLatitud;
	}
	public JTextField getTxtLongitud() {
		return txtLongitud;
	}
	public JTextArea getTextAreaDescripcion() {
		return textAreaDescripcion;
	}
	public JButton getBtnCrear() {
		return btnCrear;
	}
	public JButton getButEliminar() {
		return butEliminar;
	}
	public JButton getBtnEditar() {
		return btnEditar;
	}
	public JButton getBtnListo() {
		return btnListo;
	}
	public JList getListDestinos() {
		return listDestinos;
	}
	
	public void habilitarBut()
	{
		btnListo.setEnabled(true);
	}
	
	public void deshabilitarBut()
	{
		btnListo.setEnabled(false);
	}
	
	public void habilitarCampos()
	{
		txtId.setEditable(true);
		txtLatitud.setEditable(true);
		txtLongitud.setEditable(true);
		textAreaDescripcion.setEditable(true);
	}
	public void deshabilitarCampos()
	{
		txtId.setEditable(false);
		txtLatitud.setEditable(false);
		txtLongitud.setEditable(false);
		textAreaDescripcion.setEditable(false);
	}
	
	public void limpiarCampos() 
	{
		txtId.setText("");
		txtLatitud.setText("");
		txtLongitud.setText("");
		textAreaDescripcion.setText("");
	}
	
	public boolean getCrear()
	{
		return crear;
	}
	public void setCrear (boolean estado)
	{
		crear = estado;
	}

}
