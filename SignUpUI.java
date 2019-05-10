package GymInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Period;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpUI  implements ItemListener{

	JFrame frame = new JFrame("Sign up");
	Container contentPane;
	
	String[] birthdate = {"01" ,"02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	String[] day = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String[] year = {"1950","1951","1952","1953","1954","1955","1956","1957","1958","1959","1960","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989", "1990","1991","1992","1993","1994","1995","1996","1997","1998", "1999","2000","2001","2002","2003","2004"};
	String[] pay = {"Full payment", "Half payment"};
	String[] monthlyPay = {"3 months", "6 months", "12 months"};
	String[] paymentChoice = {"Regular","VIP"};
	String[] shift = {"Morning","Night"};
	String strYear, strMonth, strDay, strBday, strRoom, strType, strPay, strNull1=null, strMood;
	String strStatus = "Valid";
	String strRank = "Normal";
	int intTemp = 0;
	
	JComboBox cmbBirthdate = new JComboBox(birthdate);
	JComboBox cmbday = new JComboBox(day);
	JComboBox cmbyear = new JComboBox(year);
	JComboBox cmbpay = new JComboBox(pay);
	JComboBox cmbmonthlyPay = new JComboBox(monthlyPay);
	JComboBox cmbpaymentChoice = new JComboBox(paymentChoice);
	JComboBox jcShift = new JComboBox(shift);
	
	JLabel lblTitle = new JLabel("");
	JLabel lblFirstName = new JLabel("First Name: ");
	JLabel lblMI = new JLabel("M.I.: ");
	JLabel lblLastName = new JLabel("Last Name: ");
	JLabel lblUsername = new JLabel("Username: ");
	JLabel lblPassword = new JLabel("Password: ");
	JLabel lblBirthdate = new JLabel("Date of Birth: ");
	JLabel lblAge = new JLabel("Age: ");
	JLabel lblAddress = new JLabel("Address: ");	
	JLabel lblContactNo = new JLabel("Contact no: ");	
	JLabel lblRoom = new JLabel("Room Type: ");
	JLabel lblMemberShip = new JLabel("Membership: ");
	JLabel lblPayment = new JLabel("Payment: ");
	JLabel lblCost = new JLabel("Total Cost: ");
	JLabel lblShift = new JLabel("Shift: ");
	JLabel lblCheck = new JLabel("Answer this Form: ");
	JLabel lblDis = new JLabel("Health Condition: ");
	JLabel lblGender = new JLabel("Gender");
	
	JTextField txtFirstName = new JTextField(15);
	JTextField txtMI = new JTextField(15);
	JTextField txtLastName = new JTextField(15);
	JTextField txtUsername = new JTextField(15);
	JTextField txtPassword = new JPasswordField(15);
	JTextField txtAge = new JTextField(15);
	JTextField txtAddress = new JTextField(15);
	JTextField txtContactNo = new JTextField(15);
	JTextField txtPayment = new JTextField(15);
	JTextField txtDate = new JTextField(15);
	JTextField txtCost = new JTextField(15);
	JTextField txtDis = new JTextField(15);
	JTextField txtGender = new JTextField(15);
	
	JButton btnCheck = new JButton("Check-Up");
	JButton btnSubmit = new JButton("Sign-Up");
	JButton btnBack = new JButton("Back");
	JButton btnCancel = new JButton("Exit");
	
	JPanel panelTitle = new JPanel();
	JPanel panelFirstName = new JPanel();
	JPanel panelMI = new JPanel();
	JPanel panelLastName = new JPanel();
	JPanel panelUsername = new JPanel();
	JPanel panelPassword = new JPanel();
	JPanel panelBirthdate = new JPanel();
	JPanel panelAge = new JPanel();
	JPanel panelAddress = new JPanel();
	JPanel panelContactNo = new JPanel();
	JPanel panelPaymentChoices = new JPanel();
	JPanel panelInputs = new JPanel();
	JPanel panelButtons = new JPanel();
	JPanel panelCost = new JPanel();
	JPanel panelShift = new JPanel();
	JPanel panelDis = new JPanel();
	JPanel panelCheck = new JPanel();
	JPanel panelGender = new JPanel();
	
	
	private static Connection cncDatabase;
	private final static String URL = new String("jdbc:mysql://localhost/AdvProg");
	private final static String USER = new String("root");
	private final static String PASS = new String("youdontknow");
	
	public static void signUp(String strCond ,String strButton) {

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
					SignUpUI window = new SignUpUI(strCond, strButton);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUpUI(String strCond, String strButton) {
		System.out.println(strButton + strCond);
		contentPane = frame.getContentPane();
		
		cmbday.addItemListener(this);
		cmbBirthdate.addItemListener(this);
		cmbyear.addItemListener(this);
		cmbpaymentChoice.addItemListener(this);
		cmbpay.addItemListener(this);
		cmbmonthlyPay.addItemListener(this);
		jcShift.addItemListener(this);
		
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		lblTitle.setIcon(new ImageIcon(imgTitle));
		
		panelFirstName.setLayout(new GridLayout(1,2));
		panelMI.setLayout(new GridLayout(1,2));
		panelLastName.setLayout(new GridLayout(1,2));
		panelUsername.setLayout(new GridLayout(1,2));
		panelPassword.setLayout(new GridLayout(1,2));
		panelBirthdate.setLayout(new GridLayout(1,2));
		panelAge.setLayout(new GridLayout(2,2));
		panelAddress.setLayout(new GridLayout(1,2));
		panelContactNo.setLayout(new GridLayout(1,2));
		panelPaymentChoices.setLayout(new GridLayout(4,2));
		panelLastName.setLayout(new GridLayout(1,2));
		panelCost.setLayout(new GridLayout(1,2));
		panelDis.setLayout(new GridLayout(1,2));
		panelCheck.setLayout(new GridLayout(1,2));
		
		lblFirstName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblMI.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblLastName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblUsername.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblPassword.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblBirthdate.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblAge.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblAddress.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblContactNo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblPayment.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblCost.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblRoom.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblMemberShip.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblShift.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblDis.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblCheck.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblGender.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		txtFirstName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtMI.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtLastName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtUsername.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtPassword.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtAge.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtAddress.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtContactNo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtPayment.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtDate.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtCost.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtDis.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtGender.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		cmbBirthdate.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cmbday.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cmbyear.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cmbpay.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cmbmonthlyPay.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cmbpaymentChoice.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		jcShift.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		btnCheck.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnSubmit.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnCancel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtDis.setText(strCond);
		btnSubmit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String strUser = txtUsername.getText();
        		String strPass = txtPassword.getText();
        		String strLName = txtLastName.getText();
        		String strFName = txtFirstName.getText();
        		String strMiddle = txtMI.getText();
        		String strBirthdate = strBday;
        		String strAge = txtAge.getText();
        		int intAge = Integer.parseInt(strAge);
        		String strContact = txtContactNo.getText();
        		String strAddress = txtAddress.getText();
        		String strHealth = txtDis.getText();
        		String strGender = txtGender.getText();
        		String strRType = strRoom;
        		String strMembership = strType;
        		String strPayment = strPay;
        		int intVisit = 1;
        		String strShift = strMood;
        		double intCost = Double.parseDouble(txtCost.getText());
        		double intHalfPay = 0;
        		double intBal =0;
        		String sql1 = "INSERT INTO  memberaccountinfo (MemberID, Username, Password)" + " VALUES (?, ?, ?)";
        		String sql2 = "INSERT INTO  memberpersonalinfo (LName, FNAME, MI, Address, ContactNo, Birthday, Age, HealthCon, Gender, MemberID)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        		String sql3 = "INSERT INTO  membershipinfo (MemStats, MemRank, Shift, Visits, MemberID)" + " VALUES (?,?,?,?,?)";
        		String sql4 = "INSERT INTO  sessioninfo (Room, Duration, PaymentType, MemberID, CPayment, Payment, Balance)" + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        		QRCodeTry qr = new QRCodeTry();
        		
        		if(strPay.equalsIgnoreCase("Half payment")){
        			intCost = Double.parseDouble(txtCost.getText());
            		intHalfPay = intCost / 2;
            		intBal = intCost - intHalfPay;
            		System.out.println(intCost + intHalfPay + intBal);
            		
            		
            		try{
            			
            			Connection con = DriverManager.getConnection(URL, USER, PASS);
            			
            			PreparedStatement statement1 = con.prepareStatement(sql1);
            			PreparedStatement statement2 = con.prepareStatement(sql2);
            			PreparedStatement statement3 = con.prepareStatement(sql3);
            			PreparedStatement statement4 = con.prepareStatement(sql4);
            			
            			statement1.setInt(1, intTemp);
            			statement1.setString(2, strUser);
            			statement1.setString(3, strPass);
            			statement1.executeUpdate();
            			
            			statement2.setString(1, strLName);
            			statement2.setString(2, strFName);
            			statement2.setString(3, strMiddle);
            			statement2.setString(4, strAddress);
            			statement2.setString(5, strContact);
            			statement2.setString(6, strBirthdate);
            			statement2.setInt(7, intAge);
            			statement2.setString(8, strHealth);
            			statement2.setString(9, strGender);
            			statement2.setInt(10, intTemp);
            			statement2.executeUpdate();
            			
            			statement3.setString(1, strStatus);
            			statement3.setString(2, strRank);
            			statement3.setString(3, strShift);
            			statement3.setInt(4, intVisit);
            			statement3.setInt(5, intTemp);
            			statement3.executeUpdate();
            			
            			statement4.setString(1, strRType);
            			statement4.setString(2, strMembership);
            			statement4.setString(3, strPayment);
            			statement4.setInt(4, intTemp);
            			statement4.setDouble(5, intHalfPay);
            			statement4.setDouble(6, intCost);
            			statement4.setDouble(7, intBal);
            			statement4.executeUpdate();
            			
            			qr.main(strUser, strPass);
            		}
            		catch(SQLException a){
            			a.printStackTrace();
            		}
        		}
        		else{
        			
        			intCost = Double.parseDouble(txtCost.getText());
            		intHalfPay = 0;
            		intBal =0;
            		
            		try{
            			
            			Connection con = DriverManager.getConnection(URL, USER, PASS);
            			
            			PreparedStatement statement1 = con.prepareStatement(sql1);
            			PreparedStatement statement2 = con.prepareStatement(sql2);
            			PreparedStatement statement3 = con.prepareStatement(sql3);
            			PreparedStatement statement4 = con.prepareStatement(sql4);
            			
            			statement1.setInt(1, intTemp);
            			statement1.setString(2, strUser);
            			statement1.setString(3, strPass);
            			statement1.executeUpdate();
            			
            			statement2.setString(1, strLName);
            			statement2.setString(2, strFName);
            			statement2.setString(3, strMiddle);
            			statement2.setString(4, strAddress);
            			statement2.setString(5, strContact);
            			statement2.setString(6, strBirthdate);
            			statement2.setInt(7, intAge);
            			statement2.setString(8, strHealth);
            			statement2.setString(9, strGender);
            			statement2.setInt(10, intTemp);
            			statement2.executeUpdate();
            			
            			statement3.setString(1, strStatus);
            			statement3.setString(2, strRank);
            			statement3.setString(3, strShift);
            			statement3.setInt(4, intVisit);
            			statement3.setInt(5, intTemp);
            			statement3.executeUpdate();
            			
            			statement4.setString(1, strRType);
            			statement4.setString(2, strMembership);
            			statement4.setString(3, strPayment);
            			statement4.setInt(4, intTemp);
            			statement4.setDouble(5, intHalfPay);
            			statement4.setDouble(6, intCost);
            			statement4.setDouble(7, intBal);
            			statement4.executeUpdate();
            			qr.main(strUser, strPass);
        		}
            	catch(SQLException a){
            		a.printStackTrace();
            	}
        		}
        		
        		UiLogIn gj = new UiLogIn();
        		gj.main(null);
        		frame.dispose();
        	}
        });
		btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UiLogIn gj = new UiLogIn();
        		gj.main(null);
        		frame.dispose();
        	}
        });
		btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });

		panelTitle.add(lblTitle);
		panelTitle.setBackground(new Color(242,245,246));
		
		panelFirstName.add(lblFirstName);
		panelFirstName.add(txtFirstName);
		panelFirstName.setBackground(new Color(21,173,228));
		
		panelMI.add(lblMI);
		panelMI.add(txtMI);
		panelMI.setBackground(new Color(21,173,228));
		
		panelLastName.add(lblLastName);
		panelLastName.add(txtLastName);
		panelLastName.setBackground(new Color(21,173,228));
		
		panelUsername.add(lblUsername);
		panelUsername.add(txtUsername);
		panelUsername.setBackground(new Color(21,173,228));
		
		panelPassword.add(lblPassword);
		panelPassword.add(txtPassword);
		panelPassword.setBackground(new Color(21,173,228));
		
		panelBirthdate.add(lblBirthdate);
		panelBirthdate.add(cmbyear);
		panelBirthdate.add(cmbBirthdate);
		panelBirthdate.add(cmbday);		
		panelBirthdate.setBackground(new Color(21,173,228));
		
		panelAge.add(lblAge);
		panelAge.add(txtAge);
		panelAge.add(lblGender);
		panelAge.add(txtGender);
		panelAge.setBackground(new Color(21,173,228));
		
		panelAddress.add(lblAddress);
		panelAddress.add(txtAddress);
		panelAddress.setBackground(new Color(21,173,228));
		
		panelContactNo.add(lblContactNo);
		panelContactNo.add(txtContactNo);
		panelContactNo.setBackground(new Color(21,173,228));
		
		panelCost.add(lblCost);
		panelCost.add(txtCost);
		panelCost.setBackground(new Color(21,173,228));
		
		panelPaymentChoices.add(lblShift);
		panelPaymentChoices.add(jcShift);
		panelPaymentChoices.add(lblRoom);
		panelPaymentChoices.add(cmbpaymentChoice);
		panelPaymentChoices.add(lblMemberShip);
		panelPaymentChoices.add(cmbmonthlyPay);
		panelPaymentChoices.add(lblPayment);
		panelPaymentChoices.add(cmbpay);
		panelPaymentChoices.setBackground(new Color(21,173,228));
		
		panelDis.add(lblDis);
		panelDis.add(txtDis);
		panelDis.setBackground(new Color(21,173,228));
		
		panelInputs.add(panelFirstName);
		panelInputs.add(panelMI);
		panelInputs.add(panelLastName);
		panelInputs.add(panelUsername);
		panelInputs.add(panelPassword);
		panelInputs.add(panelAddress);
		panelInputs.add(panelContactNo);
		panelInputs.add(panelBirthdate);
		panelInputs.add(panelAge);
		panelInputs.add(panelDis);
	
		panelInputs.add(panelCost);
		panelInputs.add(panelPaymentChoices);
		panelInputs.setBackground(new Color(21,173,228));
		
		panelButtons.add(btnSubmit);
		panelButtons.add(btnBack);
		panelButtons.add(btnCancel);
		
		contentPane.add(panelTitle, BorderLayout.NORTH);
		contentPane.add(panelInputs, BorderLayout.CENTER);
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		
		txtAge.setEditable(false);
		txtGender.setEditable(false);
		txtDis.setEditable(false);
		txtCost.setEditable(false);
		btnCheck.setEnabled(false);
		//jcShift.setEnabled(false);
		//cmbpaymentChoice.setEnabled(false);
		//cmbmonthlyPay.setEnabled(false);
		//cmbpay.setEnabled(false);
		frame.setBounds(100, 100, 633, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	
	public static int getAge(LocalDate dob){
		LocalDate curDate = LocalDate.now();
		return Period.between(dob, curDate).getYears();
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			strMood = (String) jcShift.getSelectedItem();
		}
		
		if(e.getStateChange() == ItemEvent.SELECTED){
			strYear = (String) cmbyear.getSelectedItem();
			if(e.getStateChange() == ItemEvent.SELECTED){
				strMonth = (String) cmbBirthdate.getSelectedItem();
				if(e.getStateChange() == ItemEvent.SELECTED){
					strDay = (String) cmbday.getSelectedItem();
					strBday = strYear + "-" + strMonth + "-" + strDay ;
					LocalDate dob = LocalDate.parse(strBday);
					txtAge.setText("" + getAge(dob));
					int intAge = Integer.parseInt(txtAge.getText());
					if(intAge >= 18 && intAge <= 50){
						txtGender.setEditable(true);
						txtDis.setEditable(true);
						btnCheck.setEnabled(true);
					}
					else{
						txtGender.setEditable(false);
						txtDis.setEditable(false);
						btnCheck.setEnabled(false);
						JOptionPane.showMessageDialog(null, "You're not recommended to perform any kind of gym activity", "Warning", JOptionPane.WARNING_MESSAGE );
					}
				}
			}
		}
		
		if(e.getStateChange() == ItemEvent.SELECTED){
			strRoom = (String) cmbpaymentChoice.getSelectedItem();
			if(strRoom.equalsIgnoreCase("Regular")){
				txtCost.setText("100");
				if(e.getStateChange() == ItemEvent.SELECTED){
					strType = (String) cmbmonthlyPay.getSelectedItem();
					if(strType.equalsIgnoreCase("3 months")){
						int intProd, intNum;
						intNum = Integer.parseInt(txtCost.getText());
						intProd = (intNum - 15) * 90;
						txtCost.setText(""+intProd);
						if(e.getStateChange() == ItemEvent.SELECTED){
							strPay = (String) cmbpay.getSelectedItem();
							if(strPay.equalsIgnoreCase("Full payment")){
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.2);
								txtCost.setText(""+dblFinal);
							}
							else{
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.1);
								txtCost.setText(""+dblFinal);
							}
						}
					}
					if(strType.equalsIgnoreCase("6 months")){
						int intProd, intNum;
						intNum = Integer.parseInt(txtCost.getText());
						intProd = (intNum - 30) * 180;
						txtCost.setText(""+intProd);
						if(e.getStateChange() == ItemEvent.SELECTED){
							strPay = (String) cmbpay.getSelectedItem();
							if(strPay.equalsIgnoreCase("Full payment")){
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.2);
								txtCost.setText(""+dblFinal);
							}
							else{
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.1);
								txtCost.setText(""+dblFinal);
							}
						}
					}
					if(strType.equalsIgnoreCase("12 months")){
						int intProd, intNum;
						intNum = Integer.parseInt(txtCost.getText());
						intProd = (intNum - 50) * 365;
						txtCost.setText(""+intProd);
						if(e.getStateChange() == ItemEvent.SELECTED){
							strPay = (String) cmbpay.getSelectedItem();
							if(strPay.equalsIgnoreCase("Full payment")){
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.2);
								txtCost.setText(""+dblFinal);
							}
							else{
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.1);
								txtCost.setText(""+dblFinal);
							}
						}
					}
				}
			}
			else{
				txtCost.setText("200");
				if(e.getStateChange() == ItemEvent.SELECTED){
					strType = (String) cmbmonthlyPay.getSelectedItem();
					if(strType.equalsIgnoreCase("3 months")){
						int intProd, intNum;
						intNum = Integer.parseInt(txtCost.getText());
						intProd = (intNum - 15) * 90;
						txtCost.setText(""+intProd);
						if(e.getStateChange() == ItemEvent.SELECTED){
							strPay = (String) cmbpay.getSelectedItem();
							if(strPay.equalsIgnoreCase("Full payment")){
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.2);
								txtCost.setText(""+dblFinal);
							}
							else{
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.1);
								txtCost.setText(""+dblFinal);
							}
						}
					}
					if(strType.equalsIgnoreCase("6 months")){
						int intProd, intNum;
						intNum = Integer.parseInt(txtCost.getText());
						intProd = (intNum - 30) * 180;
						txtCost.setText(""+intProd);
						if(e.getStateChange() == ItemEvent.SELECTED){
							strPay = (String) cmbpay.getSelectedItem();
							if(strPay.equalsIgnoreCase("Full payment")){
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.2);
								txtCost.setText(""+dblFinal);
							}
							else{
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.1);
								txtCost.setText(""+dblFinal);
							}
						}
					}
					if(strType.equalsIgnoreCase("12 months")){
						int intProd, intNum;
						intNum = Integer.parseInt(txtCost.getText());
						intProd = (intNum - 50) * 365;
						txtCost.setText(""+intProd);
						if(e.getStateChange() == ItemEvent.SELECTED){
							strPay = (String) cmbpay.getSelectedItem();
							if(strPay.equalsIgnoreCase("Full payment")){
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.2);
								txtCost.setText(""+dblFinal);
							}
							else{
								int intNum1;
								Double dblFinal;
								intNum1 = Integer.parseInt(txtCost.getText());
								dblFinal = intNum1 - (intNum1 * 0.1);
								txtCost.setText(""+dblFinal);
							}
						}
					}
				}
			}
		}
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