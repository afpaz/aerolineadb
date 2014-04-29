package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class PanelDescuento extends JPanel{
	
	
	private Ventana interfaz;
	
	private JDatePickerImpl dPInicio;
	
	private JDatePickerImpl dPFin;
	
	
	
	public PanelDescuento() {
		setBorder(new TitledBorder(null, "Descuentos", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		setLayout(null);
		setBackground( new Color(184, 207, 229) );
		dPInicio = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		dPInicio.setBounds(410, 114, 126, 23);
		add(dPInicio);
		
		JLabel lblFechaLimiteSuperior = new JLabel("Fecha Limite Superior");
		lblFechaLimiteSuperior.setBounds(283, 114, 117, 14);
		add(lblFechaLimiteSuperior);
		
		JLabel lblFechaLimiteInferior = new JLabel("Fecha Limite inferior");
		lblFechaLimiteInferior.setBounds(283, 160, 117, 14);
		add(lblFechaLimiteInferior);
		
		dPFin = new JDatePickerImpl((new JDatePanelImpl(new UtilDateModel())));
		dPFin.getJFormattedTextField().setSize(117, 23);
		dPFin.getJFormattedTextField().setLocation(266, 0);
		dPFin.setBounds(410, 161, 126, 23);
		add(dPFin);
		
		JLabel lblLimiteSuperiorOcupacion = new JLabel("Limite Superior Ocupacion");
		lblLimiteSuperiorOcupacion.setBounds(283, 202, 130, 14);
		add(lblLimiteSuperiorOcupacion);
		
		JLabel lblLimiteInferiorOcupacion = new JLabel("Limite Inferior Ocupacion");
		lblLimiteInferiorOcupacion.setBounds(283, 237, 130, 14);
		add(lblLimiteInferiorOcupacion);
		
		JSpinner jSocupacionSup = new JSpinner();
		jSocupacionSup.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		jSocupacionSup.setBounds(481, 201, 55, 18);
		add(jSocupacionSup);
		
		JSpinner jSOcupacionInf = new JSpinner();
		jSOcupacionInf.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		jSOcupacionInf.setBounds(481, 236, 55, 18);
		add(jSOcupacionInf);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarDescuento();
			}
		});
		btnNewButton.setBounds(445, 301, 91, 23);
		add(btnNewButton);
		
		JList list = new JList();
		list.setBounds(10, 39, 214, 285);
		add(list);
		
		JSpinner sPPorcentage = new JSpinner();
		sPPorcentage.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		sPPorcentage.setBounds(283, 272, 55, 18);
		add(sPPorcentage);
	}



	protected void AgregarDescuento() {
		// TODO Auto-generated method stub
		
	}
}
