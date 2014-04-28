package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class DialogEditarCosto extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private Ventana principal;
	private JTextField txtDescuentos;
	private JTextField txtOrigen;
	private JTextField txtDestino;
	private JTextField textField;
	private ButtonGroup butGroup;
	private JList listCiudades;
	/**
	 * Create the dialog.
	 */
	public DialogEditarCosto(Ventana ventana) 
	{
		setTitle("Editar Costo");
		principal = ventana;

		setBounds(100, 100, 455, 449);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblDescuentos = new JLabel("Descuentos:");
			lblDescuentos.setBounds(10, 11, 117, 14);
			contentPanel.add(lblDescuentos);
		}
		{
			txtDescuentos = new JTextField();
			txtDescuentos.setBounds(10, 30, 214, 20);
			contentPanel.add(txtDescuentos);
			txtDescuentos.setColumns(10);
		}
		{
			JLabel lblCiudades = new JLabel("Ciudades:");
			lblCiudades.setBounds(10, 61, 117, 14);
			contentPanel.add(lblCiudades);
		}
		{
			JLabel lblOrigen = new JLabel("Origen:");
			lblOrigen.setBounds(234, 87, 65, 14);
			contentPanel.add(lblOrigen);
		}
		{
			txtOrigen = new JTextField();
			txtOrigen.setEditable(false);
			txtOrigen.setBounds(234, 108, 157, 20);
			contentPanel.add(txtOrigen);
			txtOrigen.setColumns(10);
		}
		{
			ImageIcon image = new ImageIcon( getClass().getResource("delete.png"));
			JButton butEliminarOrigen = new JButton(image);
			butEliminarOrigen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					txtOrigen.setText("");
					listCiudades.clearSelection();
				}
			});
			butEliminarOrigen.setBounds(397, 107, 34, 23);
			contentPanel.add(butEliminarOrigen);
		}
		{
			JLabel lblDestino = new JLabel("Destino:");
			lblDestino.setBounds(234, 152, 46, 14);
			contentPanel.add(lblDestino);
		}
		{
			txtDestino = new JTextField();
			txtDestino.setEditable(false);
			txtDestino.setBounds(234, 170, 157, 20);
			contentPanel.add(txtDestino);
			txtDestino.setColumns(10);
		}
		{
			ImageIcon image = new ImageIcon(getClass().getResource("delete.png"));
			JButton butEliminarDestino = new JButton(image);
			butEliminarDestino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					txtDestino.setText("");
					listCiudades.clearSelection();
				}
			});
			butEliminarDestino.setBounds(397, 167, 34, 23);
			contentPanel.add(butEliminarDestino);
		}
		{
			JLabel lblTarifa = new JLabel("Tarifa:");
			lblTarifa.setBounds(10, 270, 100, 14);
			contentPanel.add(lblTarifa);
		}
		{
			textField = new JTextField();
			textField.setBounds(10, 289, 214, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblClase = new JLabel("Clase:");
			lblClase.setBounds(10, 320, 74, 14);
			contentPanel.add(lblClase);
		}

		JRadioButton rdbtnAlta = new JRadioButton("Alta", true);
		rdbtnAlta.setBounds(10, 341, 54, 23);
		contentPanel.add(rdbtnAlta);

		JRadioButton rdbtnMedia = new JRadioButton("Media");
		rdbtnMedia.setBounds(88, 341, 65, 23);
		contentPanel.add(rdbtnMedia);

		JRadioButton rdbtnBaja = new JRadioButton("Baja");
		rdbtnBaja.setBounds(171, 341, 109, 23);
		contentPanel.add(rdbtnBaja);
		
		butGroup = new ButtonGroup();
		butGroup.add(rdbtnBaja);
		butGroup.add(rdbtnMedia);
		butGroup.add(rdbtnAlta);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 81, 214, 184);
			contentPanel.add(scrollPane);
			{
				String [] a = {"a","b","c"};
				listCiudades = new JList(a);
				listCiudades.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg) 
					{
						if( txtOrigen.getText().equals("") )
						{
							txtOrigen.setText( " " );
						}
						else if( txtOrigen.getText().equals(" ") )
						{
							txtOrigen.setText( listCiudades.getSelectedValue().toString() );
						}
						else if( txtDestino.getText().equals("") )
						{
							txtDestino.setText( " " );
						}
						else if( txtDestino.getText().equals(" ") )
						{
							txtDestino.setText( listCiudades.getSelectedValue().toString() );
						}
					}
				});
				scrollPane.setViewportView(listCiudades);
			}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
