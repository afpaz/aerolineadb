package vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import modelo.Tarifa;
import control.ControladoraBD;

public class PanelRutas extends JPanel
{

	// Componentes UI
	private JComboBox comboViajeRuta;
	private JComboBox comboTarifaRuta;
	private JButton btnAgregar;

	// ControladorBD
	private ControladoraBD controladorBD;

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

		JList rutas = new JList();
		scrollPane.setViewportView(rutas);

		GridLayout layout = new GridLayout(1, 2);
		setLayout(layout);

		add(panelIzquierda);
		add(panelDerecha);

	}

	public void iniciarComponentes()
	{
		comboViajeRuta = new JComboBox();

		comboTarifaRuta = new JComboBox();

		btnAgregar = new JButton("Crear");
	}

	public void eventosComponentes()
	{

		btnAgregar.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{

				if (comboTarifaRuta.getSelectedItem() != null && comboViajeRuta.getSelectedItem() != null)
				{
					// Tarifa t = (Tarifa) comboTarifaRuta.getSelectedItem();
					// Viaje v = (Viaje) comboViajeRuta.getSelectedItem();

					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = new Date();
					String fechaFormateada = dateFormat.format(date);
					try
					{
						controladorBD.crearRuta(fechaFormateada, "v.getId()", "t.getid()");
					} catch (ClassNotFoundException e)
					{
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(PanelRutas.this, "Error en el programa");

					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(PanelRutas.this, "Error en el programa");
					}

				} else
				{
					JOptionPane.showMessageDialog(PanelRutas.this, "Hay campos vacíos");
				}

			}
		});

	}

}
