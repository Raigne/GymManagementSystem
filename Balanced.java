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

public class Balanced {

	JFrame frame = new JFrame("CheckUp");
	
	JTextArea txtArea = new JTextArea(" Due to your current health condition,\n you can only apply in these two programs:\n\n Light Workout Program:\n Aerobics\n Yoga\n Light exercises that can improve your muscles.\n\n Balanced Workout Program:\n Cardiovascular exercises\n Light weightlifting");
	Container contentPane;
	JScrollPane scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JLabel lblTitle = new JLabel("");
	String strCondition = "Qualified";
	
	JButton btnOK = new JButton("OK");
	
	JPanel panelButton = new JPanel();

	public static void mainBal() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Balanced window = new Balanced();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Balanced() {
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
		frame.setSize(507,456);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		txtArea.setEditable(false);
	}

}
