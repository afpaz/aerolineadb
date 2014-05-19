package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.freixas.jcalendar.JCalendar;

import control.ControladoraBD;

public class PanelRealizarCompra extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JButton btnConsultar;
	private JLabel lblOrigen, lblDestino, lblClase;
	private JComboBox<String> comboOrigen, comboDestino, comboClase;
	private JCalendar calendario;
	
	private JButton btnVolver, btnComprar;
	private JList<String> lista1, lista2, lista3;
	private DefaultListModel<String> model1, model2, model3;
	private JScrollPane scrollLista1, scrollLista2, scrollLista3;
	
	private String comprador, vendedor;
	
	public PanelRealizarCompra(PanelClientes ventana)
	{
		this.setSize(ventana.getSize());
		setLayout(null);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(290, 350, 133, 30);
		add(btnConsultar);
		
		lblOrigen = new JLabel("Ciudad de Origen");
		lblOrigen.setBounds(130, 10, 150, 30);
		lblOrigen.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lblOrigen);
		
		comboOrigen = new JComboBox<String>();
		comboOrigen.setBounds(290, 10, 280, 30);
		comboOrigen.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		add(comboOrigen);
		
		lblDestino = new JLabel("Ciudad de Destino");
		lblDestino.setBounds(130, 45, 150, 30);
		lblDestino.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lblDestino);
		
		comboDestino = new JComboBox<String>();
		comboDestino.setBounds(290, 45, 280, 30);
		comboDestino.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		add(comboDestino);
		
		lblClase = new JLabel("Clase");
		lblClase.setBounds(130, 80, 150, 30);
		lblClase.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lblClase);
		
		comboClase = new JComboBox<String>();
		comboClase.setBounds(290, 80, 280, 30);
		comboClase.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		add(comboClase);
	
		calendario = new JCalendar();
		calendario.setBounds(190, 130, 340, 200);
		add(calendario);
		
		//-------------------------------------------------------
		
		btnVolver = new JButton("Atrás");
		btnVolver.addActionListener(this);
		btnVolver.setBounds(150, 353, 100, 30);
		add(btnVolver);
		btnVolver.setVisible(false);
		
		btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(this);
		btnComprar.setBounds(439, 353, 100, 30);
		btnComprar.setVisible(false);
		add(btnComprar);
		
		//********************************************************
		//Lista de viajes
		lista1 = new JList<String>();
		lista1.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		
		model1 = new DefaultListModel<String>();
		
		scrollLista1 = new JScrollPane();
		scrollLista1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLista1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		scrollLista1.setBounds(15, 10, 200, 330);
		scrollLista1.getViewport().add(lista1);
		scrollLista1.setBackground(Color.black);
		scrollLista1.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		scrollLista1.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollLista1.setVisible(false);
		add(scrollLista1);
		
		lista1.setModel(model1);
		
		lista1.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent evt)
			{
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				
				if(evt.getClickCount() == 1)
				{
					try {
						String[] rutas = ControladoraBD.getRutasDeViaje(list.getSelectedValue().toString());
						model2.removeAllElements();
						for (String string : rutas) {
							model2.addElement(string);
						}
					} catch (ClassNotFoundException e) {} catch (SQLException e) {}
				}
			}
		});
		//********************************************************
		
		
		//********************************************************
		//Lista de la mitad del panel
		lista2 = new JList<String>();
		lista2.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		
		model2 = new DefaultListModel<String>();
	
		scrollLista2 = new JScrollPane();
		scrollLista2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLista2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		scrollLista2.setBounds(235, 10, 200, 330);
		scrollLista2.getViewport().add(lista2);
		scrollLista2.setBackground(Color.black);
		scrollLista2.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		scrollLista2.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollLista2.setVisible(false);
		add(scrollLista2);
		
		//------------------------------------------------------------------
		model2.add(0, "Esta es la lista 2");
		//------------------------------------------------------------------
		
		lista2.setModel(model2);
		
		lista2.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent evt)
			{
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				
				if(evt.getClickCount() == 1)
				{
					try {
						String[] rutas = ControladoraBD.getVuelosDeRuta(list.getSelectedValue().toString());
						model3.removeAllElements();
						for (String string : rutas) {
							model3.addElement(string);
						}
					} catch (ClassNotFoundException e) {} catch (SQLException e) {}
				}
			}
		});
		//********************************************************
		
		//********************************************************
		//Lista de la derecha del panel
		lista3 = new JList<String>();
		lista3.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		
		model3 = new DefaultListModel<String>();
		
		scrollLista3 = new JScrollPane();
		scrollLista3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLista3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		scrollLista3.setBounds(455, 10, 200, 330);
		scrollLista3.getViewport().add(lista3);
		scrollLista3.setBackground(Color.black);
		scrollLista3.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		scrollLista3.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollLista3.setVisible(false);
		add(scrollLista3);
		
		model3.add(0, "Esta es la lista 3");
		
		lista3.setModel(model3);
		
		lista3.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent evt)
			{
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				
				if(evt.getClickCount() == 2)
					JOptionPane.showMessageDialog(null, "Oprimió dos veces el clic en " + list.getSelectedValue(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		//********************************************************
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnConsultar)
		{
			if(comboOrigen.getSelectedIndex() != comboDestino.getSelectedIndex())
			{
				btnConsultar.setVisible(false);
				lblOrigen.setVisible(false);
				lblDestino.setVisible(false);
				lblClase.setVisible(false);
				comboOrigen.setVisible(false);
				comboDestino.setVisible(false);
				calendario.setVisible(false);
				comboClase.setVisible(false);
				
				btnVolver.setVisible(true);
				scrollLista1.setVisible(true);
				scrollLista2.setVisible(true);
				scrollLista3.setVisible(true);
				btnComprar.setVisible(true);				
			}
			
			else
				JOptionPane.showMessageDialog(null, "Las ciudades de origen y destino deben ser diferentes!", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			try {
				String[] strings = comboOrigen.getSelectedItem().toString().split(",");
				String origen = strings[0];
				strings = comboDestino.getSelectedItem().toString().split(",");
				String destino = strings[0];
				String clase = comboClase.getSelectedItem().toString();
//				JOptionPane.showMessageDialog(this, origen+"."+destino+"."+clase);
				String[] viajes = ControladoraBD.consultarViajes(origen,destino,clase);
				model1.removeAllElements();
				for (int i = 0; i < viajes.length; i++) {
					model1.addElement(viajes[i]);
				}
			} catch (ClassNotFoundException e1) {
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, "error "+e1.getMessage());
			}
			
		}
		
		else if(e.getSource() == btnVolver)
		{
			btnConsultar.setVisible(true);
			lblOrigen.setVisible(true);
			lblDestino.setVisible(true);
			lblClase.setVisible(true);
			comboOrigen.setVisible(true);
			comboDestino.setVisible(true);
			calendario.setVisible(true);
			comboClase.setVisible(true);
			
			btnVolver.setVisible(false);
			scrollLista1.setVisible(false);
			scrollLista2.setVisible(false);
			scrollLista3.setVisible(false);
			btnComprar.setVisible(false);
		}
		
		else if(e.getSource() == btnComprar)
		{
			if(lista1.getSelectedValue() != null && lista2.getSelectedValue() != null)
			{
				try 
				{
					String[] usrs = ControladoraBD.getUsuarios();

					
					comprador = (String) JOptionPane.showInputDialog(this, "Quien es el comprador?", "Comprador", JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        usrs, 
					        usrs[0]);
					vendedor = (String) JOptionPane.showInputDialog(this, 
					        "Quien es el vendedor?",
					        "vendedor",
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        usrs, 
					        usrs[0]);
					
					String idVenta = ControladoraBD.generarVenta(calendario.getDate(), comprador, vendedor);
					ControladoraBD.generarTiquete(4500, idVenta, lista1.getSelectedValue());
					
					try
					{
						int aumento = Integer.parseInt(lista2.getSelectedValue());
						ControladoraBD.aumentarCupo(lista1.getSelectedValue(), aumento);
					}
					
					catch(Exception exce)
					{
						JOptionPane.showMessageDialog(null, exce, "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					
					
					if(!comprador.equals("") && comprador != null && !vendedor.equals("") && vendedor != null)
					{
						
						JOptionPane.showMessageDialog(null, "Gracias por comprar el vuelo " + lista1.getSelectedValue(), "EXITO", JOptionPane.INFORMATION_MESSAGE);
						
						comboOrigen.setSelectedIndex(0);
						comboDestino.setSelectedIndex(0);
						comboClase.setSelectedIndex(0);
						
						btnVolver.setVisible(false);
						scrollLista1.setVisible(false);
						scrollLista2.setVisible(false);
						scrollLista3.setVisible(false);
						btnComprar.setVisible(false);
						scrollLista2.setVisible(false);
						scrollLista3.setVisible(false);
						
						btnConsultar.setVisible(true);
						lblOrigen.setVisible(true);
						lblDestino.setVisible(true);
						lblClase.setVisible(true);
						comboOrigen.setVisible(true);
						comboDestino.setVisible(true);
						calendario.setVisible(true);
						comboClase.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "Los ID's de comprador y vendedor no son válidos", "ERROR", JOptionPane.ERROR_MESSAGE);
				} 
				catch (ClassNotFoundException e1) {} catch (SQLException e1) {}

				btnVolver.setVisible(false);
				scrollLista1.setVisible(false);
				scrollLista2.setVisible(false);
				scrollLista3.setVisible(false);
				btnComprar.setVisible(false);					

				btnConsultar.setVisible(true);
				lblOrigen.setVisible(true);
				lblDestino.setVisible(true);
				lblClase.setVisible(true);
				comboOrigen.setVisible(true);
				comboDestino.setVisible(true);
				calendario.setVisible(true);
				comboClase.setVisible(true);					
				}
			
			else
				JOptionPane.showMessageDialog(null, "Debe seleccionar un vuelo de la lista!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public JComboBox<String> getComboOringen()
	{
		return comboOrigen;
	}
	
	public JComboBox<String> getComboDestino()
	{
		return comboDestino;
	}
	
	public JComboBox<String> getComboClases()
	{
		return comboClase;
	}
	
	public String darComprador()
	{
		return comprador;
	}
	
	public String darVendedor()
	{
		return vendedor;
	}
	
	public Date darFecha()
	{
		return calendario.getDate();
	}
	
	public String darIdViaje()
	{
		return lista1.getSelectedValue();
	}
	
	public String darIdRuta()
	{
		return lista2.getSelectedValue();
	}
}
