package vista;

import java.awt.Color;
import javax.swing.JPanel;

import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.JTextField;


public class PanelVendedores extends JPanel
{	
	private static final long serialVersionUID = 1L;
	
	private JDatePickerImpl fecha;	
	private JComboBox cbxOrigen;
	private JLabel lblLugarFin;
	private JComboBox cbxDestino;
	private JButton btnAgregar;
	private SpringLayout springLayout;
	private JTextField txtVendedor;
	private JTextField txtComprador;
	private JList listVentas;
	private JLabel lblDescuento;
	private JComboBox cbxDescuento;
	private JLabel lblClase;
	private JLabel lblVendedor;
	private JLabel lblLugarInicio;
	private JLabel lblComprador;
	private JButton btnModificar;
	private JLabel lblFecha;
	private JComboBox cbxClase;
	
	
	public PanelVendedores() {
		
		setLayout(null);
		setBackground( new Color(184, 207, 229) );
		fecha = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		springLayout = (SpringLayout) fecha.getLayout();
		springLayout.putConstraint(SpringLayout.NORTH, fecha.getJFormattedTextField(), 0, SpringLayout.NORTH, fecha);
		springLayout.putConstraint(SpringLayout.WEST, fecha.getJFormattedTextField(), 10, SpringLayout.WEST, fecha);
		
		fecha.setBounds(328, 81, 126, 23);
		add(fecha);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(232, 81, 142, 24);
		add(lblFecha);
		
		ImageIcon image = new ImageIcon("./imagenes/delete.png");
		
		lblLugarInicio = new JLabel("Lugar de Origen");
		lblLugarInicio.setBounds(232, 179, 116, 14);
		add(lblLugarInicio);
		
		cbxOrigen = new JComboBox();
		cbxOrigen.setBounds(328, 175, 126, 22);
		add(cbxOrigen);
		
		lblLugarFin = new JLabel("Lugar de Destino");
		lblLugarFin.setBounds(232, 208, 116, 14);
		add(lblLugarFin);
		
		cbxDestino = new JComboBox();
		cbxDestino.setBounds(328, 204, 126, 22);
		add(cbxDestino);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(232, 254, 91, 23);
		add(btnAgregar);
		
		lblVendedor = new JLabel("Vendedor");
		lblVendedor.setBounds(232, 33, 61, 14);
		add(lblVendedor);
		
		lblComprador = new JLabel("Comprador");
		lblComprador.setBounds(232, 58, 61, 14);
		add(lblComprador);
		
		txtVendedor = new JTextField();
		txtVendedor.setBounds(328, 27, 126, 20);
		add(txtVendedor);
		txtVendedor.setColumns(10);
		
		txtComprador = new JTextField();
		txtComprador.setColumns(10);
		txtComprador.setBounds(328, 53, 126, 20);
		add(txtComprador);
		
		listVentas = new JList();
		listVentas.setBounds(10, 11, 202, 297);
		add(listVentas);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(328, 254, 91, 23);
		add(btnModificar);
		
		lblDescuento = new JLabel("Desucento Id");
		lblDescuento.setBounds(232, 119, 116, 14);
		add(lblDescuento);
		
		cbxDescuento = new JComboBox();
		cbxDescuento.setBounds(328, 115, 126, 22);
		add(cbxDescuento);
		
		lblClase = new JLabel("Clase Id");
		lblClase.setBounds(232, 150, 116, 14);
		add(lblClase);
		
		cbxClase = new JComboBox();
		cbxClase.setBounds(328, 146, 126, 22);
		add(cbxClase);
	}

	public JTextField getTxtComprador()
	{
		return txtComprador;
	}
	
	public JTextField getTxtVendedor()
	{
		return txtVendedor;
	}
	public JDatePickerImpl getdPInicio( )
	{
		return fecha;
	}


	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	
	public JComboBox getCbxOrigen() {
		return cbxOrigen;
	}

	public JComboBox getCbxDestino() {
		return cbxDestino;
	}
	public JList getListVentas() {
		return listVentas;
	}

	public void setlistVentas(JList listVentas) {
		this.listVentas = listVentas;
	}
}
