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
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;



public class PanelDemanda extends JPanel
{
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnGenerarReporte;
	
	
	public PanelDemanda() {
		setBackground( new Color(184, 207, 229) );
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.WEST);
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.EAST);
		
		panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);
		
		btnGenerarReporte = new JButton("Generar Reporte");
		panel_3.add(btnGenerarReporte);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel(new String[]{"Vuelo", "Fecha", "Origen", "Destino", "Tiquetes Vendidos"}, 0);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		ImageIcon image = new ImageIcon("./imagenes/delete.png");
	}

	public JTable getTable( )
	{
		return this.table;
	}
	
	public JButton getBtnGenerarReporte()
	{
		return this.btnGenerarReporte;
	}
	
	public void actualizarTabla(ResultSet rs)
	{
		DefaultTableModel model = new DefaultTableModel(new String[]{"Vuelo", "Fecha", "Origen", "Destino", "Tiquetes Vendidos"}, 0);
		try {
			while(rs.next())
			{
				model.addRow(new Object[] {rs.getString(1), rs.getDate(2).toString(), rs.getString(3), rs.getString(4), rs.getInt(5)});
			}
			this.table.setModel(model);
			this.table.repaint();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error");
			e.printStackTrace();
		}
	}
}
