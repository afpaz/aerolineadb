package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.omg.CORBA.Environment;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class PanelConsultaViajes extends JPanel implements ActionListener {
	private JDatePickerImpl inicio, fin;
	private final static String BUSCAR = "BUSCAR";
	public PanelConsultaViajes() {
		this.add(new JLabel("Favor ingresar fecha de inicio"));
		
		inicio = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		this.add(inicio);
		
		this.add(new JLabel("Favor ingresar fecha de fin"));
		
		fin = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
		this.add(fin);
		
		JButton button = new JButton("Buscar");
		button.setActionCommand(BUSCAR);
		button.addActionListener(this);
		this.add(button);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(BUSCAR)) {
			Date dateInicio, dateFin;
			try
			{
			dateInicio = (Date) inicio.getModel().getValue();
			dateFin = (Date) fin.getModel().getValue();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Error"+System.getProperty("line.separator")+ex.getMessage());
				
			}
			
			
		}
	}

}
