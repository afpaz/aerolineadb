package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Clase;

import vista.PanelClases;
import vista.Ventana;

public class Main {

	public static Ventana ventana;
	public static ControladoraBD controladoraBD;
	
	public static void main(String[] args) {
		ventana = new Ventana();
		ventana.setVisible(true);
		controladoraBD = new ControladoraBD();
		
		eventosPanelConsultaViajes();
		eventosPanelGerente( );
		
		actualizarPanelGerente();
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
	
	private static void eventosPanelGerente()
	{
		eventosPanelClases( );
	}
	
	private static void actualizarPanelGerente( )
	{
		try
		{
			ventana.actualizarPanelClases( consultarClases( ) );
		}
		catch ( ClassNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( SQLException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void eventosPanelClases()
	{		
		final PanelClases panelClases = ventana.getPanelGerente( ).getPanelClases( );
		// evento lista
		final JList listClases = panelClases.getListClases( );
		listClases.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) 
			{
				if( !listClases.isSelectionEmpty( ) )
				{
					Clase c = (Clase) listClases.getSelectedValue( );
					panelClases.getTxtNombreClase().setText( c.getNombre( ) );
					panelClases.getTxtMultiplicador( ).setText( c.getMultiplicador( )+"" );
					panelClases.getTxtDescripcion( ).setText( c.getDescripcion( ) );
				}
			}
		});
		
		// evento btnlimpiar
		panelClases.getBtnLimpiar( ).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panelClases.getTxtNombreClase().setText( "" );
				panelClases.getTxtMultiplicador().setText( "" );
				panelClases.getTxtDescripcion().setText( "" );
				panelClases.getListClases( ).clearSelection( );
			}
		});	
		
		// evento btnguardar
		panelClases.getBtnGuardar( ).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String nom = panelClases.getTxtNombreClase().getText(  );
				String des = panelClases.getTxtDescripcion( ).getText(  );
				int mul = Integer.parseInt( panelClases.getTxtMultiplicador( ).getText( ) );
				
				try
				{
					if( panelClases.getListClases( ).isSelectionEmpty( ) )
						controladoraBD.crearClase( nom, des, mul );
					else
						controladoraBD.actualizarClase( ((Clase)listClases.getSelectedValue( )).getNombre( ) ,nom, des, mul );				
				}
				catch ( ClassNotFoundException e1 )
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch ( SQLException e1 )
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				panelClases.getTxtNombreClase().setText( "" );
				panelClases.getTxtMultiplicador().setText( "" );
				panelClases.getTxtDescripcion().setText( "" );
				panelClases.getListClases( ).clearSelection( );
			}
		});	
		
		// evento btn eliminar
		panelClases.getBtnEliminar( ).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if( !panelClases.getListClases( ).isSelectionEmpty( ) )
				{
					try
					{
						controladoraBD.eliminarClase( ((Clase)listClases.getSelectedValue( )).getNombre( ));
						panelClases.getListClases( ).clearSelection( );
					}
					catch ( ClassNotFoundException e1 )
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch ( SQLException e1 )
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});	
		
		//TODO actualizar lista con nuevos datos
	}
	
	public static Clase consultarClase(String nombre) throws SQLException, ClassNotFoundException
	{
		Clase clase = null;
		ResultSet resultSet = controladoraBD.consultarClase( nombre );
		if( resultSet.next( ) )
		{
			String nom = resultSet.getString( 1 );
			String des = resultSet.getString( 2 );
			int mul = resultSet.getInt( 3 );
			clase = new Clase( nom, des, mul );
		}
		resultSet.close( );
		return clase;
	}
	
	public static ArrayList< Clase > consultarClases() throws SQLException, ClassNotFoundException
	{
		ArrayList< Clase > clases = new ArrayList< Clase >( );
		ResultSet resultSet = controladoraBD.consultarClases( );
		while ( resultSet.next( ) )
		{
			String nom = resultSet.getString( 1 );
			String des = resultSet.getString( 2 );
			int mul = resultSet.getInt( 3 );
			Clase c = new Clase( nom, des, mul );
			clases.add( c );
		}
		return clases;
	}
}
