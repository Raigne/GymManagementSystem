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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateAdmin {

	private JFrame frame;
	private JPanel panelLabel;
	private JPanel panelText;
	private JPanel panel;
	private JPanel panelSname;
	private JPanel panelMname;
	private JPanel panelFname;
	private JPanel panelUser;
	private JPanel panelAge;
	private JPanel panelBday;
	private JPanel panelAdd;
	private JPanel panelContact;
	private JPanel panelBal;
	private JPanel panelRoom;
	private JPanel panelShift;
	private JPanel panelCheck;
	private JLabel labelTitle;
	private JLabel labelFname;
	private JLabel labelMname;
	private JLabel labelSname;
	private JLabel labelUser;
	private JLabel labelAge;
	private JLabel labelBday;
	private JLabel labelRoom;
	private JLabel labelAddress;
	private JLabel labelStatus;
	private JLabel labelNumber;
	private JLabel labelShift;
	private JLabel labelCheck;
	private JTextField tfFname;
	private JTextField tfMname;
	private JTextField tfSname;
	private JTextField tfUser;
	private JTextField tfAge;
	private JTextField tfBday;
	private JTextField tfRoom;
	private JTextField tfStatus;
	private JTextField tfAddress;
	private JTextField tfNumber;
	private JTextField tfShift;
	private JTextField tfCheck;
	private JButton btnSave;
	private JButton btnBack;
	
	public static void updateUi() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAdmin window = new UpdateAdmin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UpdateAdmin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Update");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Panel
				panel = new JPanel();
				panelLabel = new JPanel();
				panelLabel.setBackground(new Color(242,245,246));
				panelText = new JPanel();
				panelText.setBackground(new Color(21,173,228));
				panelSname = new JPanel();
				panelSname.setLayout(new GridLayout(1,2));
				panelSname.setBackground(new Color(21,173,228));
				panelFname = new JPanel();
				panelFname.setLayout(new GridLayout(1,2));
				panelFname.setBackground(new Color(21,173,228));
				panelMname = new JPanel();
				panelMname.setLayout(new GridLayout(1,2));
				panelMname.setBackground(new Color(21,173,228));
				panelUser = new JPanel();
				panelUser.setLayout(new GridLayout(1,2));
				panelUser.setBackground(new Color(21,173,228));
				panelAdd = new JPanel();
				panelAdd.setLayout(new GridLayout(1,2));
				panelAdd.setBackground(new Color(21,173,228));
				panelRoom = new JPanel();
				panelRoom.setLayout(new GridLayout(1,2));
				panelRoom.setBackground(new Color(21,173,228));
				panelShift = new JPanel();
				panelShift.setLayout(new GridLayout(1,2));
				panelShift.setBackground(new Color(21,173,228));
				panelCheck = new JPanel();
				panelCheck.setLayout(new GridLayout(1,2));
				panelCheck.setBackground(new Color(21,173,228));
				
				//Label
				labelTitle = new JLabel("");
				Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
				labelTitle.setIcon(new ImageIcon(imgTitle));
				labelSname = new JLabel("Surname: ");
				labelSname.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				labelFname = new JLabel("First Name: ");
				labelFname.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				labelMname = new JLabel("Middle Name: ");
				labelMname.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				labelUser = new JLabel("Username: ");
				labelUser.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				labelAddress = new JLabel("Address: ");
				labelAddress.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				labelRoom = new JLabel("Room Type: ");
				labelRoom.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				labelShift = new JLabel("Shift: ");
				labelShift.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				labelCheck = new JLabel("Health Condition: ");
				labelCheck.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				
				//TextField
				tfSname = new JTextField(20);
				tfSname.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				tfSname.setEditable(false);
				
				tfCheck = new JTextField(20);
				tfCheck.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				tfCheck.setEditable(false);
				
				tfFname = new JTextField(20);
				tfFname.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				tfFname.setEditable(false);
				
				tfMname = new JTextField(20);
				tfMname.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				tfMname.setEditable(false);
				
				tfUser = new JTextField(20);
				tfUser.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				tfUser.setEditable(false);
				
				tfAddress = new JTextField(20);
				tfAddress.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				tfAddress.setEditable(false);
				
				tfRoom = new JTextField(10);
				tfRoom.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				tfRoom.setEditable(false);
				
				tfShift = new JTextField(10);
				tfShift.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				tfShift.setEditable(false);
				//Buttons
				btnSave = new JButton("Save");
				btnSave.setFont(new Font("Century Gothic", Font.PLAIN, 18));
				btnSave.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		JOptionPane.showMessageDialog(null, "Save Successful");
		        		Admin ad = new Admin();
		        		ad.mainAd();
		        		frame.dispose();
		        	}
		        });
				btnBack = new JButton("Back");
				btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 18));
				btnBack.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		Admin ad = new Admin();
		        		ad.mainAd();
		        		frame.dispose();
		        	}
		        });
				//add to panel
				panelLabel.add(labelTitle);
				panelSname.add(labelSname);
				panelSname.add(tfSname);
				panelFname.add(labelFname);
				panelFname.add(tfFname);
				panelMname.add(labelMname);
				panelMname.add(tfMname);
				panelUser.add(labelUser);
				panelUser.add(tfUser);
				panelAdd.add(labelAddress);
				panelAdd.add(tfAddress);
				panelRoom.add(labelRoom);
				panelRoom.add(tfRoom);
				panelShift.add(labelShift);
				panelShift.add(tfShift);
				panelCheck.add(labelCheck);
				panelCheck.add(tfCheck);
				
				panelText.add(panelSname);
				panelText.add(panelFname);
				panelText.add(panelMname);
				panelText.add(panelUser);
				panelText.add(panelAdd);
				panelText.add(panelCheck);
				panelBday = new JPanel();
				panelBday.setLayout(new GridLayout(1,2));
				labelBday = new JLabel("Birthdate: ");
				labelBday.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				
				tfBday = new JTextField(17);
				tfBday.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				panelBday.setBackground(new Color(21,173,228));
				tfBday.setEditable(false);
				panelBday.add(labelBday);
				panelBday.add(tfBday);
				panelText.add(panelBday);
				panelAge = new JPanel();
				panelAge.setLayout(new GridLayout(1,2));
				labelAge = new JLabel("Age: ");
				labelAge.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				
				tfAge = new JTextField(2);
				tfAge.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				panelAge.setBackground(new Color(21,173,228));
				tfAge.setEditable(false);
				panelAge.add(labelAge);
				panelAge.add(tfAge);
				panelText.add(panelAge);
				panelContact = new JPanel();
				panelContact.setLayout(new GridLayout(1,2));
				panelContact.setBackground(new Color(21,173,228));
				labelNumber = new JLabel("Contact No:");
				labelNumber.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				
				tfNumber = new JTextField(12);
				tfNumber.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				tfNumber.setEditable(false);
				panelContact.add(labelNumber);
				panelContact.add(tfNumber);
				panelText.add(panelContact);
				panelBal = new JPanel();
				panelText.add(panelBal);
				panelBal.setLayout(new GridLayout(1,2));
				panelBal.setBackground(new Color(21,173,228));
				labelStatus = new JLabel("Balance Status: ");
				labelStatus.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				
				tfStatus = new JTextField(5);
				tfStatus.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				panelBal.add(labelStatus);
				panelBal.add(tfStatus);
				panelText.add(panelRoom);
				panelText.add(panelShift);
				
				panel.add(btnSave);
				panel.add(btnBack);
				
				//add to frame
				frame.getContentPane().add(panelLabel,"North");
				frame.getContentPane().add(panelText);
				frame.getContentPane().add(panel, "South");
				frame.setSize(834, 531);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
	}
}