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
	
	private JDatePickerImpl dPInicio;	
	private JDatePickerImpl dPFin;	
	private JComboBox cbxOrigen;
	private JLabel lblLugarFin;
	private JComboBox cbxDestino;
	private JButton btnAgregar;
	private SpringLayout springLayout;
	private JTextField txtVendedor;
	private JTextField txtComprador;
	private JList listVentas;
	
	
	public PanelVendedores() {
		
		setLayout(null);
		setBackground( new Color(184, 207, 229) );
		dPInicio = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		springLayout = (SpringLayout) dPInicio.getLayout();
		springLayout.putConstraint(SpringLayout.NORTH, dPInicio.getJFormattedTextField(), 0, SpringLayout.NORTH, dPInicio);
		springLayout.putConstraint(SpringLayout.WEST, dPInicio.getJFormattedTextField(), 10, SpringLayout.WEST, dPInicio);
		
		dPInicio.setBounds(328, 81, 126, 23);
		add(dPInicio);
		
		JLabel lblFechaLimiteSuperior = new JLabel("Fecha de inico");
		lblFechaLimiteSuperior.setBounds(232, 81, 142, 24);
		add(lblFechaLimiteSuperior);
		
		JLabel lblFechaLimiteInferior = new JLabel("Fecha fin");
		lblFechaLimiteInferior.setBounds(232, 108, 142, 23);
		add(lblFechaLimiteInferior);
		
		dPFin = new JDatePickerImpl((new JDatePanelImpl(new UtilDateModel())));
		dPFin.getJFormattedTextField().setSize(117, 23);
		dPFin.getJFormattedTextField().setLocation(266, 0);
		dPFin.setBounds(328, 108, 126, 23);
		add(dPFin);
		
		ImageIcon image = new ImageIcon("./imagenes/delete.png");
		
		JLabel lblLugarInicio = new JLabel("Lugar de Origen");
		lblLugarInicio.setBounds(232, 144, 116, 14);
		add(lblLugarInicio);
		
		cbxOrigen = new JComboBox();
		cbxOrigen.setBounds(328, 142, 126, 22);
		add(cbxOrigen);
		
		lblLugarFin = new JLabel("Lugar de Destino");
		lblLugarFin.setBounds(232, 179, 116, 14);
		add(lblLugarFin);
		
		cbxDestino = new JComboBox();
		cbxDestino.setBounds(328, 175, 126, 22);
		add(cbxDestino);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(232, 219, 91, 23);
		add(btnAgregar);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setBounds(232, 33, 61, 14);
		add(lblVendedor);
		
		JLabel lblComprador = new JLabel("Comprador");
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
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(333, 219, 91, 23);
		add(btnModificar);
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
		return dPInicio;
	}

	public JDatePickerImpl getdPFin( )
	{
		return dPFin;
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
