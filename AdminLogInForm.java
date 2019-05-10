package GymInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;
import java.util.ArrayList;

public class AdminLogInForm extends Encryption{

	private JFrame frame;
	private JPanel panel;
	private JPanel panelLabel;
	private JPanel panelText;
	private JPanel panelUser;
	private JPanel panelMid;
	private JLabel labelTitle;
	private JLabel labelUser;
	private JLabel labelPass;
	private JTextField tfUser;
	private JTextField tfPass;
	private JButton btnLogIn;
	private JButton btnBack;
	private JButton btnExit;

	private static Connection cncDatabase;
	private final static String URL = new String("jdbc:mysql://localhost/AdvProg");
	private final static String USER = new String("root");
	private final static String PASS = new String("youdontknow");
	
	private ArrayList<String> strUser = new ArrayList<String>();
	String strUserTemp = null;
	private ArrayList<String> strPass = new ArrayList<String>();
	String strPassTemp = null;
	String strTemp = null;
	String strTemp2 = null;
	String strUse, strPword;
	
	public static void admin() {
		try{
			cncDatabase = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("CONNECTED");
		}
		catch(SQLException objSqlException){
			objSqlException.printStackTrace();
		}//catch
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogInForm window = new AdminLogInForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminLogInForm() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Panel
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panelLabel = new JPanel();
		panelLabel.setLayout(new FlowLayout());
		panelLabel.setBackground(new Color(242,245,246));
		panelMid = new JPanel();
		panelMid.setBackground(new Color(21,173,228));
		panelText = new JPanel();
		panelText.setLayout(new GridLayout(1,2));
		panelText.setBackground(new Color(21,173,228));
		panelUser = new JPanel();
		panelUser.setLayout(new GridLayout(1,2));
		panelUser.setBackground(new Color(21,173,228));
		//Label
		labelTitle = new JLabel("");
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		labelTitle.setIcon(new ImageIcon(imgTitle));
		labelUser = new JLabel("Username: ");
		labelUser.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		labelPass = new JLabel("Password: ");
		labelPass.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		//TextField
		tfUser = new JTextField(15);
		tfUser.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tfPass = new JPasswordField(15);
		tfPass.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		//tfPass.setEchoChar('*');
		//Buttons
		btnLogIn = new JButton("Log-In");
		btnLogIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		getData();
        	}
        });
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UiLogIn front = new UiLogIn();
        		front.main(null);
        		frame.dispose();
        	}
        });
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
		btnLogIn.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnExit.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		//add to panel
		panelLabel.add(labelTitle);
		panelUser.add(labelUser);
		panelUser.add(tfUser);
		panelText.add(labelPass);
		panelText.add(tfPass);
		panelMid.add(panelUser);
		panelMid.add(panelText);
		panel.add(btnLogIn);
		panel.add(btnBack);
		panel.add(btnExit);
		//add to frame
		frame.getContentPane().add(panelLabel,"North");
		frame.getContentPane().add(panelMid);
		frame.getContentPane().add(panel, "South");
		frame.setSize(674, 276);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

	public static void sayUser(String user){
		System.out.println(user);
	}
	
	public void getData(){
		strTemp = tfUser.getText();
		strTemp2 = tfPass.getText();
		int intCtr =0;
		String sql = "SELECT * FROM memberaccountinfo";
		try{
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			//GET PREPARED STMT
			Statement s = con.prepareStatement(sql);
			ResultSet rs = s.executeQuery(sql);
		
			while(rs.next()){
				strPassTemp = rs.getString(3);
				strUserTemp = rs.getString(2);
				strUser.add(strUserTemp);
				strPass.add(strPassTemp);
			}
		}
		catch(SQLException g){
			g.printStackTrace();
		}
		if(strTemp.isEmpty()){
			try {
				JOptionPane.showMessageDialog(null, "Please Enter a Username/Password");
				throw new Exception("Enter a Username or Password");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else{
			if(strTemp.equalsIgnoreCase("Admin")){
				Admin objAdmin = new Admin();
				objAdmin.mainAd();
				frame.dispose();
			}
			else{
				for(intCtr=0;intCtr<strUser.size();intCtr++){
					if(strTemp.equals(strUser.get(intCtr))){
						for(intCtr=0;intCtr<strPass.size();intCtr++){
							if(strTemp2.equals(strPass.get(intCtr))){
								Member objMember = new Member(strTemp);
		        				objMember.mainMem(strTemp);
		        				frame.dispose();
							}
						}
					}
				}
			}
		sayUser(strTemp);
		}
	}
	public AdminLogInForm(String strLog){
		strLog = strTemp;
	}
	public class FrameListener extends WindowAdapter{
		public void windowClosing(WindowEvent objEvent){
			try{	
				
				cncDatabase.close();
				System.out.println("DISCONNECTED");
				
			}catch(SQLException objSqlException){
				
				objSqlException.printStackTrace();
				
			}//catch
		}//windowClosing
	}//FrameListener
}
