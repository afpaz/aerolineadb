package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Pasabordo;

public class PanelPasabordo extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JButton btnConsultar, btnVolver;
	private JLabel lblIdUsuario, lblIdViaje;
	private JTextField txtIdUsuario;
	private JList<Pasabordo> lista;
	private DefaultListModel<Pasabordo> model;
	private JScrollPane scrollLista;
	public static String idUsuario, idViaje;

	private JTextField txtIdViaje;
	
	public PanelPasabordo(PanelClientes ventana)
	{
		this.setSize(ventana.getSize());
		setLayout(null);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(10, 60, 133, 30);
		add(btnConsultar);
		
		lblIdUsuario = new JLabel("Id Usuario");
		lblIdUsuario.setBounds(10, 10, 150, 30);
		lblIdUsuario.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lblIdUsuario);
		
		txtIdUsuario = new JTextField();
		txtIdUsuario.setBounds(100, 10, 200, 30);
		txtIdUsuario.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		add(txtIdUsuario);
		
		lblIdViaje = new JLabel("Id Viaje");
		lblIdViaje.setBounds(340, 10, 150, 30);
		lblIdViaje.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lblIdViaje);
		
		txtIdViaje = new JTextField();
		txtIdViaje.setBounds(410, 10, 200, 30);
		txtIdViaje.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		add(txtIdViaje);
		
		//----------------------------------------------------------------------
		
		btnVolver = new JButton("Atrás");
		btnVolver.addActionListener(this);
		btnVolver.setBounds(300, 353, 100, 30);
		add(btnVolver);
		btnVolver.setVisible(false);
		
		lista = new JList<Pasabordo>();
		lista.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		
		model = new DefaultListModel<Pasabordo>();
		
		scrollLista = new JScrollPane();
		scrollLista.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLista.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		scrollLista.setBounds(165, 80, 360, 260);
		scrollLista.getViewport().add(lista);
		scrollLista.setBackground(Color.black);
		scrollLista.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		scrollLista.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollLista.setVisible(false);
		add(scrollLista);
		
		lista.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent evt)
			{
				@SuppressWarnings("unchecked")
				JList<Pasabordo> list = (JList<Pasabordo>) evt.getSource();
				
				if(evt.getClickCount() == 2)
					JOptionPane.showMessageDialog(null, "Fecha Vuelo: " +
												  list.getSelectedValue().darFecha() + "\nId Venta: " + 
												  list.getSelectedValue().darVentaId() + "\nId Vuelo: " +
												  list.getSelectedValue().darVueloId() + "\nId Viaje: " +
												  list.getSelectedValue().darIdViaje() + "\nCiudad origen: " + 
												  list.getSelectedValue().darOrigeb() + "\nCiudad destino: " +
												  list.getSelectedValue().darDestino() + "\nClase: " + 
												  list.getSelectedValue().darClase() + "\nCliente: " + 
												  list.getSelectedValue().darCliente() + "\nVendedor: " +
												  list.getSelectedValue().darVendedor(), 
												  "INFORMACION PASABORDO", 
												  JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnConsultar)
		{
			try
			{
				idUsuario = txtIdUsuario.getText();
				idViaje = txtIdViaje.getText();
				
				btnConsultar.setEnabled(false);
				lblIdUsuario.setEnabled(false);
				lblIdViaje.setEnabled(false);
				txtIdUsuario.setEnabled(false);
				txtIdViaje.setEnabled(false);
				
				ArrayList<Pasabordo> pasabordos = new PanelClientes().darListaPasabordos();
				
				for(int i = 0; i < pasabordos.size(); i++)
					model.add(i, pasabordos.get(i));
				
				lista.setModel(model);
				
				btnVolver.setVisible(true);
				scrollLista.setVisible(true);
				
				if(model.size() == 0)
				{
					JOptionPane.showMessageDialog(null, "No hay pasabordos disponibles", "Pasabordos", JOptionPane.INFORMATION_MESSAGE);

					btnConsultar.setEnabled(true);
					lblIdUsuario.setEnabled(true);
					lblIdViaje.setEnabled(true);
					txtIdUsuario.setEnabled(true);
					txtIdViaje.setEnabled(true);
					
					txtIdUsuario.setText("");
					txtIdViaje.setText("");
					
					btnVolver.setVisible(false);
					scrollLista.setVisible(false);
				}
			} 
			
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		if(e.getSource() == btnVolver)
		{
			btnConsultar.setEnabled(true);
			lblIdUsuario.setEnabled(true);
			lblIdViaje.setEnabled(true);
			txtIdUsuario.setEnabled(true);
			txtIdViaje.setEnabled(true);
			
			txtIdUsuario.setText("");
			txtIdViaje.setText("");
			
			btnVolver.setVisible(false);
			scrollLista.setVisible(false);
			
			model.clear();
		}
	}	
}
