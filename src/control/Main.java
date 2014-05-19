package control;

import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import modelo.Venta;
import vista.DialogGenerarReporte;
import vista.PanelClases;
import vista.PanelDemanda;
import vista.PanelDescuento;
import vista.PanelDestinos;
import vista.PanelTarifa;
import vista.PanelVendedores;

import vista.Ventana;

public class Main {

	public static Ventana ventana;
	public static ControladoraBD controladoraBD;

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

	private static void eventosPanelReporteVentas()
	{
		ventana.getPanelReporteVentas().getBtnGenerarReporte().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> lista = new ArrayList<String>();
				try {
					ReporteVentas reporte = controladoraBD.reporteVendedor(ventana.getPanelReporteVentas().getIdVendedor().getText());
					ventana.getPanelReporteVentas().actualizarRankingLugares(reporte.getRanking());
					ventana.getPanelReporteVentas().getLblNumeroTiquetes().setText("" + reporte.getCantidad());
					ventana.getPanelReporteVentas().getLblValorTotalVendido().setText("" + reporte.getValorTotal());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	private static void eventosPanelAnulaciones()
	{

			try {
				ventana.getPanelAnulaciones().actualizarTabla(controladoraBD.consultarVentas());
				ventana.getPanelAnulaciones().getBtnAnular().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							controladoraBD.anularVenta(ventana.getPanelAnulaciones().getIdVenta().getText());
							JOptionPane.showMessageDialog(ventana, "Se ha anulado exitosamente la venta " + ventana.getPanelAnulaciones().getIdVenta().getText());
							ventana.getPanelAnulaciones().getIdVenta().setText("");
							ventana.getPanelAnulaciones().actualizarTabla(controladoraBD.consultarVentas());
						} catch (ClassNotFoundException e1) {
							JOptionPane.showMessageDialog(ventana, "Ha ocurrido un error al intentar eliminar la venta " + ventana.getPanelAnulaciones().getIdVenta().getText());
							e1.printStackTrace();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(ventana, "Ha ocurrido un error al intentar eliminar la venta " + ventana.getPanelAnulaciones().getIdVenta().getText());
							e1.printStackTrace();
						}
					}
				});
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(ventana, "Ha ocurrido un error al intentar eliminar la venta " + ventana.getPanelAnulaciones().getIdVenta().getText());
				e.printStackTrace();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(ventana, "Ha ocurrido un error al intentar eliminar la venta " + ventana.getPanelAnulaciones().getIdVenta().getText());
				e.printStackTrace();
			}

	}
	private static void eventosPanelGerente()
	{
		ventana.getPanelGerente( ).getBtnGenerarReporte( ).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					controladoraBD.generarTablaRankingDinero( );

					String r = controladoraBD.consultarCompactadoTablaRank( );
					double d = controladoraBD.consultarDineroTotalRecaudado( );
					int t = controladoraBD.consultarTotalTiquetes( );
					Object[] rank = null;
					if( r != null )
						rank = r.split( "," );
					else
						rank = new Object[]{};
					DialogGenerarReporte dialog = new DialogGenerarReporte( rank, d, t );
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
					panelDescuento.getId( ).setEditable( false );
				}
			}
		});

		// evento bnt limpiar
		panelDescuento.getBtnLimpiar( ).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				panelDescuento.getId( ).setEditable( true );
				panelDescuento.getId( ).setText( "" );
				panelDescuento.getdPInicio( ).getJFormattedTextField( ).setText( "" );
				panelDescuento.getdPFin( ).getJFormattedTextField( ).setText( "" );
				panelDescuento.getjSOcupacionInf( ).getModel( ).setValue( 0 );
				panelDescuento.getjSocupacionSup( ).getModel( ).setValue( 0 );
				panelDescuento.getsPPorcentage( ).getModel( ).setValue( 1 );
				panelDescuento.getList( ).clearSelection( );
			}
		});	


		//evento bnt Guardar
		panelDescuento.getBtnAgregar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String id = panelDescuento.getId().getText();
				String[] fi = panelDescuento.getdPInicio().getJFormattedTextField().getText().split( "/" );
				String[] fs = panelDescuento.getdPFin().getJFormattedTextField().getText().split( "/" );
				
				int[] fechaInf = new int[]{ Integer.parseInt( fi[0] ), Integer.parseInt( fi[1] )-1, Integer.parseInt( fi[2] )-1900 };
				int[] fechaSup = new int[]{ Integer.parseInt( fs[0] ), Integer.parseInt( fs[1] )-1, Integer.parseInt( fs[2] )-1900 };
				Integer ocupacionInf = (Integer) panelDescuento.getjSOcupacionInf( ).getModel( ).getValue();
				Integer ocupacionSup = (Integer) panelDescuento.getjSocupacionSup( ).getModel( ).getValue();
				Integer descuento = (Integer) panelDescuento.getsPPorcentage( ).getModel( ).getValue();
				String usu = JOptionPane.showInputDialog( "Ingrese su usuario" );

				try 
				{
					if( !panelDescuento.getList( ).isSelectionEmpty() )
					{
						Descuento d = (Descuento) panelDescuento.getList( ).getSelectedValue();
						controladoraBD.actualizarDescuento( usu, d.getId( ), id, fechaInf, fechaSup, ocupacionInf, ocupacionSup, descuento );
					}
					else
						controladoraBD.crearDescuento( usu, id, fechaInf, fechaSup, ocupacionInf, ocupacionSup, descuento );			

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
					if( e1.getErrorCode( ) == 20000 || e1.getErrorCode( ) == 20001 || e1.getErrorCode( ) == 20004 )
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
					String usu = JOptionPane.showInputDialog( "Ingrese su usuario" );
					try
					{
						controladoraBD.eliminarDescuento( usu, ((Descuento)panelDescuento.getList( ).getSelectedValue( )).getId( ) );
						ventana.actualizarListaDescuentos( consultarDescuentos( ) );
						panelDescuento.getList( ).clearSelection( );
					}
					catch ( ClassNotFoundException e1 )
					{
						e1.printStackTrace();
					}
					catch (SQLException e1) 
					{
						String[] err = e1.getMessage( ).split( "\n" );
						if( e1.getErrorCode( ) == 20004 )
							JOptionPane.showMessageDialog( null, err[0], "Error", JOptionPane.ERROR_MESSAGE );
						else if( e1.getErrorCode( ) == 2292 )
							JOptionPane.showMessageDialog( null, "No puedes eliminar este Descuento tiene datos asociados a otras entidades", "Error", JOptionPane.ERROR_MESSAGE );
						else
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
					panelClases.getTxtNombreClase().setEditable( false );
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
				panelClases.getTxtNombreClase().setEditable( true );
			}
		});	

		// evento btnguardar
		panelClases.getBtnGuardar( ).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String nom = panelClases.getTxtNombreClase().getText(  );
				String des = panelClases.getTxtDescripcion( ).getText(  );
				String usu = JOptionPane.showInputDialog( "Ingrese su usuario" );
				try
				{
					int mul = Integer.parseInt( panelClases.getTxtMultiplicador( ).getText( ) );

					if( panelClases.getListClases( ).isSelectionEmpty( ) )
						controladoraBD.crearClase( usu, nom, des, mul );
					else
						controladoraBD.actualizarClase( usu, ((Clase)listClases.getSelectedValue( )).getNombre( ) ,nom, des, mul+"" );
					ventana.actualizarPanelClases( consultarClases( ) );

					panelClases.getTxtNombreClase().setText( "" );
					panelClases.getTxtMultiplicador().setText( "" );
					panelClases.getTxtDescripcion().setText( "" );
					panelClases.getListClases( ).clearSelection( );
				}
				catch ( NumberFormatException e2 )
				{
					JOptionPane.showMessageDialog( null, "El porcentaje debe ser numrico", "Error", JOptionPane.ERROR_MESSAGE );
					panelClases.getTxtMultiplicador().setText( "" );
				}
				catch ( ClassNotFoundException e1 )
				{
					e1.printStackTrace();
				}
				catch (SQLException e1) 
				{
					String[] err = e1.getMessage( ).split( "\n" );
					if( e1.getErrorCode( ) == 20004 )
						JOptionPane.showMessageDialog( null, err[0], "Error", JOptionPane.ERROR_MESSAGE );
					else
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
					String usu = JOptionPane.showInputDialog( "Ingrese su usuario" );
					try
					{
						controladoraBD.eliminarClase( usu, ((Clase)listClases.getSelectedValue( )).getNombre( ));
						ventana.actualizarPanelClases( consultarClases( ) );
						panelClases.getListClases( ).clearSelection( );
					}
					catch ( ClassNotFoundException e1 )
					{
						e1.printStackTrace();
					}
					catch (SQLException e1) 
					{
						String[] err = e1.getMessage( ).split( "\n" );
						if( e1.getErrorCode( ) == 20004 )
							JOptionPane.showMessageDialog( null, err[0], "Error", JOptionPane.ERROR_MESSAGE );
						else if( e1.getErrorCode( ) == 2292 )
							JOptionPane.showMessageDialog( null, "No puedes eliminar esta clase tiene datos asociados a otras entidades", "Error", JOptionPane.ERROR_MESSAGE );
						else
							e1.printStackTrace();
					}
				}
			}
		});	

	}

	private static void eventosPanelVendedores() 
	{
		final PanelVendedores panelV = ventana.getPanelVendedores();

//		 Listener de la Lista vendedores
		final JList listVendedores = panelV.getListVentas();
		listVendedores.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				if( !listVendedores.isSelectionEmpty( ) )
				{
					Venta venta = (Venta) listVendedores.getSelectedValue( );
					panelV.getTxtComprador().setText(venta.getComprador()+"");
					panelV.getTxtVendedor().setText(venta.getVendedor()+"");
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
				panelDestinos.getTxtId().setEditable( false );
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
				String usu = JOptionPane.showInputDialog( "Ingrese su usuario" );
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
						controladoraBD.crearDestino(usu, id, latitud, longitud, descripcion);
					} 
					catch (ClassNotFoundException e1) 
					{
						e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						String[] err = e1.getMessage( ).split( "\n" );
						if( e1.getErrorCode( ) == 20004 )
							JOptionPane.showMessageDialog( null, err[0], "Error", JOptionPane.ERROR_MESSAGE );
						else
							e1.printStackTrace();
					}
				}
				else
				{
					try 
					{
						Destino d = (Destino)panelDestinos.getListDestinos( ).getSelectedValue( );
						controladoraBD.actualizarDestino(usu, id, latitud, longitud, descripcion, d.getId( ));
					} 
					catch (ClassNotFoundException e1)
					{
						e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						String[] err = e1.getMessage( ).split( "\n" );
						if( e1.getErrorCode( ) == 20004 )
							JOptionPane.showMessageDialog( null, err[0], "Error", JOptionPane.ERROR_MESSAGE );
						else
							e1.printStackTrace();
					}
				}
				ventana.actualizarListaDestinos( consultarDestinos( ) );
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
					String usu = JOptionPane.showInputDialog( "Ingrese su usuario" );
					try 
					{
						controladoraBD.eliminarDestino(usu, id);
						panelDestinos.limpiarCampos();
					}
					catch (ClassNotFoundException e1) 
					{
						e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						String[] err = e1.getMessage( ).split( "\n" );
						if( e1.getErrorCode( ) == 20004 )
							JOptionPane.showMessageDialog( null, err[0], "Error", JOptionPane.ERROR_MESSAGE );
						else if( e1.getErrorCode( ) == 2292 )
							JOptionPane.showMessageDialog( null, "No puedes eliminar este destino tiene datos asociados a otras entidades", "Error", JOptionPane.ERROR_MESSAGE );
						else
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
					panelTarifa.getTxtId().setEditable( false );
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
				panelTarifa.getTxtId().setEditable( true );
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
					String usu = JOptionPane.showInputDialog( "Ingrese su usuario" );
					if(listaTarifas.isSelectionEmpty())
					{
						controladoraBD.crearTarifa(usu, id, valor, inferior, superior);
					}
					else
					{
						controladoraBD.actualizarTarifa(usu, id, valor, inferior, superior);
					}
					ventana.actualizarListaTarifas( consultarTarifas( ) );
				}
				catch (ClassNotFoundException e1)
				{
					e1.printStackTrace();
				}
				catch (SQLException e1) 
				{
					String[] err = e1.getMessage( ).split( "\n" );
					if( e1.getErrorCode( ) == 20002 || e1.getErrorCode( ) == 20004 )
						JOptionPane.showMessageDialog( null, err[0], "Error", JOptionPane.ERROR_MESSAGE );
					else
						e1.printStackTrace();
				}
			}
		});

		panelTarifa.getBtnEliminar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				String id = panelTarifa.getTxtId().getText();
				if( !panelTarifa.getListTarifas().isSelectionEmpty( ) )
				{
					String usu = JOptionPane.showInputDialog( "Ingrese su usuario" );
					try 
					{
						controladoraBD.eliminarTarifa(usu,id);
						panelTarifa.limpiarCampos();
					}
					catch (ClassNotFoundException e1) 
					{
						e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						String[] err = e1.getMessage( ).split( "\n" );
						if( e1.getErrorCode( ) == 20004 )
							JOptionPane.showMessageDialog( null, err[0], "Error", JOptionPane.ERROR_MESSAGE );
						else if( e1.getErrorCode( ) == 2292 )
							JOptionPane.showMessageDialog( null, "No puedes eliminar esta tarifa tiene datos asociados a otras entidades", "Error", JOptionPane.ERROR_MESSAGE );
						else
							e1.printStackTrace();
					}
				}
				ventana.actualizarListaTarifas( consultarTarifas( ) );
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
	
	public static ArrayList< Venta > consultarVentas()
	{
		ArrayList< Venta > ventas = new ArrayList< Venta >( );
		try
		{
			ResultSet resultSet = controladoraBD.consultarVentas( );
			while ( resultSet.next( ) )
			{
				String ventaId = resultSet.getString( 1 );
				
				String f1[] = resultSet.getString( 3 ).split( " " )[0].split( "-" );
				String fecha = f1[2] + "/" + f1[1] + "/" + f1[0];
				String vendedor = resultSet.getString( 3 );
				String comprador = resultSet.getString( 3 );
				Venta v = new Venta( ventaId, fecha, vendedor, comprador );
				ventas.add( v );
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
		return ventas;
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
		eventosPanelGerente( );
		eventosPanelTarifa();
		eventosPanelVendedores();
		eventosPanelDemanda();
		eventosPanelReporteVentas();
		eventosPanelAnulaciones();
		actualizarPanelGerente();
	}


	private static void actualizarPanelDemanda() {
		ventana.actualizarListaDestinos(consultarDestinos());
		ventana.getPanelDemanda().getTextArea().setText("");
	}

	private static void eventosPanelDemanda() {
		final PanelDemanda panelDemanda = ventana.getPanelDemanda();
		
		panelDemanda.getBtnGenerar().addActionListener(new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				Date inicio = (Date) panelDemanda.getdPInicio( ).getModel().getValue();
				
				Date fin = (Date) panelDemanda.getdPFin( ).getModel().getValue();
				
				
				if(inicio != null && fin != null && inicio.compareTo(fin)>=0)
				{
					JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser anterior a la fecha fin", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				try {
					ResultSet resultado = controladoraBD.ConsultarDemanda(inicio, fin, 
							((panelDemanda.getCbxOrigen().getSelectedIndex()< 0)? null: ((Destino) panelDemanda.getCbxOrigen().getSelectedItem())),
							((panelDemanda.getCbxDestino().getSelectedIndex()< 0)? null: ((Destino) panelDemanda.getCbxDestino().getSelectedItem())));
				
					
					
					
					StringBuilder sb = new StringBuilder();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					while(resultado.next())
					{
						sb.append(sdf.format(resultado.getObject(1)));
						sb.append(' ');
						sb.append(resultado.getString(2));
						sb.append(System.lineSeparator());
					}
					panelDemanda.getTextArea().setText(sb.toString());
					
				} 
				catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				catch ( SQLException e1 )
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		panelDemanda.getBtnRefrescar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actualizarPanelDemanda();
			}
		});
	}

}