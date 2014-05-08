package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Clase;
import modelo.Destino;

import vista.DialogGenerarReporte;
import vista.PanelClases;
import vista.PanelDescuento;
import vista.PanelDestinos;
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
		ventana.getPanelGerente( ).getBtnGenerarReporte( ).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				DialogGenerarReporte dialog = new DialogGenerarReporte( );
				dialog.setVisible(true);
			}
		});
		
		eventosPanelDescuentos();
		eventosPanelClases( );
		eventosPanelDestinos( );
	}

	private static void actualizarPanelGerente( )
	{
//		ventana.actualizarLista(getDestinos());
		try
		{
			ventana.actualizarPanelClases( consultarClases( ) );
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}
	
	private static void eventosPanelDescuentos( )
	{
		final PanelDescuento panelDescuento = ventana.getPanelGerente( ).getPanelDescuento( );
	
		//evento lista
		final JList listDescuentos = panelDescuento.getList( );
		listDescuentos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) 
			{
				if( !listDescuentos.isSelectionEmpty( ) )
				{
					//TODO
				}
			}
		});
		
		// evento bnt limpiar
		panelDescuento.getBtnLimpiar( ).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panelDescuento.getdPInicio( ).getJFormattedTextField( ).setText( "" );
				panelDescuento.getdPFin( ).getJFormattedTextField( ).setText( "" );
				panelDescuento.getjSOcupacionInf( ).getModel( ).setValue( 0 );
				panelDescuento.getjSocupacionSup( ).getModel( ).setValue( 0 );
				panelDescuento.getsPPorcentage( ).getModel( ).setValue( 0 );
				panelDescuento.getList( ).clearSelection( );
			}
		});	
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
					ventana.actualizarPanelClases( consultarClases( ) );
				}
				catch ( ClassNotFoundException e1 )
				{
					e1.printStackTrace();
				}
				catch ( SQLException e1 )
				{
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
						ventana.actualizarPanelClases( consultarClases( ) );
						panelClases.getListClases( ).clearSelection( );
					}
					catch ( ClassNotFoundException e1 )
					{
						e1.printStackTrace();
					}
					catch ( SQLException e1 )
					{
						e1.printStackTrace();
					}
				}
			}
		});	
		
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
	
	private static void eventosPanelDestinos() 
	{
		final PanelDestinos panelDestinos = ventana.getPanelGerente().getPanelDestinos();
		
		// Listener de la Lista destinos
		final JList listDestinos = panelDestinos.getListDestinos();
		listDestinos.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				if( !listDestinos.isSelectionEmpty( ) )
				{
					Destino destino = (Destino) listDestinos.getSelectedValue( );
					panelDestinos.getTxtId().setText( destino.getId());
					panelDestinos.getTxtLatitud().setText(destino.getLatitud()+"");
					panelDestinos.getTxtLongitud().setText(destino.getLongitud()+"");
					panelDestinos.getTextAreaDescripcion().setText(destino.getDescripcion());
					
					panelDestinos.deshabilitarBut();
					panelDestinos.deshabilitarCampos();
				}
			}
		});
		
		// evento para el boton editar
		panelDestinos.getBtnEditar().addActionListener(new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panelDestinos.habilitarCampos();
				panelDestinos.habilitarBut();
				panelDestinos.setCrear(false);
			}
		});
		
		//evento para el boton crear
		panelDestinos.getBtnCrear().addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panelDestinos.habilitarCampos();
				panelDestinos.limpiarCampos();
				panelDestinos.habilitarBut();
				panelDestinos.setCrear(true);
			}
		});
		
		//evetno para el boton listo
		panelDestinos.getBtnListo().addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String id = panelDestinos.getTxtId().getText();
				double latitud = Double.parseDouble(panelDestinos.getTxtLatitud().getText());
				double longitud = Double.parseDouble(panelDestinos.getTxtLongitud().getText());
				String descripcion = panelDestinos.getTextAreaDescripcion().getText();
				
				if(panelDestinos.getCrear())
				{
					try 
					{
						controladoraBD.crearDestino(id, latitud, longitud, descripcion);
					} 
					catch (ClassNotFoundException e1) 
					{
						e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
				else
				{
					try 
					{
						controladoraBD.actualizarDestino(id, latitud, longitud, descripcion);
					} 
					catch (ClassNotFoundException e1)
					{
						e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
				actualizarPanelGerente();
			}
		});
		
		//evento para el boton eliminar
		panelDestinos.getButEliminar().addActionListener(new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String id = panelDestinos.getTxtId().getText();
				if( !panelDestinos.getListDestinos().isSelectionEmpty( ) )
				{
					try 
					{
						controladoraBD.eliminarDestino(id);
					}
					catch (ClassNotFoundException e1) 
					{
						e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
				}
				actualizarPanelGerente();
			}
		});
	}
	
	public static ArrayList<Destino> getDestinos()
	{
		ArrayList<Destino> destinos = new ArrayList<Destino>();
		try 
		{
			ResultSet resultado = controladoraBD.consultarDestinos();
			while(resultado.next())
			{
				String id = resultado.getString(1);
				double latitud = resultado.getInt(2);
				double longitud = resultado.getInt(3);
				String descripcion = resultado.getString(4);
				
				Destino destino = new Destino(id, latitud, longitud, descripcion);
				destinos.add(destino);
			}
			
		} 
		catch (ClassNotFoundException e) 
		{
			
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
		
			e.printStackTrace();
		}
		return destinos;
	}

}
