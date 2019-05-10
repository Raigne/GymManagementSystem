package GymInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UiLogIn {

	private JFrame frame;
	private JPanel panel;
	private JPanel panelButton;
	private JPanel panelLabel;
	private JLabel labelName;
	private JLabel labelImage;
	private JButton btnAccount;
	private JButton btnAdmin;
	private JButton btnLogIn;
	private JButton btnExit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiLogIn window = new UiLogIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UiLogIn() {
		initialize();
	}

	private void initialize() {
		//Frame
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Panel
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(new Color(52,152,246));
		panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout());
		panelLabel = new JPanel();
		panelLabel.setBackground(new Color(242,245,246));
		//Label Image
		labelName = new JLabel("");
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		labelName.setIcon(new ImageIcon(imgTitle));
		labelImage = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
		labelImage.setIcon(new ImageIcon(img));
		//Button LogIn
		btnLogIn = new JButton("Log-In");		
		btnLogIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        		Sample s = new Sample();
        		s.main(null);
        		
        	}
        });
		btnAdmin = new JButton("Admin");		
		btnAdmin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AdminLogInForm admin = new AdminLogInForm();
        		admin.admin();
        		frame.dispose();
        	}
        });
		//Button WalkIn
		btnAccount = new JButton("Don't Have Account ?");
		btnAccount.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CheckUp cu = new CheckUp();
        		cu.CheckUp();
        		frame.dispose();
        	}
        });
		//Button Exit
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
		
		btnLogIn.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnAdmin.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnAccount.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnExit.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		//Adding to Panel
		panelLabel.add(labelName);
		panel.add(labelImage);
		panelButton.add(btnLogIn);
		panelButton.add(btnAdmin);
		panelButton.add(btnAccount);
		panelButton.add(btnExit);
		//Adding to Frame
		frame.getContentPane().add(panelLabel, "North");
		frame.getContentPane().add(panelButton, "South");
		frame.getContentPane().add(panel);
		frame.setSize(516, 711);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
}