package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

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
import javax.swing.JTabbedPane;

public class DialogEditarCosto extends JDialog {

	private Ventana principal;
	private ButtonGroup butGroup;
	/**
	 * Create the dialog.
	 */
	public DialogEditarCosto(Ventana ventana) 
	{
		setResizable(false);
		setTitle("Editar Costo");
		principal = ventana;

		setBounds(100, 100, 572, 449);
		{
			ImageIcon image = new ImageIcon( getClass().getResource("delete.png"));
		}
		{
			ImageIcon image = new ImageIcon(getClass().getResource("delete.png"));
		}
		
		butGroup = new ButtonGroup();
		{
			{
				String [] a = {"a","b","c"};
			}
		}
		getContentPane().setLayout(null);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 388, 566, 33);
			buttonPane.setBackground( new Color(184, 207, 229) );
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 566, 387);
		getContentPane().add(tabbedPane);
	}
}
