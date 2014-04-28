package vista;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class DialogGenerarReporte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTotalDinero;
	private JTextField txtTotalViajes;
	private JTextField txtTotalDescuentos;
	private ButtonGroup butGroup;
	
	private Ventana principal;
	private JRadioButton rdbtnCantidadDineroRecaudado;
	private JRadioButton rdbtnCantidadViajesVendidos;

	/**
	 * Create the dialog.
	 */
	public DialogGenerarReporte(Ventana ventana) {
		
		principal = ventana;
		setTitle("Generar Reporte");
		setBounds(100, 100, 554, 377);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblVendedores = new JLabel("Ranking Vendedores:");
			lblVendedores.setBounds(10, 11, 174, 14);
			contentPanel.add(lblVendedores);
		}
		{
			JList vendedoresList = new JList();
			vendedoresList.setBounds(10, 29, 228, 298);
			contentPanel.add(vendedoresList);
		}
		{
			rdbtnCantidadDineroRecaudado = new JRadioButton("Cantidad dinero recaudado", true);
			rdbtnCantidadDineroRecaudado.setBounds(262, 63, 241, 23);
			contentPanel.add(rdbtnCantidadDineroRecaudado);
		}
		{
			JLabel lblTotal = new JLabel("Total:");
			lblTotal.setBounds(322, 93, 54, 14);
			contentPanel.add(lblTotal);
		}
		{
			txtTotalDinero = new JTextField();
			txtTotalDinero.setEditable(false);
			txtTotalDinero.setBounds(365, 90, 158, 20);
			contentPanel.add(txtTotalDinero);
			txtTotalDinero.setColumns(10);
		}
		{
			rdbtnCantidadViajesVendidos = new JRadioButton("Cantidad Viajes Vendidos");
			rdbtnCantidadViajesVendidos.setBounds(262, 146, 241, 23);
			contentPanel.add(rdbtnCantidadViajesVendidos);
		}
		{
			butGroup = new ButtonGroup();
			butGroup.add(rdbtnCantidadDineroRecaudado);
			butGroup.add(rdbtnCantidadViajesVendidos);
		}
		{
			txtTotalViajes = new JTextField();
			txtTotalViajes.setEditable(false);
			txtTotalViajes.setBounds(365, 173, 158, 20);
			contentPanel.add(txtTotalViajes);
			txtTotalViajes.setColumns(10);
		}
		{
			JLabel lblTotal_1 = new JLabel("Total:");
			lblTotal_1.setBounds(322, 176, 54, 14);
			contentPanel.add(lblTotal_1);
		}
		{
			JLabel lblTotalDescuentos = new JLabel("Total Descuentos:");
			lblTotalDescuentos.setBounds(262, 264, 114, 14);
			contentPanel.add(lblTotalDescuentos);
		}
		{
			txtTotalDescuentos = new JTextField();
			txtTotalDescuentos.setEditable(false);
			txtTotalDescuentos.setBounds(365, 261, 158, 20);
			contentPanel.add(txtTotalDescuentos);
			txtTotalDescuentos.setColumns(10);
		}
	}

}
