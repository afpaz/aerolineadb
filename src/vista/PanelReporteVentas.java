package vista;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PanelReporteVentas extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JLabel lblNombreVendedor;
	private JLabel lblValorTotalVendido;
	private JLabel lblNumeroTiquetes;
	private JList listaLugares;
	private JButton btnGenerarReporte;
	private JTextField idVendedor;
	
	public PanelReporteVentas() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(1dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(7dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(51dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(22dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(119dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblInfoGral = new JLabel("Informaci\u00F3n General:");
		add(lblInfoGral, "4, 4, 17, 1");
		
		JLabel lblLugaresMsPopulares = new JLabel("Lugares m\u00E1s populares:");
		add(lblLugaresMsPopulares, "24, 4, 5, 1");
		
		JPanel panel = new JPanel();
		add(panel, "4, 6, 19, 1, fill, fill");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{17, 115, 20, 56, 72, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 15, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblVendedor = new JLabel("Vendedor: ");
		lblVendedor.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblVendedor = new GridBagConstraints();
		gbc_lblVendedor.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblVendedor.gridwidth = 3;
		gbc_lblVendedor.anchor = GridBagConstraints.WEST;
		gbc_lblVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblVendedor.gridx = 0;
		gbc_lblVendedor.gridy = 1;
		panel.add(lblVendedor, gbc_lblVendedor);
		
		lblNombreVendedor = new JLabel("");
		lblNombreVendedor.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblNombreVendedor = new GridBagConstraints();
		gbc_lblNombreVendedor.anchor = GridBagConstraints.WEST;
		gbc_lblNombreVendedor.gridwidth = 3;
		gbc_lblNombreVendedor.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombreVendedor.gridx = 3;
		gbc_lblNombreVendedor.gridy = 1;
		panel.add(lblNombreVendedor, gbc_lblNombreVendedor);
		
		JLabel lblTotalVendido = new JLabel("Total vendido:");
		lblTotalVendido.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblTotalVendido = new GridBagConstraints();
		gbc_lblTotalVendido.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTotalVendido.anchor = GridBagConstraints.WEST;
		gbc_lblTotalVendido.gridwidth = 3;
		gbc_lblTotalVendido.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalVendido.gridx = 0;
		gbc_lblTotalVendido.gridy = 2;
		panel.add(lblTotalVendido, gbc_lblTotalVendido);
		
		lblValorTotalVendido = new JLabel("");
		lblValorTotalVendido.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblValorTotalVendido = new GridBagConstraints();
		gbc_lblValorTotalVendido.insets = new Insets(0, 0, 5, 0);
		gbc_lblValorTotalVendido.anchor = GridBagConstraints.WEST;
		gbc_lblValorTotalVendido.gridwidth = 3;
		gbc_lblValorTotalVendido.gridx = 3;
		gbc_lblValorTotalVendido.gridy = 2;
		panel.add(lblValorTotalVendido, gbc_lblValorTotalVendido);
		
		JLabel lblNumero = new JLabel("N\u00FAmero de tiquetes:");
		lblNumero.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblNumero = new GridBagConstraints();
		gbc_lblNumero.gridwidth = 3;
		gbc_lblNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNumero.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumero.gridx = 0;
		gbc_lblNumero.gridy = 3;
		panel.add(lblNumero, gbc_lblNumero);
		
		lblNumeroTiquetes = new JLabel("");
		lblNumeroTiquetes.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblNumeroTiquetes = new GridBagConstraints();
		gbc_lblNumeroTiquetes.anchor = GridBagConstraints.WEST;
		gbc_lblNumeroTiquetes.gridwidth = 2;
		gbc_lblNumeroTiquetes.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumeroTiquetes.gridx = 3;
		gbc_lblNumeroTiquetes.gridy = 3;
		panel.add(lblNumeroTiquetes, gbc_lblNumeroTiquetes);
		
		JLabel lblIdVendedor = new JLabel("Id Vendedor:");
		GridBagConstraints gbc_lblIdVendedor = new GridBagConstraints();
		gbc_lblIdVendedor.anchor = GridBagConstraints.EAST;
		gbc_lblIdVendedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdVendedor.gridx = 1;
		gbc_lblIdVendedor.gridy = 6;
		panel.add(lblIdVendedor, gbc_lblIdVendedor);
		
		idVendedor = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 6;
		panel.add(idVendedor, gbc_textField);
		idVendedor.setColumns(10);
		
		btnGenerarReporte = new JButton("Generar Reporte");
		GridBagConstraints gbc_btnGenerarReporte = new GridBagConstraints();
		gbc_btnGenerarReporte.gridwidth = 4;
		gbc_btnGenerarReporte.insets = new Insets(0, 0, 0, 5);
		gbc_btnGenerarReporte.gridx = 1;
		gbc_btnGenerarReporte.gridy = 7;
		panel.add(btnGenerarReporte, gbc_btnGenerarReporte);
		
		listaLugares = new JList();
		add(listaLugares, "24, 6, 5, 1, fill, fill");

	}

	public JLabel getLblNombreVendedor() {
		return lblNombreVendedor;
	}

	public void setLblNombreVendedor(JLabel lblNombreVendedor) {
		this.lblNombreVendedor = lblNombreVendedor;
	}

	public JLabel getLblValorTotalVendido() {
		return lblValorTotalVendido;
	}

	public void setLblValorTotalVendido(JLabel lblValorTotalVendido) {
		this.lblValorTotalVendido = lblValorTotalVendido;
	}

	public JLabel getLblNumeroTiquetes() {
		return lblNumeroTiquetes;
	}

	public void setLblNumeroTiquetes(JLabel lblNumeroTiquetes) {
		this.lblNumeroTiquetes = lblNumeroTiquetes;
	}

	public JList getListaLugares() {
		return listaLugares;
	}

	public void setListaLugares(JList listaLugares) {
		this.listaLugares = listaLugares;
	}

	public JButton getBtnGenerarReporte() {
		return btnGenerarReporte;
	}

	public void setBtnGenerarReporte(JButton btnGenerarReporte) {
		this.btnGenerarReporte = btnGenerarReporte;
	}

	public JTextField getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(JTextField idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	

}
