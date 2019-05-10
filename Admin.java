package GymInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Admin {

	private JFrame frame;
	private JPanel panelLabel;
	private JPanel panelBtn;
	private JPanel panelName;
	private JLabel labelTitle;
	private JLabel labelName;
	private JButton btnUpdate;
	private JButton btnChat;
	private JButton btnMember;
	private JButton btnExit;

	public static void mainAd() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Admin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Admin");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Panel
		panelName = new JPanel();
		panelName.setBackground(new Color(52,152,246));
		panelName.setLayout(new GridLayout(2,1));
		panelLabel = new JPanel();
		panelBtn = new JPanel();
		panelBtn.setLayout(new GridLayout(3,1));
		panelBtn.setBackground(new Color(52,152,246));
		panelLabel.setBackground(new Color(242,245,246));
		//Label
		labelTitle = new JLabel("");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		labelTitle.setIcon(new ImageIcon(imgTitle));
		labelName = new JLabel("Admin");
		labelName.setHorizontalAlignment(SwingConstants.LEFT);
		labelName.setFont(new Font("Century Gothic", Font.ITALIC, 25));
		//Button
		btnChat = new JButton("Message");
		btnChat.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		btnChat.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
        		Message mess = new Message();
        		mess.messageUi();
		   		frame.dispose();
		      	}
	       });
		btnMember = new JButton("Neutron Members");
		btnMember.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		btnMember.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		NeutronMembers mem = new NeutronMembers();
		   	mem.btnMem();
    		frame.dispose();
    		}
	   });
		btnExit = new JButton("Log-Out");
		btnExit.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		   		UiLogIn front = new UiLogIn();
	       		front.main(null);
        		frame.dispose();
        	}
		});
		//add to panel
		panelName.add(labelName);
		panelLabel.add(labelTitle);
		panelBtn.add(btnChat);
		panelBtn.add(btnMember);
		panelBtn.add(btnExit);
		panelLabel.setSize(50,50);
		//add to frame
		frame.getContentPane().add(panelLabel, "North");
		frame.getContentPane().add(panelName);
		frame.getContentPane().add(panelBtn, "South");
		frame.setSize(721, 370);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
}