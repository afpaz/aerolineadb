package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelConsultaViajes extends JPanel implements ActionListener {
	private JDatePickerImpl inicio, fin;
	private final static String BUSCAR = "BUSCAR";
	private JLabel label_2;
	private JTable table;
	private JButton buscar;
	public PanelConsultaViajes() {
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("31px"),
				ColumnSpec.decode("139px:grow"),
				ColumnSpec.decode("239px"),
				ColumnSpec.decode("73px"),},
			new RowSpec[] {
				RowSpec.decode("max(9dlu;default)"),
				RowSpec.decode("36px"),
				RowSpec.decode("max(24dlu;default)"),
				RowSpec.decode("46px"),
				RowSpec.decode("max(87dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		label_2 = new JLabel("");
		add(label_2, "1, 2, fill, fill");
		JLabel label_1 = new JLabel("Fecha de inicio");
		this.add(label_1, "2, 2, fill, fill");
		
		buscar = new JButton("Buscar");
		buscar.setActionCommand(BUSCAR);
		buscar.addActionListener(this);
		
		inicio = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		this.add(inicio, "3, 2, fill, fill");
		JLabel label = new JLabel("Fecha de fin");
		this.add(label, "2, 3, fill, fill");
		
		fin = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		this.add(fin, "3, 3, fill, fill");
		this.add(buscar, "2, 4, 2, 1, center, center");
		
		String[] nombres = {"Vuelo", "Fecha", "Origen", "Destino"};
		DefaultTableModel model = new DefaultTableModel(nombres, 1);
		table = new JTable(model);
		add(new JScrollPane(table), "2, 5, 2, 1, fill, fill");
		
		
	}
	
	public JButton getBuscar()
	{
		return this.buscar;
	}
	
	public JDatePickerImpl getFechaInicio()
	{
		return this.inicio;
	}
	
	public JDatePickerImpl getFechaFin()
	{
		return this.fin;
	}
	
	public void setTable (JTable table)
	{
		this.table = table;
	}
	
	public JTable getTable ()
	{
		return this.table;
	}
	
	
	public void actualizarTabla(ResultSet rs){
	
		String[] nombres = {"Vuelo", "Fecha", "Origen", "Destino"};
		DefaultTableModel model = new DefaultTableModel(nombres, 0);
		try {
			while(rs.next())
			{
				System.out.println("En el while");
				model.addRow(new Object[]{rs.getString(2), rs.getDate(1).toString(), rs.getString(3), rs.getString(4) });
				System.out.println("Origen" + rs.getString(3));
			
			}
			this.table.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		this.table.setModel(model);
		this.table.repaint();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getActionCommand().equals(BUSCAR)) {
//			Date dateInicio, dateFin;
//			try
//			{
//				System.out.println("Entra al evento");
//				dateInicio = (Date) inicio.getModel().getValue();
//				dateFin = (Date) fin.getModel().getValue();
//			}
//			catch(Exception ex)
//			{
//				JOptionPane.showMessageDialog(null, "Error"+System.getProperty("line.separator")+ex.getMessage());
//				
//			}
//			
//			
//		}
	}

}
