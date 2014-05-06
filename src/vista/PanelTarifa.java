package vista;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

public class PanelTarifa extends JPanel {
	private JTextField txtValorKm;
	private JTextField txtLimInfKm;
	private JTextField txtLimSup;

	/**
	 * Create the panel.
	 */
	public PanelTarifa() {
		setLayout(null);
		setBackground( new Color(184, 207, 229) );
		JLabel lblValorDeKm = new JLabel("Valor de km");
		lblValorDeKm.setBounds(342, 95, 85, 14);
		add(lblValorDeKm);
		
		JLabel lblLimiteInfKm = new JLabel("Limite inf km");
		lblLimiteInfKm.setBounds(342, 131, 85, 14);
		add(lblLimiteInfKm);
		
		JLabel lblLimiteSupKm = new JLabel("Limite sup km");
		lblLimiteSupKm.setBounds(342, 170, 85, 14);
		add(lblLimiteSupKm);
		
		txtValorKm = new JTextField();
		txtValorKm.setBounds(459, 92, 141, 20);
		add(txtValorKm);
		txtValorKm.setColumns(10);
		
		txtLimInfKm = new JTextField();
		txtLimInfKm.setBounds(459, 128, 141, 20);
		add(txtLimInfKm);
		txtLimInfKm.setColumns(10);
		
		txtLimSup = new JTextField();
		txtLimSup.setBounds(459, 167, 141, 20);
		add(txtLimSup);
		txtLimSup.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 267, 299);
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnGuardarTarifa = new JButton("Guardar");
		btnGuardarTarifa.setBounds(487, 213, 95, 23);
		add(btnGuardarTarifa);
		
		JButton btnEliminar = new JButton( new ImageIcon( getClass().getResource("delete.png") ) );
		btnEliminar.setBounds(118, 321, 41, 23);
		add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(358, 213, 95, 23);
		add(btnLimpiar);

	}
}
