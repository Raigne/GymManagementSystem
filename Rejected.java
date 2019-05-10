package GymInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Rejected {

	JFrame frame = new JFrame("CheckUp");
	
	JTextArea txtArea = new JTextArea("\n\n\n\tSorry, you can't go to the\n\tgym due to your current\n\t        health condition.");
	Container contentPane;
	JScrollPane scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JLabel lblTitle = new JLabel("");
	
	JButton btnOK = new JButton("OK");
	
	JPanel panelButton = new JPanel();

	public static void mainRej() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rejected window = new Rejected();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Rejected() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		lblTitle.setIcon(new ImageIcon(imgTitle));
		
		txtArea.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnOK.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		btnOK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
		
		panelButton.add(btnOK);
		
		contentPane=frame.getContentPane();
		contentPane.add(lblTitle, BorderLayout.NORTH);
		contentPane.add(scroll, BorderLayout.CENTER);
		contentPane.add(panelButton, BorderLayout.SOUTH);
		
		frame.setResizable(false);
		frame.setSize(507,406);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		txtArea.setEditable(false);
		
	}

}
