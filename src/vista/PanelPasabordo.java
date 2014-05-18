package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelPasabordo extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private JButton btnConsultar;
	private JLabel lblIdUsuario, lblIdViaje;
	private JTextField txtIdUsuario, txtIdViaje;
	
	public PanelPasabordo(PanelClientes ventana)
	{
		this.setSize(ventana.getSize());
		setLayout(null);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(240, 200, 133, 30);
		add(btnConsultar);
		
		lblIdUsuario = new JLabel("Id Usuario");
		lblIdUsuario.setBounds(60, 30, 150, 30);
		lblIdUsuario.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lblIdUsuario);
		
		txtIdUsuario = new JTextField();
		txtIdUsuario.setBounds(220, 30, 200, 30);
		txtIdUsuario.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		add(txtIdUsuario);
		
		lblIdViaje = new JLabel("Id Viaje");
		lblIdViaje.setBounds(60, 80, 150, 30);
		lblIdViaje.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lblIdViaje);
		
		txtIdViaje = new JTextField();
		txtIdViaje.setBounds(220, 80, 200, 30);
		txtIdViaje.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		add(txtIdViaje);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnConsultar)
		{
			JOptionPane.showMessageDialog(null, "Oprimió el botón del panel", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
		}
	}	
}
