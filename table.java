package GymInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class table extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table frame = new table();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public table() {
		DefaultTableModel model = new DefaultTableModel();
	    JTable table = new JTable(model);

	    model.addColumn("Col1");
	    model.addColumn("Col2");

	    model.addRow(new Object[] { "v1", "v2" });

	    model.addRow(new Object[] { "v1" });

	    model.addRow(new Object[] { "v1", "v2", "v3" });

	    JFrame f = new JFrame();
	    f.setSize(300, 300);
	    f.add(new JScrollPane(table));
	    f.setVisible(true);
	}

}
