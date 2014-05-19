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
import javax.swing.JTextArea;



public class PanelDemanda extends JPanel
{	
	private JDatePickerImpl dPInicio;	
	private JDatePickerImpl dPFin;	
	private JButton btnGenerar;	
	private JComboBox cbxOrigen;
	private JLabel lblLugarFin;
	private JComboBox cbxDestino;
	private JButton btnRefrescar;
	private JTextArea textArea;
	
	
	public PanelDemanda() {
		setLayout(null);
		setBackground( new Color(184, 207, 229) );
		dPInicio = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		dPInicio.setBounds(480, 74, 126, 23);
		add(dPInicio);
		
		JLabel lblFechaLimiteSuperior = new JLabel("Fecha de inico");
		lblFechaLimiteSuperior.setBounds(325, 74, 142, 24);
		add(lblFechaLimiteSuperior);
		
		JLabel lblFechaLimiteInferior = new JLabel("Fecha fin");
		lblFechaLimiteInferior.setBounds(325, 121, 142, 23);
		add(lblFechaLimiteInferior);
		
		dPFin = new JDatePickerImpl((new JDatePanelImpl(new UtilDateModel())));
		dPFin.getJFormattedTextField().setSize(117, 23);
		dPFin.getJFormattedTextField().setLocation(266, 0);
		dPFin.setBounds(480, 121, 126, 23);
		add(dPFin);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.setBounds(515, 285, 91, 23);
		add(btnGenerar);
		
		ImageIcon image = new ImageIcon("./imagenes/delete.png");
		
		JLabel lblLugarInicio = new JLabel("Lugar de Origen");
		lblLugarInicio.setBounds(325, 188, 116, 14);
		add(lblLugarInicio);
		
		cbxOrigen = new JComboBox();
		cbxOrigen.setBounds(480, 184, 126, 22);
		add(cbxOrigen);
		
		lblLugarFin = new JLabel("Lugar de Destino");
		lblLugarFin.setBounds(325, 217, 116, 14);
		add(lblLugarFin);
		
		cbxDestino = new JComboBox();
		cbxDestino.setBounds(480, 213, 126, 22);
		add(cbxDestino);
		
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setBounds(325, 285, 91, 23);
		add(btnRefrescar);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 74, 305, 236);
		add(textArea);
	}

	public JDatePickerImpl getdPInicio( )
	{
		return dPInicio;
	}

	public JDatePickerImpl getdPFin( )
	{
		return dPFin;
	}


	

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getBtnRefrescar() {
		return btnRefrescar;
	}

	public JButton getBtnGenerar( )
	{
		return btnGenerar;
	}
	
	public JComboBox getCbxOrigen() {
		return cbxOrigen;
	}

	public JComboBox getCbxDestino() {
		return cbxDestino;
	}
}
