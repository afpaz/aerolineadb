package vista;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Ventana extends JFrame {
	public Ventana() {
		//1. Create the frame.
		super();
		this.setLayout(new GridLayout(1, 1));
		//2. Optional: What happens when the frame closes?
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//3. Create components and put them in the frame.
		//TODO all all panes to
		JTabbedPane tabbedPane = new JTabbedPane();
		PanelConsultaViajes consultaViajes = new PanelConsultaViajes();
        tabbedPane.addTab("Tap 1", consultaViajes);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		
		//Add the tabbed pane to this panel.
        add(tabbedPane);
        //4. Size the frame.
		this.pack();
		//5. Show it.
		this.setVisible(true);
	}

}
