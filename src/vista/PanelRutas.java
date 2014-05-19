package vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import modelo.Ruta;
import modelo.Tarifa;
import modelo.Viaje;
import control.ControladoraBD;

public class PanelRutas extends JPanel
{

	// Componentes UI
	private JComboBox comboViajeRuta;
	private JComboBox comboTarifaRuta;
	private JButton btnAgregar;
	private JList listaRutas;
	private JButton btnEliminar;

	// ControladorBD
	private ControladoraBD controladorBD;

	private ArrayList<Ruta> rutas = new ArrayList<Ruta>();

	public PanelRutas()
	{
		controladorBD = new ControladoraBD();

		iniciarComponentes();
		eventosComponentes();

		setBackground(new Color(184, 207, 229));
		setLayout(null);

		// Panel Izquierda
		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setBackground(new Color(184, 207, 229));
		TitledBorder titleBorder;
		titleBorder = BorderFactory.createTitledBorder("Creación de rutas");
		panelIzquierda.setBorder(titleBorder);

		GridLayout layoutPanelIzq = new GridLayout(3, 2);
		panelIzquierda.setLayout(layoutPanelIzq);

		JLabel lblViaje = new JLabel("Viaje:");
		panelIzquierda.add(lblViaje);

		panelIzquierda.add(comboViajeRuta);

		JLabel lblTarifa = new JLabel("Tarifa:");
		panelIzquierda.add(lblTarifa);

		panelIzquierda.add(comboTarifaRuta);

		panelIzquierda.add(btnAgregar);

		// Panel Derecha

		JPanel panelDerecha = new JPanel();
		panelDerecha.setBackground(new Color(184, 207, 229));

		TitledBorder titleBorder_2;
		titleBorder_2 = BorderFactory.createTitledBorder("Eliminación de rutas");
		panelDerecha.setBorder(titleBorder_2);

		JScrollPane scrollPane = new JScrollPane();
		panelDerecha.add(scrollPane);
		scrollPane.setViewportView(listaRutas);
		
		panelDerecha.add(btnEliminar);

		GridLayout layout = new GridLayout(1, 2);
		setLayout(layout);
		add(panelIzquierda);
		add(panelDerecha);

	}

	/**
	 * Se inician los componentes de la UI
	 */
	public void iniciarComponentes()
	{
		comboViajeRuta = new JComboBox();
		llenarComboBoxViajes();

		comboTarifaRuta = new JComboBox();
		llenarComboBoxTarifas();

		btnAgregar = new JButton("Crear");

		listaRutas = new JList();
		llenarListaRutas();

		btnEliminar = new JButton("Eliminar");

	}

	/**
	 * Se declaran los eventos de los componentes de la UI
	 */
	public void eventosComponentes()
	{

		//Evento boton agregar
		btnAgregar.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{

				if (comboTarifaRuta.getSelectedItem() != null && comboViajeRuta.getSelectedItem() != null)
				{

					Tarifa t = (Tarifa) comboTarifaRuta.getSelectedItem();
					Viaje v = (Viaje) comboViajeRuta.getSelectedItem();

					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = new Date();
					String fechaFormateada = dateFormat.format(date);
					try
					{
						controladorBD.crearRuta(fechaFormateada, v.getId(), t.getid());
						llenarListaRutas();

					} catch (ClassNotFoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(PanelRutas.this, "Error creando la ruta");

					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(PanelRutas.this, "Error creando la ruta");
					}

				} else
				{
					JOptionPane.showMessageDialog(PanelRutas.this, "Hay campos vacíos");
				}

			}
		});
		
		//Evento boton eliminar
		btnEliminar.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				if(listaRutas.getSelectedIndex()>=0)
				{
					Ruta r = rutas.get(listaRutas.getSelectedIndex());
					if(r!=null)
					{
						try
						{
							controladorBD.eliminarRuta(r.getId());
							rutas.remove(listaRutas.getSelectedIndex());
							listaRutas.setListData(rutas.toArray());
						} catch (ClassNotFoundException e)
						{
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(PanelRutas.this, "Error eliminando la ruta");
						} catch (SQLException e)
						{
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(PanelRutas.this, "Elimine los datos que dependan de esta ruta para poder eliminarla.");
						}

					}

				}

			}
		});

	}

	/**
	 * Permite llenar el combo box de tarifas
	 */
	public void llenarComboBoxTarifas()
	{
		ResultSet resultado = null;
		try
		{
			comboTarifaRuta.removeAllItems();

			resultado = controladorBD.consultarTarifas();
			while (resultado.next())
			{
				String idTarifa = resultado.getString("tarifa_id");
				int valorKm = resultado.getInt("vlr_km");
				double limInfKm = resultado.getDouble("liminfkm");
				double limSupKm = resultado.getDouble("limsupkm");

				Tarifa t = new Tarifa(idTarifa, valorKm, limInfKm, limSupKm);
				comboTarifaRuta.addItem(t);

			}

		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(PanelRutas.this, "Error obteniendo tarifas");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(PanelRutas.this, "Error obteniendo tarifas");
		} finally
		{
			if (resultado != null)
			{
				try
				{
					resultado.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(PanelRutas.this, "Error cerrando la conexión");
				}
			}
		}
	}

	/**
	 * Permite llenar el combo box de viajes
	 */
	public void llenarComboBoxViajes()
	{
		ResultSet resultado = null;
		try
		{
			comboViajeRuta.removeAllItems();

			resultado = controladorBD.consultarViajes();
			while (resultado.next())
			{
				String idViaje = resultado.getString("viaje_id");
				String sentido = resultado.getString("sentido");
				String clase_id = resultado.getString("clase_id");
				String descuento_id = resultado.getString("descuento_id");
				String origen = resultado.getString("origen");
				String destino = resultado.getString("destino");
				Date fecha = resultado.getDate("fecha");

				Viaje v = new Viaje(idViaje, sentido, clase_id, descuento_id, origen, destino, fecha);
				comboViajeRuta.addItem(v);

			}

		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(PanelRutas.this, "Error obteniendo viajes");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(PanelRutas.this, "Error obteniendo viajes");
		} finally
		{
			if (resultado != null)
			{
				try
				{
					resultado.close();
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(PanelRutas.this, "Error cerrando la conexión");
				}
			}
		}
	}

	public void llenarListaRutas()
	{
		ResultSet resultado = null;

		rutas.clear();

		try
		{
			resultado = controladorBD.consultarRutas();
			while (resultado.next())
			{
				int idRuta = resultado.getInt("ruta_id");
				Date fecha = resultado.getDate("fecha");
				String viajeId = resultado.getString("viaje_id");
				String tarifaId = resultado.getString("tarifa_id");

				Ruta r = new Ruta(idRuta, fecha, viajeId, tarifaId);
				rutas.add(r);
			}
			listaRutas.setListData(rutas.toArray());
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
