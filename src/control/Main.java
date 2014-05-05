package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import vista.Ventana;

public class Main {

	public static Ventana ventana;
	public static ControladoraBD controladoraBD;
	
	public static void main(String[] args) {
		ventana = new Ventana();
		ventana.setVisible(true);
		controladoraBD = new ControladoraBD();
		
		eventosPanelConsultaViajes();
	}

	private static void eventosPanelConsultaViajes() {
		
		ventana.getPanelConsultaViajes().getBuscar().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Date dateInicio, dateFin;
				try
				{
					dateInicio = (Date) ventana.getPanelConsultaViajes().getFechaInicio().getModel().getValue();
					dateFin = (Date) ventana.getPanelConsultaViajes().getFechaFin().getModel().getValue();
					ventana.getPanelConsultaViajes().setTable(new ResultSetTable(ControladoraBD.consultarVuelosEntreFechas(dateInicio, dateFin)));
					ventana.getPanelConsultaViajes().repaint();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Error"+System.getProperty("line.separator")+ex.getMessage());
					
				}
				
			}
			
			
		});
		
	}

}
