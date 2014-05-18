package vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class DialogGenerarReporte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTotalDinero;
	private JTextField txtTotalViajes;
	private JTextField txtTotalDescuentos;
	private ButtonGroup butGroup;
	private JList list;
	
	private JRadioButton rdbtnCantidadDineroRecaudado;
	private JRadioButton rdbtnCantidadViajesVendidos;

	/**
	 * Create the dialog.
	 * @param rank 
	 */
	public DialogGenerarReporte(Object[ ] rank) 
	{
		setResizable(false);
		contentPanel.setBackground( new Color(184, 207, 229) );
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
			rdbtnCantidadDineroRecaudado = new JRadioButton("Cantidad dinero recaudado", true);
			rdbtnCantidadDineroRecaudado.setBackground( new Color(184, 207, 229) );
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
			rdbtnCantidadViajesVendidos.setBackground( new Color(184, 207, 229) );
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
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 36, 231, 292);
			contentPanel.add(scrollPane);
			{
				list = new JList( rank );
				scrollPane.setViewportView(list);
			}
		}
	}

	public JTextField getTxtTotalDinero( )
	{
		return txtTotalDinero;
	}

	public JTextField getTxtTotalViajes( )
	{
		return txtTotalViajes;
	}

	public JTextField getTxtTotalDescuentos( )
	{
		return txtTotalDescuentos;
	}

	public JRadioButton getRdbtnCantidadDineroRecaudado( )
	{
		return rdbtnCantidadDineroRecaudado;
	}

	public JRadioButton getRdbtnCantidadViajesVendidos( )
	{
		return rdbtnCantidadViajesVendidos;
	}

	public JList getList( )
	{
		return list;
	}

	public ButtonGroup getButGroup( )
	{
		return butGroup;
	}
	
}
