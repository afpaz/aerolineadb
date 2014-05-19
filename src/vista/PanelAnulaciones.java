package vista;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class PanelAnulaciones extends JPanel {
	private JTable table;
	private JTextField idVenta;
	private JButton btnAnular;

	/**
	 * Create the panel.
	 */
	public PanelAnulaciones() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JLabel lblIdDeLa = new JLabel("Id de la Venta");
		panel.add(lblIdDeLa);
		
		idVenta = new JTextField();
		panel.add(idVenta);
		idVenta.setColumns(10);
		
		btnAnular = new JButton("Anular");
		panel.add(btnAnular);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.WEST);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.EAST);

	}

	public JTable getTable() {
		return table;
	}

	public JTextField getIdVenta() {
		return idVenta;
	}

	public JButton getBtnAnular() {
		return btnAnular;
	}
	
	public void actualizarTabla(ResultSet rs)
	{
		DefaultTableModel model = new DefaultTableModel(new String[] {"Id", "Fecha", "Vendedor", "Comprador"}, 0);
		try {
			while(rs.next())
			{
				model.addRow(new Object[]{rs.getString(1), rs.getDate(2).toString(), rs.getString(3), rs.getString(4)});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
