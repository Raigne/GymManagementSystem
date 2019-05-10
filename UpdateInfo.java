package GymInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UpdateInfo {

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
	private JPanel panelPass;
	private JPanel panelCheck;
	private JPanel panelGender;
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
	private JLabel labelPass;
	private JLabel labelCheck;
	private JLabel labelGender;
	private JTextField tfFname;
	private JTextField tfMname;
	private JTextField tfSname;
	private JTextField tfGender;
	private JTextField tfUser;
	private JTextField tfAge;
	private JTextField tfBday;
	private JTextField tfRoom;
	private JTextField tfStatus;
	private JTextField tfAddress;
	private JTextField tfNumber;
	private JTextField tfShift;
	private JTextField tfCheck;
	private JTextField tfPass;
	private JButton btnSave;
	private JButton btnBack;
	
	private static Connection cncDatabase;
	private final static String URL = new String("jdbc:mysql://localhost/advprog");
	private final static String USER = new String("root");
	private final static String PASS = new String("12345");
	
	public static void up(String strCUser) {
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
					UpdateInfo window = new UpdateInfo(strCUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UpdateInfo(String strCUser) {
		frame = new JFrame();
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
		panelGender.setLayout(new GridLayout(1,2));
		panelGender.setBackground(new Color(21,173,228));
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
		labelAge = new JLabel("Age: ");
		labelAge.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		labelBday = new JLabel("Birthday: ");
		labelBday.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		labelRoom = new JLabel("Room Type: ");
		labelRoom.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		labelAddress = new JLabel("Address: ");
		labelAddress.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		labelNumber = new JLabel("Contact No.: ");
		labelNumber.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		labelStatus = new JLabel("Bal Status: ");
		labelStatus.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		labelShift = new JLabel("Shift: ");
		labelShift.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		labelCheck = new JLabel("Health Condition: ");
		labelGender = new JLabel("Gender: ");
		labelCheck.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		//TextField
		
		tfSname = new JTextField(20);
		tfSname.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfFname = new JTextField(20);
		tfFname.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfMname = new JTextField(20);
		tfMname.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfAge = new JTextField(5);
		tfAge.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfAge.setEditable(false);
		tfStatus = new JTextField(5);
		tfStatus.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfStatus.setEditable(false);
		tfBday = new JTextField(15);
		tfBday.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfRoom = new JTextField(10);
		tfRoom.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfRoom.setEditable(false);
		tfAddress = new JTextField(20);
		tfAddress.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfNumber = new JTextField(20);
		tfNumber.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfShift = new JTextField(10);
		tfShift.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfShift.setEditable(false);
		tfCheck = new JTextField(20);
		tfGender.setFont(new Font("Century Gothic", Font.PLAIN, 20));

		/*String str1 = null;
		String str2 = null;
		String str3 = null;
		String str4 = null;
		String str5 = null;
		String str6 = null;
		String str7 = null;
		String str8 = null;
		String str9 = null;
		String str10 = null;
		String str11 = null;
		String str12 = null;
		String str13 = null;
		String str14 = null;
		String str15 = null;
		
		String sql = "SELECT * FROM memberpersonalinfo";
		String sql2 = "SELECT * FROM membershipinfo";
		String sql3 = "SELECT * FROM memberaccountinfo";
		int intCtr2 = 0;
		try{
			//Get Connection
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			//GET PREPARED STMT
			Statement s = con.prepareStatement(sql);
			ResultSet rs = s.executeQuery(sql);
			Statement s1 = con.prepareStatement(sql);
			ResultSet rs1 = s1.executeQuery(sql);
			Statement s2 = con.prepareStatement(sql);
			ResultSet rs2 = s2.executeQuery(sql);
			Statement s3 = con.prepareStatement(sql);
			ResultSet rs3 = s3.executeQuery(sql);
			Statement s4 = con.prepareStatement(sql);
			ResultSet rs4 = s4.executeQuery(sql);
			Statement s5 = con.prepareStatement(sql);
			ResultSet rs5 = s5.executeQuery(sql);
			Statement s6 = con.prepareStatement(sql);
			ResultSet rs6 = s6.executeQuery(sql);
			Statement s7 = con.prepareStatement(sql);
			ResultSet rs7 = s7.executeQuery(sql);
			Statement s8 = con.prepareStatement(sql);
			ResultSet rs8 = s8.executeQuery(sql);
			Statement s9 = con.prepareStatement(sql2);
			ResultSet rs9 = s9.executeQuery(sql2);
			Statement s10 = con.prepareStatement(sql2);
			ResultSet rs10 = s10.executeQuery(sql2);
			Statement s11 = con.prepareStatement(sql2);
			ResultSet rs11 = s11.executeQuery(sql2);
			Statement s12 = con.prepareStatement(sql2);
			ResultSet rs12 = s12.executeQuery(sql2);
			Statement s13 = con.prepareStatement(sql3);
			ResultSet rs13 = s13.executeQuery(sql3);
			Statement s14 = con.prepareStatement(sql3);
			ResultSet rs14 = s14.executeQuery(sql3);
			
			while(rs.next()){
				str1 = rs.getString(1);
				System.out.println(str1);
			}
			while(rs1.next()){
				str2 = rs1.getString(2);
				System.out.println(str2);
			}
			while(rs2.next()){
				str3 = rs2.getString(3);
				System.out.println(str3);
			}
			while(rs3.next()){
				str4 = rs3.getString(4);
				System.out.println(str4);
			}
			while(rs4.next()){
				str5 = rs4.getString(5);
				System.out.println(str5);
			}
			while(rs5.next()){
				str6 = rs5.getString(6);
				System.out.println(str6);
			}
			while(rs6.next()){
				str7 = rs6.getString(7);
				System.out.println(str7);
			}
			while(rs7.next()){
				str8 = rs7.getString(8);
				System.out.println(str8);
			}
			while(rs8.next()){
				str9 = rs8.getString(9);
				System.out.println(str9);
			}
			while(rs9.next()){
				str10 = rs9.getString(1);
				System.out.println(str10);
			}
			while(rs10.next()){
				str11 = rs10.getString(2);
				System.out.println(str11);
			}
			while(rs11.next()){
				str12 = rs11.getString(3);
				System.out.println(str12);
			}
			while(rs12.next()){
				str13 = rs12.getString(4);
				System.out.println(str13);
			}
			while(rs13.next()){
				str14 = rs13.getString(2);
				System.out.println(str14);
			}
			while(rs14.next()){
				str15 = rs14.getString(3);
				System.out.println(str15);
			}
			
			tfFname.setText("str1");
			tfSname.setText("str2");
			tfMname.setText("str3");
			tfAddress.setText("str4");
			tfNumber.setText("str5");
			tfBday.setText("str6");
			tfAge.setText("str7");

			tfGender.setText("str8");
			tfUser.setText("str14");
			tfPass.setText("str15");
			
			
			tfRoom.setText("str10");
			tfStatus.setText("str9");
			tfShift.setText("str12");
			tfCheck.setText("str9");
		
		}
		catch(SQLException b){
			b.printStackTrace();
		}
		
		*/
		//Buttons
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String strLName = tfSname.getText();
        		String strFName = tfSname.getText();
        		String strMiddle = tfMname.getText();
        		String strAge = tfAge.getText();
        		int intAge = Integer.parseInt(strAge);
        		String strBday = tfBday.getText();
        		String strRoom = tfRoom.getText();
        		String strAddress = tfAddress.getText();
        		String strNumber = tfNumber.getText();
        		String strShift = tfShift.getText();
        		String strCheck = tfCheck.getText();
        		
        		String sql1 = "UPDATE memberpersonalinfo SET LName=?, FNAME=?, MI=?, Address=?, ContactNo=?, Birthday=?, Age=?, HealthCon=?";
        	
        		try{
        			Connection con = DriverManager.getConnection(URL, USER, PASS);
        			PreparedStatement statement = con.prepareStatement(sql1);
        			statement.setString(2, strLName);
        			statement.setString(3, strFName);
        			statement.setString(4, strMiddle);
        			statement.setString(5, strAddress);
        			statement.setString(6, strNumber);
        			statement.setString(7, strBday);
        			statement.setInt(8, intAge);
        			statement.setString(9, strCheck);
        			JOptionPane.showMessageDialog(null, "Save Successful");
        			Member mem = new Member(null);
        			mem.mainMem(null);
        			frame.dispose();
        		}
        		catch(SQLException a){
        			a.printStackTrace();
        		}
        	}
        });
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Member mem = new Member(null);
        		mem.mainMem(null);
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
		panelAdd.add(labelAddress);
		panelAdd.add(tfAddress);
		panelRoom.add(labelRoom);
		panelRoom.add(tfRoom);
		panelShift.add(labelShift);
		panelShift.add(tfShift);
		panelGender.add(labelGender);
		panelGender.add(tfGender);
		panelUser = new JPanel();
		panelUser.setLayout(new GridLayout(1,2));
		panelUser.setBackground(new Color(21,173,228));
		labelUser = new JLabel("Username: ");
		labelUser.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfUser = new JTextField(20);
		tfUser.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panelUser.add(labelUser);
		panelUser.add(tfUser);
		panelText.add(panelUser);
		panelCheck.add(labelCheck);
		panelCheck.add(tfCheck);
	
		panelText.add(panelSname);
		panelText.add(panelFname);
		panelText.add(panelMname);
		panelPass = new JPanel();
		panelPass.setLayout(new GridLayout(1,2));
		panelPass.setBackground(new Color(21,173,228));
		labelPass = new JLabel("Password: ");
		labelPass.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		tfPass = new JPasswordField(20);
		tfPass.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panelPass.add(labelPass);
		panelPass.add(tfPass);
		panelText.add(panelPass);
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
		frame.setSize(807, 560);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
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
