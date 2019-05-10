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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class WalkInUI implements ItemListener{

	JFrame frame = new JFrame("Walk-in");
	Container contentPane;
	String strType;
	int intTemp = 0;
	String[] birthdate = {"01" ,"02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	String[] day = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String[] year = {"1950","1951","1952","1953","1954","1955","1956","1957","1958","1959","1960","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989", "1990","1991","1992","1993","1994","1995","1996","1997","1998", "1999","2000","2001","2002","2003","2004"};
	String[] paymentChoice = {"-", "Regular","VIP"};
	String strYear, strMonth, strDay, strBday;
	
	JComboBox cmbpaymentChoice = new JComboBox(paymentChoice);
	JComboBox cmbBirthdate = new JComboBox(birthdate);
	JComboBox cmbday = new JComboBox(day);
	JComboBox cmbyear = new JComboBox(year);
	
	private static Connection cncDatabase;
	private final static String URL = new String("jdbc:mysql://localhost/AdvProg");
	private final static String USER = new String("root");
	private final static String PASS = new String("youdontknow");
	
	JLabel lblTitle = new JLabel("");
	JLabel lblName = new JLabel("Name: ");
	JLabel lblAge = new JLabel("Age: ");
	JLabel lblAddress = new JLabel("Address: ");	
	JLabel lblContactNo = new JLabel("Contact no: ");	
	JLabel lblPayment = new JLabel("Total Cost: ");	
	JLabel lblDate = new JLabel("Date: ");
	JLabel lblTimeIn = new JLabel("Time in: ");
	JLabel lblRoom = new JLabel("Room Type: ");
	JLabel lblCheck = new JLabel("Answer this Form: ");
	JLabel lblDis = new JLabel("Health Condition: ");
	JLabel lblBirthdate = new JLabel("Date of Birth: ");
	JLabel lblGender = new JLabel("Gender: ");
	
	JTextField txtName = new JTextField(15);
	JTextField txtAge = new JTextField(15);
	JTextField txtAddress = new JTextField(15);
	JTextField txtContactNo = new JTextField(15);
	JTextField txtPayment = new JTextField(15);
	JTextField txtDate = new JTextField(15);
	JTextField txtTimeIn = new JTextField(15);
	JTextField txtDis = new JTextField(15);
	JTextField txtGender = new JTextField(15);
	
	JButton btnCheck = new JButton("Check-Up");
	JButton btnSubmit = new JButton("TimeIn");
	JButton btnSignup = new JButton("Sign-Up");
	JButton btnBack = new JButton("Back");
	JButton btnCancel = new JButton("Exit");
	
	JPanel panelBirthdate = new JPanel();
	JPanel panelTitle = new JPanel();
	JPanel panelName = new JPanel();
	JPanel panelAge = new JPanel();
	JPanel panelAddress = new JPanel();
	JPanel panelContactNo = new JPanel();
	JPanel panelPayment = new JPanel();
	JPanel panelDate = new JPanel();
	JPanel panelGender = new JPanel();
	JPanel panelTimeIn = new JPanel();
	JPanel panelInputs = new JPanel();
	JPanel panelButtons = new JPanel();
	JPanel panelCheck = new JPanel();
	JPanel panelRoom = new JPanel();
	JPanel panelDis = new JPanel();
	
	public static void walkIn(String strCond, String strButton) {
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
					WalkInUI window = new WalkInUI(strCond, strButton);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WalkInUI(String strCond, String strButton) {
		System.out.println(strCond);
		contentPane = frame.getContentPane();
		
		cmbpaymentChoice.addItemListener(this);
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		lblTitle.setIcon(new ImageIcon(imgTitle));
		panelName.setLayout(new GridLayout(1,2));
		panelAge.setLayout(new GridLayout(1,2));
		panelAddress.setLayout(new GridLayout(1,2));
		panelContactNo.setLayout(new GridLayout(1,2));
		panelPayment.setLayout(new GridLayout(1,2));
		panelDate.setLayout(new GridLayout(1,2));
		panelTimeIn.setLayout(new GridLayout(1,2));
		panelCheck.setLayout(new GridLayout(1,2));
		panelRoom.setLayout(new GridLayout(1,2));
		panelInputs.setLayout(new GridLayout(11,1));
		panelDis.setLayout(new GridLayout(1,2));
		panelBirthdate.setLayout(new GridLayout(1,2));
		panelGender.setLayout(new GridLayout(1,2));
		
		lblName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblAge.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblContactNo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblPayment.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblDate.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblTimeIn.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblAddress.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblRoom.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblCheck.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblDis.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblBirthdate.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblGender.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		txtName.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtAge.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtContactNo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtPayment.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtDate.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtAddress.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtDis.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cmbBirthdate.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cmbday.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cmbyear.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		txtGender.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		btnCheck.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnSubmit.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnBack.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnCancel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnSignup.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		cmbBirthdate.addItemListener(this);
		cmbday.addItemListener(this);
		cmbyear.addItemListener(this);
		txtDis.setText(strCond);

		btnSubmit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String strFname = txtName.getText();
        		String strAddress = txtAddress.getText();
        		String strContact = txtContactNo.getText();
        		String Birthdate = strBday;
        		String strAge = txtAge.getText();
        		int intAge = Integer.parseInt(strAge);
        		String strGender = txtGender.getText();
        		String strHealth = txtDis.getText();
        		String strDate = txtDate.getText();
        		String strRoom = strType;
        		int intVisit = 1;
        		String sql = "INSERT INTO  walkin (walkinID, FullName, Address, ContactNo, DateOfBirth, Age, Gender, HeathCon, Date, RoomType, Visits)" + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        			
        		try{
        			
        			Connection con = DriverManager.getConnection(URL, USER, PASS);
        			
        			PreparedStatement statement = con.prepareStatement(sql);
  
        			statement.setInt(1, intTemp);
        			statement.setString(2, strFname);
        			statement.setString(3, strAddress);
        			statement.setString(4, strContact);
        			statement.setString(5, Birthdate);
        			statement.setInt(6, intAge);
        			statement.setString(7, strGender);
        			statement.setString(8, strHealth);
        			statement.setString(9, strDate);
        			statement.setString(10, strRoom);
        			statement.setInt(11, intVisit);
        			statement.executeUpdate();			
        		}
        		catch(SQLException a){
        			a.printStackTrace();
        		}
        		JOptionPane.showMessageDialog(null, "Time-In Successful");
        		UiLogIn gj = new UiLogIn();
        		gj.main(null);
        		frame.dispose();
        	}
        });
		btnSignup.addActionListener(new ActionListener() {
			String strCond = null ,strButton = null;
        	public void actionPerformed(ActionEvent e) {
        		SignUpUI sign = new SignUpUI(strCond, strButton);
        		sign.signUp(strCond, strButton);
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
		
		cmbpaymentChoice.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		
		panelTitle.add(lblTitle);
		panelTitle.setBackground(new Color(242,245,246));
		
		panelName.add(lblName);
		panelName.add(txtName);
		panelName.setBackground(new Color(52,152,246));
		
		panelAge.add(lblAge);
		panelAge.add(txtAge);
		panelAge.setBackground(new Color(52,152,246));
		
		panelAddress.add(lblAddress);
		panelAddress.add(txtAddress);
		panelAddress.setBackground(new Color(52,152,246));
		
		panelContactNo.add(lblContactNo);
		panelContactNo.add(txtContactNo);
		panelContactNo.setBackground(new Color(52,152,246));
		
		panelPayment.add(lblPayment);
		panelPayment.add(txtPayment);
		panelPayment.setBackground(new Color(52,152,246));
		
		panelRoom.add(lblRoom);
		panelRoom.add(cmbpaymentChoice);
		panelRoom.setBackground(new Color(52,152,246));

		panelDate.add(lblDate);
		panelDate.add(txtDate);
		panelDate.setBackground(new Color(52,152,246));
		
		panelGender.add(lblGender);
		panelGender.add(txtGender);
		panelGender.setBackground(new Color(52,152,246));
		
		panelBirthdate.add(lblBirthdate);
		panelBirthdate.add(cmbyear);
		panelBirthdate.add(cmbBirthdate);
		panelBirthdate.add(cmbday);		
		panelBirthdate.setBackground(new Color(52,152,246));
		
		panelDis.add(lblDis);
		panelDis.add(txtDis);
		panelDis.setBackground(new Color(52,152,246));
		
		panelInputs.add(panelName);
		panelInputs.add(panelAddress);
		panelInputs.add(panelContactNo);
		panelInputs.add(panelBirthdate);
		panelInputs.add(panelAge);
		panelInputs.add(panelGender);
		panelInputs.add(panelDis);
		panelInputs.add(panelDate);
		panelInputs.add(panelRoom);
		panelInputs.add(panelPayment);
		panelInputs.setBackground(new Color(52,152,246));
		
		panelButtons.add(btnSubmit);
		panelButtons.add(btnSignup);
		panelButtons.add(btnBack);
		panelButtons.add(btnCancel);
		
		contentPane.add(panelTitle, BorderLayout.NORTH);
		contentPane.add(panelInputs, BorderLayout.CENTER);
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		txtPayment.setEditable(false);
		//txtGender.setEditable(false);
		//txtDis.setEditable(false);
		//btnCheck.setEnabled(false);
		//txtAge.setEditable(false);
		//txtDate.setEditable(false);
		//txtGender.setEditable(false);
		//txtDis.setEditable(false);
		//btnCheck.setEnabled(false);
		contentPane.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 834, 555);
	}
	
	public static int getAge(LocalDate dob){
		LocalDate curDate = LocalDate.now();
		return Period.between(dob, curDate).getYears();
	}
	
	public void itemStateChanged(ItemEvent e) {
	
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
						//txtGender.setEditable(true);
						//txtDis.setEditable(true);
						//btnCheck.setEnabled(true);
					}
					else{
						//txtGender.setEditable(false);
						//txtDis.setEditable(false);
						//btnCheck.setEnabled(false);
						JOptionPane.showMessageDialog(null, "You're not recommended to perform any kind of gym activity", "Warning", JOptionPane.WARNING_MESSAGE );
					}
				}
			}
		}
		
		if(cmbpaymentChoice.getSelectedIndex() == 1){
			txtPayment.setText("100");
			strType = (String) cmbpaymentChoice.getSelectedItem();
		}
		if(cmbpaymentChoice.getSelectedIndex() == 2){
			txtPayment.setText("200");
			strType = (String) cmbpaymentChoice.getSelectedItem();
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