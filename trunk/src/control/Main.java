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
import modelo.Descuento;
import modelo.Destino;
import modelo.Tarifa;

import vista.DialogGenerarReporte;
import vista.PanelClases;
import vista.PanelDescuento;
import vista.PanelDestinos;
import vista.PanelTarifa;
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
		eventosPanelTarifa();

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
		ventana.actualizarListaDestinos(getDestinos());
		ventana.actualizarListaTarifas(getTarifas());
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


		//evento bnt Agregar
		panelDescuento.getBtnAgregar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{

				if( !panelDescuento.getList( ).isSelectionEmpty() )
				{
					Descuento d = (Descuento) panelDescuento.getList( ).getSelectedValue();
					try 
					{
						controladoraBD.actualizarDescuento(0, null, null, 0, 0, 0);
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
					String fechaInf = panelDescuento.getdPInicio().getJFormattedTextField().getText();
					String fechaSup = panelDescuento.getdPFin().getJFormattedTextField().getText();
					Integer ocupacionInf = (Integer) panelDescuento.getjSOcupacionInf( ).getModel( ).getValue();
					Integer ocupacionSup = (Integer) panelDescuento.getjSocupacionSup( ).getModel( ).getValue();
					Integer descuento = (Integer) panelDescuento.getsPPorcentage( ).getModel( ).getValue();

					try {
						controladoraBD.crearDescuento( fechaInf, fechaSup, ocupacionInf, ocupacionSup, descuento );
					} catch (ClassNotFoundException e1) 
					{
						e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}

					System.out.println(fechaInf);
					System.out.println(fechaSup);
					System.out.println(ocupacionInf);
					System.out.println(ocupacionSup);
					System.out.println(descuento); 
				}
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
					panelDestinos.getTxtId().setText( destino.getId()+"");
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
				int id = Integer.parseInt(panelDestinos.getTxtId().getText());
				String latitud = panelDestinos.getTxtLatitud().getText();

				String longitud = panelDestinos.getTxtLongitud().getText();
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
				int id = resultado.getInt(1);
				String descripcion = resultado.getString(2);
				String latitud = resultado.getString(3);
				String longitud = resultado.getString(4);

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

	private static void eventosPanelTarifa() 
	{
		final PanelTarifa panelTarifa = ventana.getPanelGerente().getPanelTarifa();

		final JList listaTarifas = panelTarifa.getListTarifas();
		listaTarifas.addListSelectionListener(new ListSelectionListener() 
		{
			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				if(!listaTarifas.isSelectionEmpty())
				{
					Tarifa tarifa = (Tarifa)listaTarifas.getSelectedValue();
					panelTarifa.getTxtValorKm().setText(tarifa.getValorKm()+"");
					panelTarifa.getTxtLimInfKm().setText(tarifa.getLimInfKm()+"");
					panelTarifa.getTxtLimSup().setText(tarifa.getLimSupKm()+"");					
				}
			}
		});

		panelTarifa.getBtnLimpiar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				panelTarifa.limpiarCampos();	
				listaTarifas.clearSelection();	
			}
		});

		panelTarifa.getBtnGuardarTarifa().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int id = Integer.parseInt(panelTarifa.getTxtId().getText());
				int valor = Integer.parseInt(panelTarifa.getTxtValorKm().getText());
				int inferior = Integer.parseInt(panelTarifa.getTxtLimInfKm().getText());
				int superior = Integer.parseInt(panelTarifa.getTxtLimSup().getText());

				try
				{
					if(listaTarifas.isSelectionEmpty())
					{
						controladoraBD.crearTarifa(id, valor, inferior, superior);
					}
					else
					{
						controladoraBD.actualizarTarifa(id, valor, inferior, superior);
					}
				}
				catch (ClassNotFoundException e1)
				{
					e1.printStackTrace();
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}

				actualizarPanelGerente();
			}
		});

		panelTarifa.getBtnEliminar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					int id = Integer.parseInt(panelTarifa.getTxtId().getText());
					controladoraBD.eliminarTarifa(id);
				}
				catch (ClassNotFoundException e1)
				{
					e1.printStackTrace();
				}
				catch (SQLException e2)
				{
					e2.printStackTrace();
				}
				actualizarPanelGerente();
			}
		});

	}
	
	public static ArrayList<Tarifa> getTarifas()
	{
		ArrayList<Tarifa> destinos = new ArrayList<Tarifa>();
		try 
		{
			ResultSet resultado = controladoraBD.consultarTarifas();
			while(resultado.next())
			{
				int id = resultado.getInt(1);
				int valor = resultado.getInt(2);
				int inferior = resultado.getInt(3);
				int superior = resultado.getInt(4);

				Tarifa tarifa = new Tarifa(id, valor, inferior, superior);
				destinos.add(tarifa);
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