package GymInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Message {

	private JFrame frame;
	private JPanel panelBtn;
	private JPanel panelLabel;
	private JPanel panelText;
	private JLabel labelTitle;
	private JLabel labelSend;
	private JLabel labelMessage;
	private JTextField tfSend;
	private JTextArea taMessage;
	private JScrollPane scroll;
	private JButton btnSend;
	private JButton btnBack;
	int intCtr;
	String strUser;
	String strInbox;
	ArrayList<String> user = new ArrayList<String>();
	ArrayList<String> inbox = new ArrayList<String>();
	
	private static Connection cncDatabase;
	private final static String URL = new String("jdbc:mysql://localhost/AdvProg");
	private final static String USER = new String("root");
	private final static String PASS = new String("youdontknow");

	
	public static void messageUi() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Message window = new Message();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Message() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//panel
		panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout());
		panelLabel = new JPanel();
		panelLabel.setLayout(new FlowLayout());
		panelLabel.setBackground(new Color(242,245,246));
		panelText = new JPanel();
		panelText.setLayout(new FlowLayout());
		panelText.setBackground(new Color(52,152,246));
		//Label
		labelTitle = new JLabel("");
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		labelTitle.setIcon(new ImageIcon(imgTitle));
		labelSend = new JLabel("Send to: ");
		labelSend.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		labelMessage = new JLabel("Message: ");
		labelMessage.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		//TextField
		tfSend = new JTextField(30);
		tfSend.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		//TextArea and Scroll
		taMessage = new JTextArea(20,60);
		scroll = new JScrollPane(taMessage, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//Button
		btnSend = new JButton("Send");
		btnSend.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnSend.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String sql = "SELECT * FROM memberaccountinfo";
        		
        		try{
        			Connection con = DriverManager.getConnection(URL, USER, PASS);
        			Statement s1 = con.prepareStatement(sql);
        			ResultSet rs1 = s1.executeQuery(sql);
        			
        			while(rs1.next()){
        				strUser = rs1.getString(2);
        				user.add(strUser);
        				strInbox = rs1.getString(1);
        				inbox.add(strInbox);
        			}
        			for(intCtr = 0; intCtr < inbox.size(); intCtr++){
        				if(tfSend.getText().equalsIgnoreCase(user.get(intCtr))){
        					String sql1 = "UPDATE  memberaccountinfo SET Inbox = ? WHERE MemberID =" + inbox.get(intCtr);
        					PreparedStatement statement1 = con.prepareStatement(sql1);
        					statement1.setString(1, taMessage.getText());
                			statement1.executeUpdate();
        				}
        			}
        		}
        		catch(SQLException a){
        			a.printStackTrace();
        		}
        		
        		JOptionPane.showMessageDialog(null, "Message Sent");
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
		panelText.add(labelSend);
		panelText.add(tfSend);
		panelText.add(labelMessage);
		panelText.add(scroll);
		panelBtn.add(btnSend);
		panelBtn.add(btnBack);
		//add to frame
		frame.getContentPane().add(panelLabel,"North");
		frame.getContentPane().add(panelText);
		frame.getContentPane().add(panelBtn, "South");
		frame.setSize(700, 604);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}


}
