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
	private JButton btnGuardarTarifa;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private JList listTarifas;
	private JTextField txtId;

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
		
		listTarifas = new JList();
		scrollPane.setViewportView(listTarifas);
		
		btnGuardarTarifa = new JButton("Guardar");
		btnGuardarTarifa.setBounds(487, 213, 95, 23);
		add(btnGuardarTarifa);
		
		btnEliminar = new JButton( new ImageIcon("./imagenes/delete.png") );
		btnEliminar.setBounds(118, 321, 41, 23);
		add(btnEliminar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(358, 213, 95, 23);
		add(btnLimpiar);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(343, 63, 46, 14);
		add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(459, 60, 141, 20);
		add(txtId);
		txtId.setColumns(10);

	}

	public JTextField getTxtValorKm() {
		return txtValorKm;
	}

	public void setTxtValorKm(JTextField txtValorKm) {
		this.txtValorKm = txtValorKm;
	}

	public JTextField getTxtLimInfKm() {
		return txtLimInfKm;
	}

	public void setTxtLimInfKm(JTextField txtLimInfKm) {
		this.txtLimInfKm = txtLimInfKm;
	}

	public JTextField getTxtLimSup() {
		return txtLimSup;
	}

	public void setTxtLimSup(JTextField txtLimSup) {
		this.txtLimSup = txtLimSup;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}
	
	public JList getListTarifas() {
		return listTarifas;
	}

	public void setListTarifas(JList listTarifas) {
		this.listTarifas = listTarifas;
	}

	public JButton getBtnGuardarTarifa() {
		return btnGuardarTarifa;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}
	
	public void limpiarCampos() 
	{
		txtId.setText("");
		txtLimInfKm.setText("");
		txtLimSup.setText("");
		txtValorKm.setText("");
	}
	
}
