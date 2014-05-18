package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class PanelRealizarCompra extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JButton btnConsultar;
	private JLabel lblOrigen, lblDestino, lblClase;
	private JComboBox<String> comboOrigen, comboDestino, comboClase;
	private JCalendar calendario;
	
	private JButton btnVolver, btnComprar;
	private JList<String> lista;
	private DefaultListModel<String> model;
	private JScrollPane scrollLista;
	
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
		
		lista = new JList<String>();
		lista.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		
		model = new DefaultListModel<String>();
		
		scrollLista = new JScrollPane();
		scrollLista.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollLista.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		scrollLista.setBounds(165, 10, 360, 330);
		scrollLista.getViewport().add(lista);
		scrollLista.setBackground(Color.black);
		scrollLista.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		scrollLista.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollLista.setVisible(false);
		add(scrollLista);
		
		lista.setModel(model);
		
		lista.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent evt)
			{
				@SuppressWarnings("unchecked")
				JList<String> list = (JList<String>) evt.getSource();
				
				if(evt.getClickCount() == 2)
					JOptionPane.showMessageDialog(null, "Oprimió dos veces el clic en " + list.getSelectedValue(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
	}

	public String darFecha()
	{
		String respuesta;
		String[] fecha = calendario.getDate().toString().split(" ");
		respuesta = new String(fecha[5] + "-" + fecha[1] + "-" + fecha[2] + "-" + fecha[3]);
		return respuesta;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnConsultar)
		{
			if(comboOrigen.getSelectedIndex() != comboDestino.getSelectedIndex())
			{
				JOptionPane.showMessageDialog(null, darFecha(), "La fecha", JOptionPane.INFORMATION_MESSAGE);
				
				btnConsultar.setVisible(false);
				lblOrigen.setVisible(false);
				lblDestino.setVisible(false);
				lblClase.setVisible(false);
				comboOrigen.setVisible(false);
				comboDestino.setVisible(false);
				calendario.setVisible(false);
				comboClase.setVisible(false);
				
				btnVolver.setVisible(true);
				scrollLista.setVisible(true);
				btnComprar.setVisible(true);				
			}
			
			else
				JOptionPane.showMessageDialog(null, "Las ciudades de origen y destino deben ser diferentes!", "ERROR", JOptionPane.ERROR_MESSAGE);
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
			scrollLista.setVisible(false);
			btnComprar.setVisible(false);
		}
		
		else if(e.getSource() == btnComprar)
		{
			if(lista.getSelectedValue() != null)
			{
				String idComprador = JOptionPane.showInputDialog("Ingrese el ID del comprador"),
					   idVendedor = JOptionPane.showInputDialog("Ingrese el ID del vendedor");
				
				if(!idComprador.equals("") && idComprador != null && !idVendedor.equals("") && idVendedor != null)
				{
					JOptionPane.showMessageDialog(null, "Gracias por comprar el vuelo " + lista.getSelectedValue(), "EXITO", JOptionPane.INFORMATION_MESSAGE);
					
					comboOrigen.setSelectedIndex(0);
					comboDestino.setSelectedIndex(0);
					comboClase.setSelectedIndex(0);
					
					btnConsultar.setVisible(true);
					lblOrigen.setVisible(true);
					lblDestino.setVisible(true);
					lblClase.setVisible(true);
					comboOrigen.setVisible(true);
					comboDestino.setVisible(true);
					calendario.setVisible(true);
					comboClase.setVisible(true);
					
					btnVolver.setVisible(false);
					scrollLista.setVisible(false);
					btnComprar.setVisible(false);					
				}
				
				else
					JOptionPane.showMessageDialog(null, "Los ID's de comprador y vendedor no son válidos", "ERROR", JOptionPane.ERROR_MESSAGE);
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
}
