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
import modelo.ReporteVentas;
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

	private static void eventosPanelReporteVendedor()
	{
		ventana.getPanelReporteVentas().getBtnGenerarReporte().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Entra al evento - Usuario: " +  ventana.getPanelReporteVentas().getIdVendedor().getText()  );

					ReporteVentas reporte;
					try
					{
						reporte = controladoraBD.reporteVendedor(ventana.getPanelReporteVentas().getIdVendedor().getText());
						ventana.getPanelReporteVentas().getLblValorTotalVendido().setText("$" + reporte.getValorTotal());
						ventana.getPanelReporteVentas().getLblNumeroTiquetes().setText("" + reporte.getCantidad());
						ventana.getPanelReporteVentas().actualizarRankingLugares(reporte.getRanking());
					}
					catch ( ClassNotFoundException e1 )
					{
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error"+System.getProperty("line.separator")+e1.getMessage());
					}
					catch ( SQLException e1 )
					{
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error"+System.getProperty("line.separator")+e1.getMessage());
					}				
			}
		});
	}
	
	private static void eventosPanelConsultaViajes() {

		ventana.getPanelConsultaViajes().getBuscar().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Entra al metodo desde el main");
				Date dateInicio, dateFin;
				try
				{
					dateInicio = (Date) ventana.getPanelConsultaViajes().getFechaInicio().getModel().getValue();
					dateFin = (Date) ventana.getPanelConsultaViajes().getFechaFin().getModel().getValue();
					ResultSet rs = controladoraBD.consultarVuelosEntreFechas(dateInicio, dateFin);

					ventana.getPanelConsultaViajes().actualizarTabla(rs);
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
				try
				{
					controladoraBD.generarTablaRankingDinero( );
					Object[] rank = controladoraBD.consultarCompactadoTablaRank( ).split( "," );
					String dineroTotal = controladoraBD.consultarDineroTotalRecaudado();
					DialogGenerarReporte dialog = new DialogGenerarReporte( rank );
					dialog.setVisible(true);
					eventosPanelReporte( dialog );
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
		});

		eventosPanelDescuentos();
		eventosPanelClases( );
		eventosPanelDestinos( );
	}

	private static void eventosPanelReporte( final DialogGenerarReporte dialog )
	{
		// evento boton cantidad de dinero
		dialog.getRdbtnCantidadDineroRecaudado( ).addActionListener( new ActionListener( )
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				try
				{
					controladoraBD.generarTablaRankingDinero( );
					Object[] rank = controladoraBD.consultarCompactadoTablaRank( ).split( "," );
					dialog.getList( ).setListData( rank );
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
		} );


		// evento boton cantidad tiquetes
		dialog.getRdbtnCantidadViajesVendidos( ).addActionListener( new ActionListener( )
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				try
				{
					controladoraBD.generarTablaRankingTiquetes( );
					Object[] rank = controladoraBD.consultarCompactadoTablaRank2( ).split( "," );
					dialog.getList( ).setListData( rank );
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
		} );
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
					Descuento d = ( Descuento ) listDescuentos.getSelectedValue( );
					panelDescuento.getId( ).setText( d.getId( ) );
					panelDescuento.getjSOcupacionInf( ).getModel( ).setValue( d.getOcupacionLimiteInferior( ) );
					panelDescuento.getjSocupacionSup( ).getModel( ).setValue( d.getOcupacionLimiteSuperior( ) );
					panelDescuento.getdPInicio( ).getJFormattedTextField( ).setText( d.getFechaLimiteInferior( ) );
					panelDescuento.getdPFin( ).getJFormattedTextField( ).setText( d.getFechaLimiteSuperior( ) );
					panelDescuento.getsPPorcentage( ).getModel( ).setValue( d.getPorcentajeDescuento( ) );
				}
			}
		});

		// evento bnt limpiar
		panelDescuento.getBtnLimpiar( ).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panelDescuento.getId( ).setText( "" );
				panelDescuento.getdPInicio( ).getJFormattedTextField( ).setText( "" );
				panelDescuento.getdPFin( ).getJFormattedTextField( ).setText( "" );
				panelDescuento.getjSOcupacionInf( ).getModel( ).setValue( 0 );
				panelDescuento.getjSocupacionSup( ).getModel( ).setValue( 0 );
				panelDescuento.getsPPorcentage( ).getModel( ).setValue( 0 );
				panelDescuento.getList( ).clearSelection( );
			}
		});	


		//evento bnt Guardar
		panelDescuento.getBtnAgregar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String id = panelDescuento.getId().getText();
				String fechaInf = panelDescuento.getdPInicio().getJFormattedTextField().getText();
				String fechaSup = panelDescuento.getdPFin().getJFormattedTextField().getText();
				Integer ocupacionInf = (Integer) panelDescuento.getjSOcupacionInf( ).getModel( ).getValue();
				Integer ocupacionSup = (Integer) panelDescuento.getjSocupacionSup( ).getModel( ).getValue();
				Integer descuento = (Integer) panelDescuento.getsPPorcentage( ).getModel( ).getValue();


				try 
				{
					if( !panelDescuento.getList( ).isSelectionEmpty() )
					{
						Descuento d = (Descuento) panelDescuento.getList( ).getSelectedValue();
						controladoraBD.actualizarDescuento( d.getId( ), id, fechaInf, fechaSup, ocupacionInf, ocupacionSup, descuento );
					}
					else
						controladoraBD.crearDescuento( id, fechaInf, fechaSup, ocupacionInf, ocupacionSup, descuento );			

					panelDescuento.getId( ).setText( "" );
					panelDescuento.getdPInicio( ).getJFormattedTextField( ).setText( "" );
					panelDescuento.getdPFin( ).getJFormattedTextField( ).setText( "" );
					panelDescuento.getjSOcupacionInf( ).getModel( ).setValue( 0 );
					panelDescuento.getjSocupacionSup( ).getModel( ).setValue( 0 );
					panelDescuento.getsPPorcentage( ).getModel( ).setValue( 0 );
					panelDescuento.getList( ).clearSelection( );
					ventana.actualizarListaDescuentos( consultarDescuentos( ) );
				} 
				catch (ClassNotFoundException e1) 
				{
					e1.printStackTrace();
				} 
				catch (SQLException e1) 
				{
					String[] err = e1.getMessage( ).split( "\n" );
					if( e1.getErrorCode( ) == 20000 || e1.getErrorCode( ) == 20001 )
						JOptionPane.showMessageDialog( null, err[0], "Error", JOptionPane.ERROR_MESSAGE );
					else
						e1.printStackTrace();
				}
			}
		});	

		//evento eliminar
		panelDescuento.getBtnEliminar( ).addActionListener( new ActionListener( )
		{			
			@Override
			public void actionPerformed( ActionEvent e )
			{
				if( !panelDescuento.getList( ).isSelectionEmpty( ) )
				{
					try
					{
						controladoraBD.eliminarDescuento( ((Descuento)panelDescuento.getList( ).getSelectedValue( )).getId( ) );
						ventana.actualizarListaDescuentos( consultarDescuentos( ) );
						panelDescuento.getList( ).clearSelection( );
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
		} );
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

				try
				{
					int mul = Integer.parseInt( panelClases.getTxtMultiplicador( ).getText( ) );

					if( panelClases.getListClases( ).isSelectionEmpty( ) )
						controladoraBD.crearClase( nom, des, mul );
					else
						controladoraBD.actualizarClase( ((Clase)listClases.getSelectedValue( )).getNombre( ) ,nom, des, mul+"" );
					ventana.actualizarPanelClases( consultarClases( ) );

					panelClases.getTxtNombreClase().setText( "" );
					panelClases.getTxtMultiplicador().setText( "" );
					panelClases.getTxtDescripcion().setText( "" );
					panelClases.getListClases( ).clearSelection( );
				}
				catch ( NumberFormatException e2 )
				{
					JOptionPane.showMessageDialog( null, "El porcentaje debe ser num��rico", "Error", JOptionPane.ERROR_MESSAGE );
					panelClases.getTxtMultiplicador().setText( "" );
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
				String id = panelDestinos.getTxtId().getText();
				double latitud=0;
				double longitud=0;
				String descripcion = panelDestinos.getTextAreaDescripcion().getText();

				//es una longitud y latitud correcta
				try
				{
					longitud = Double.parseDouble(panelDestinos.getTxtLongitud().getText());
					latitud = Double.parseDouble(panelDestinos.getTxtLatitud().getText());
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "La Latitud y/o longitud no tienen un formato correcto", "Error en el formato", JOptionPane.ERROR_MESSAGE);
					return;
				}


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
				panelDestinos.deshabilitarCampos();
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
						panelDestinos.limpiarCampos();
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
					panelTarifa.getTxtId().setText(tarifa.getid()+"");
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
				String id=panelTarifa.getTxtId().getText();
				int valor = 0;
				double inferior = 0;
				double superior = 0;

				//validacion para el valor
				try
				{
					valor = Integer.parseInt(panelTarifa.getTxtValorKm().getText());
				}
				catch(NumberFormatException ni)
				{
					JOptionPane.showMessageDialog(null, "El valor debe ser un numero", "Error en el formato", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// validacion inferior y superior
				try
				{
					inferior =  Double.parseDouble(panelTarifa.getTxtLimInfKm().getText());
					superior = Double.parseDouble(panelTarifa.getTxtLimSup().getText());
				}
				catch(NumberFormatException ni)
				{
					JOptionPane.showMessageDialog(null, "El Limite inferior y superior deben de ser un valor numerico", "Error en el formato", JOptionPane.ERROR_MESSAGE);
					return;
				}


				// agregar o actualizar
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
				String id = panelTarifa.getTxtId().getText();
				if( !panelTarifa.getListTarifas().isSelectionEmpty( ) )
				{
					try 
					{
						controladoraBD.eliminarTarifa(id);
						panelTarifa.limpiarCampos();
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
	private static void actualizarPanelGerente( )
	{
		ventana.actualizarListaDestinos(consultarDestinos());
		ventana.actualizarListaTarifas(consultarTarifas());
		ventana.actualizarPanelClases( consultarClases( ) );
		ventana.actualizarListaDescuentos(consultarDescuentos( ));
	}

	public static ArrayList<Tarifa> consultarTarifas()
	{
		ArrayList<Tarifa> tarifas = new ArrayList<Tarifa>();
		try 
		{
			ResultSet resultado = controladoraBD.consultarTarifas();
			while(resultado.next())
			{
				String id = resultado.getString(1);
				int valor = resultado.getInt(2);
				double inferior = resultado.getDouble(3);
				double superior = resultado.getDouble(4);

				Tarifa tarifa = new Tarifa(id, valor, inferior, superior);
				tarifas.add(tarifa);
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
		return tarifas;
	}

	public static ArrayList< Clase > consultarClases()
	{
		ArrayList< Clase > clases = new ArrayList< Clase >( );
		try
		{
			ResultSet resultSet = controladoraBD.consultarClases( );
			while ( resultSet.next( ) )
			{
				String nom = resultSet.getString( 1 );
				String des = resultSet.getString( 2 );
				int mul = resultSet.getInt( 3 );
				Clase c = new Clase( nom, des, mul );
				clases.add( c );
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
		return clases;
	}

	public static ArrayList<Destino> consultarDestinos()
	{
		ArrayList<Destino> destinos = new ArrayList<Destino>();
		try 
		{
			ResultSet resultado = controladoraBD.consultarDestinos();
			while(resultado.next())
			{
				String id = resultado.getString(1);
				String descripcion = resultado.getString(2);
				double latitud = resultado.getDouble(3);
				double longitud = resultado.getDouble(4);

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

	public static ArrayList<Descuento> consultarDescuentos()
	{
		ArrayList<Descuento> descuentos = new ArrayList<Descuento>();
		try 
		{
			ResultSet resultado = controladoraBD.consultarDescuentos();
			while(resultado.next())
			{
				String id = resultado.getString( 1 );
				int porcentajeDescuento = resultado.getInt( 2 );
				String f1[] = resultado.getString( 3 ).split( " " )[0].split( "-" );
				String fechaLimiteInferior = f1[2] + "/" + f1[1] + "/" + f1[0];
				String f2[] = resultado.getString( 4 ).split( " " )[0].split( "-" );
				String fechaLimiteSuperior = f2[2] + "/" + f2[1] + "/" + f2[0];
				int ocupacionLimiteInferior = resultado.getInt( 5 );
				int ocupacionLimiteSuperior = resultado.getInt( 6 );

				Descuento d = new Descuento( id, fechaLimiteInferior, fechaLimiteSuperior, ocupacionLimiteInferior, ocupacionLimiteSuperior, porcentajeDescuento );
				descuentos.add( d );
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
		return descuentos;
	}

	public static void main(String[] args) {
		ventana = new Ventana();
		ventana.setVisible(true);
		controladoraBD = new ControladoraBD();
		
		eventosPanelConsultaViajes();
		eventosPanelReporteVendedor();
		eventosPanelGerente( );
		eventosPanelTarifa();
		actualizarPanelGerente();
	}

}