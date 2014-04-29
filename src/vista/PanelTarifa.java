package vista;

import java.awt.Color;

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
		lblValorDeKm.setBounds(269, 96, 68, 14);
		add(lblValorDeKm);
		
		JLabel lblLimiteInfKm = new JLabel("Limite inf km");
		lblLimiteInfKm.setBounds(269, 132, 68, 14);
		add(lblLimiteInfKm);
		
		JLabel lblLimiteSupKm = new JLabel("Limite sup km");
		lblLimiteSupKm.setBounds(269, 171, 68, 14);
		add(lblLimiteSupKm);
		
		txtValorKm = new JTextField();
		txtValorKm.setBounds(364, 93, 115, 20);
		add(txtValorKm);
		txtValorKm.setColumns(10);
		
		txtLimInfKm = new JTextField();
		txtLimInfKm.setBounds(364, 129, 115, 20);
		add(txtLimInfKm);
		txtLimInfKm.setColumns(10);
		
		txtLimSup = new JTextField();
		txtLimSup.setBounds(364, 168, 115, 20);
		add(txtLimSup);
		txtLimSup.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 225, 299);
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnGuardarTarifa = new JButton("Guardar");
		btnGuardarTarifa.setBounds(364, 242, 115, 23);
		add(btnGuardarTarifa);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(69, 321, 91, 23);
		add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(364, 208, 115, 23);
		add(btnLimpiar);

	}
}
