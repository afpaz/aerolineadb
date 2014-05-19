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
	private JButton btnAgregar;
	private SpringLayout springLayout;
	private JTextField txtVendedor;
	private JTextField txtComprador;
	private JList listVentas;
	private JLabel lblViaje;
	private JComboBox cbxViajeId;
	private JLabel lblVendedor;
	private JLabel lblComprador;
	private JButton btnModificar;
	private JLabel lblFecha;
	private JButton btnLimpiar;
	private JLabel lblValor;
	private JTextField txtValor;
	
	public PanelVendedores() {
		
		setLayout(null);
		setBackground( new Color(184, 207, 229) );
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(232, 81, 142, 24);
		add(lblFecha);
		fecha = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		fecha.setBounds(328, 81, 126, 23);
		add(fecha);
		
		
		
		ImageIcon image = new ImageIcon("./imagenes/delete.png");
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(232, 189, 91, 23);
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
		btnModificar.setBounds(331, 189, 91, 23);
		add(btnModificar);
		
		lblViaje = new JLabel("Viaje Id");
		lblViaje.setBounds(232, 119, 116, 14);
		add(lblViaje);
		
		cbxViajeId = new JComboBox();
		cbxViajeId.setBounds(328, 115, 126, 22);
		add(cbxViajeId);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(430, 189, 91, 23);
		add(btnLimpiar);
		

		
		
		springLayout = (SpringLayout) fecha.getLayout();
		springLayout.putConstraint(SpringLayout.NORTH, fecha.getJFormattedTextField(), 0, SpringLayout.NORTH, fecha);
		springLayout.putConstraint(SpringLayout.WEST, fecha.getJFormattedTextField(), 10, SpringLayout.WEST, fecha);
		
		lblValor = new JLabel("Valor");
		lblValor.setBounds(232, 147, 61, 14);
		add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(328, 144, 126, 20);
		add(txtValor);
		
		
		
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
	
	public JButton getBtnModificar() {
		return btnModificar;
	}
	
	public JButton getBtnLimpiar() {
		return btnLimpiar;
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
	
	public void limpiarCampos()
	{
		txtComprador.setText("");
		txtVendedor.setText("");
		fecha.getJFormattedTextField( ).setText("");
		
	}
}
