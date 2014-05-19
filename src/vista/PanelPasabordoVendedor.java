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

import modelo.Vuelo;
import control.ControladoraBD;

public class PanelPasabordoVendedor extends JPanel
{
	// Componentes UI
	JTable pasabordos;
	JComboBox vuelos;

	// ControladorBD
	ControladoraBD controladorBD;

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
		String[] columnas =
		{ "Nro. Tiquete", "Fecha", "Origen", "Destino", "Hora Salida", "Cliente" };
		DefaultTableModel model = new DefaultTableModel(columnas, 1);
		pasabordos = new JTable(model);

		vuelos = new JComboBox();
		llenarVuelos();
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
				System.out.println(vuelos.getSelectedItem().toString());
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

				Vuelo v = new Vuelo(id, fecha, cupoMax, origen, destino, cupoActual);
				vuelos.addItem(v);
			}

		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(PanelPasabordoVendedor.this, "Error obteniendo vuelos");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
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

	/**
	 * Permite llenar la tabla con los pasabordos asociados a un vuelo
	 * 
	 * @param idVuelo
	 *            Id del vuelo al cual se le van a generar los pasabordos
	 */
	public void llenarPasabordos(String idVuelo)
	{
/*		String[] nombres =
		{ "Vuelo", "Fecha", "Origen", "Destino" };
		DefaultTableModel model = new DefaultTableModel(nombres, 0);
		try
		{
			while (rs.next())
			{
				System.out.println("En el while");
				model.addRow(new Object[]
				{ rs.getString(2), rs.getDate(1).toString(), rs.getString(3), rs.getString(4) });
				System.out.println("Origen" + rs.getString(3));

			}
			this.table.setModel(model);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		this.table.repaint();*/
	}

}
