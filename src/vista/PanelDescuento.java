package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class PanelDescuento extends JPanel{
	
	
	private Ventana interfaz;
	
	private JDatePickerImpl dPInicio;
	
	private JDatePickerImpl dPFin;
	
	
	
	public PanelDescuento() {
		setLayout(null);
		setBackground( new Color(184, 207, 229) );
		dPInicio = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		dPInicio.setBounds(481, 95, 126, 23);
		add(dPInicio);
		
		JLabel lblFechaLimiteSuperior = new JLabel("Fecha Limite Superior");
		lblFechaLimiteSuperior.setBounds(326, 95, 142, 24);
		add(lblFechaLimiteSuperior);
		
		JLabel lblFechaLimiteInferior = new JLabel("Fecha Limite inferior");
		lblFechaLimiteInferior.setBounds(326, 142, 142, 23);
		add(lblFechaLimiteInferior);
		
		dPFin = new JDatePickerImpl((new JDatePanelImpl(new UtilDateModel())));
		dPFin.getJFormattedTextField().setSize(117, 23);
		dPFin.getJFormattedTextField().setLocation(266, 0);
		dPFin.setBounds(481, 142, 126, 23);
		add(dPFin);
		
		JLabel lblLimiteSuperiorOcupacion = new JLabel("Limite Superior Ocupacion");
		lblLimiteSuperiorOcupacion.setBounds(326, 184, 155, 14);
		add(lblLimiteSuperiorOcupacion);
		
		JLabel lblLimiteInferiorOcupacion = new JLabel("Limite Inferior Ocupacion");
		lblLimiteInferiorOcupacion.setBounds(326, 219, 155, 14);
		add(lblLimiteInferiorOcupacion);
		
		JSpinner jSocupacionSup = new JSpinner();
		jSocupacionSup.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		jSocupacionSup.setBounds(552, 182, 55, 18);
		add(jSocupacionSup);
		
		JSpinner jSOcupacionInf = new JSpinner();
		jSOcupacionInf.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		jSOcupacionInf.setBounds(552, 217, 55, 18);
		add(jSOcupacionInf);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarDescuento();
			}
		});
		btnNewButton.setBounds(516, 306, 91, 23);
		add(btnNewButton);
		
		JSpinner sPPorcentage = new JSpinner();
		sPPorcentage.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		sPPorcentage.setBounds(552, 253, 55, 18);
		add(sPPorcentage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 269, 318);
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblPorcentaje = new JLabel("Porcentaje de descuento");
		lblPorcentaje.setBounds(326, 255, 142, 14);
		add(lblPorcentaje);
	}



	protected void AgregarDescuento() {
		// TODO Auto-generated method stub
		
	}
}
