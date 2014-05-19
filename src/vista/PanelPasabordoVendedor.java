package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.PasabordoVendedor;
import modelo.Vuelo;
import control.ControladoraBD;

public class PanelPasabordoVendedor extends JPanel
{
	// Componentes UI
	JTable pasabordos;
	JComboBox vuelos;

	// ControladorBD
	ControladoraBD controladorBD;

	// Modelo JTable
	String[] columnas =
	{ "Nro. Tiquete", "Fecha", "Origen", "Destino", "Hora Salida", "Cliente" };;

	public PanelPasabordoVendedor()
	{
		controladorBD = new ControladoraBD();

		iniciarUI();
		eventos();

		// Crear panel superior
		JPanel panelSup = new JPanel();
		GridLayout layoutPanelSup = new GridLayout(1, 2);
		panelSup.setLayout(layoutPanelSup);
		JLabel etiqueta = new JLabel("Vuelo");
		panelSup.add(etiqueta);
		panelSup.add(vuelos);

		// Agregar elementos al panel principal
		GridLayout layout = new GridLayout(2, 1);
		setLayout(layout);
		add(panelSup);
		add(new JScrollPane(pasabordos));

	}

	/**
	 * Se inician los componentes de la UI
	 */
	public void iniciarUI()
	{
		DefaultTableModel model = new DefaultTableModel(columnas, 1);
		pasabordos = new JTable(model);

		vuelos = new JComboBox();
		llenarVuelos();
		
		Vuelo v = (Vuelo) vuelos.getSelectedItem();
		if(v!=null)
		{
			llenarPasabordos(v.getId());
		}

	}

	/**
	 * Se declaran los eventos de los componentes de la UI
	 */
	public void eventos()
	{
		vuelos.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Vuelo v = (Vuelo) vuelos.getSelectedItem();
				if(v!=null)
				{
					llenarPasabordos(v.getId());
				}
			}
		});

	}

	/**
	 * Permite llenar el combo box de vuelos
	 */
	public void llenarVuelos()
	{
		ResultSet resultado = null;
		try
		{
			vuelos.removeAllItems();
			resultado = controladorBD.consultarVuelos();

			while (resultado.next())
			{
				String id = resultado.getString("vuelo_id");
				Date fecha = resultado.getDate("fecha");
				int cupoMax = resultado.getInt("cupoMax");
				String origen = resultado.getString("origen");
				String destino = resultado.getString("destino");
				int cupoActual = resultado.getInt("cupo_actual");
				String hora = resultado.getString("hora");

				Vuelo v = new Vuelo(id, fecha, cupoMax, origen, destino, cupoActual, hora);
				vuelos.addItem(v);
			}

		} catch (ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(PanelPasabordoVendedor.this, "Error obteniendo vuelos");
		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(PanelPasabordoVendedor.this, "Error obteniendo vuelos");
		} finally
		{
			if (resultado != null)
			{
				try
				{
					resultado.close();
				} catch (SQLException e)
				{
					JOptionPane.showMessageDialog(PanelPasabordoVendedor.this, "Error cerrando la conexión");
				}
			}
		}
	}

	/**
	 * Permite llenar la tabla con los pasabordos asociados a un vuelo
	 * 
	 * @param idVuelo
	 *            Id del vuelo al cual se le van a generar los pasabordos
	 */
	public void llenarPasabordos(String idVuelo)
	{

		ResultSet resultado = null;

		try
		{
			resultado = controladorBD.consultarPasabordos(idVuelo);
			DefaultTableModel model = new DefaultTableModel(columnas, 0);

			while (resultado.next())
			{
				String tiqueteId = resultado.getString("tiquete_id");
				Date fecha = resultado.getDate("fecha");
				String origen = resultado.getString("origen");
				String destino = resultado.getString("destino");
				String hora = resultado.getString("hora");
				String comprador = resultado.getString("comprador");

				PasabordoVendedor p = new PasabordoVendedor(tiqueteId, fecha, origen, destino, hora, comprador);

				model.addRow(new Object[]
				{ p.getTiqueteId(), p.getFecha().toString(), p.getOrigen(), p.getDestino(), p.getHora(), p.getComprador() });
			}

			pasabordos.setModel(model);
			pasabordos.repaint();
		} catch (ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(PanelPasabordoVendedor.this, "Error obteniendo vuelos");
		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(PanelPasabordoVendedor.this, "Error obteniendo vuelos");
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
					JOptionPane.showMessageDialog(PanelPasabordoVendedor.this, "Error cerrando la conexión");
				}
			}
		}

	}

}
