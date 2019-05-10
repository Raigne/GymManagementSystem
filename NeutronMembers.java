package GymInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class NeutronMembers {

	private JFrame frame;
	private JPanel panelTitle;
	private JPanel panelText;
	private JPanel panelButton; 
	private JTable tblSQL;
	private JLabel labelTitle;
	private JLabel labelMember;
	private JScrollPane scroll;
	private JButton btnBack;
	private static Connection cncDatabase;
	private final static String URL = new String("jdbc:mysql://localhost/AdvProg");
	private final static String USER = new String("root");
	private final static String PASS = new String("youdontknow");
	String strContact,strUser, strPass, strFname, strLname, strMI, strBdate, strAddress, strHealth, strGender, strMem, strRank, strShift, strRoom, strDuration, strPayType;
	int intId, intVisit, intAge, intCtr =0;
	Double dblCpay, dblPay, dblBal;
	ArrayList<String> last = new ArrayList<String>();
	ArrayList<String> first = new ArrayList<String>();
	ArrayList<String> mi = new ArrayList<String>();
	ArrayList<String> bday = new ArrayList<String>();
	ArrayList<String> add = new ArrayList<String>();
	ArrayList<String> health = new ArrayList<String>();
	ArrayList<String> gender = new ArrayList<String>();
	ArrayList<String> mem = new ArrayList<String>();
	ArrayList<String> user = new ArrayList<String>();
	ArrayList<String> pass = new ArrayList<String>();
	ArrayList<String> rank = new ArrayList<String>();
	ArrayList<String> shift = new ArrayList<String>();
	ArrayList<String> room = new ArrayList<String>();
	ArrayList<String> duration = new ArrayList<String>();
	ArrayList<String> paytype = new ArrayList<String>();
	ArrayList<String> contact = new ArrayList<String>();
	ArrayList<Integer> id = new ArrayList<Integer>();
	ArrayList<Integer> visit = new ArrayList<Integer>();
	ArrayList<Integer> age = new ArrayList<Integer>();
	ArrayList<Double> cpay = new ArrayList<Double>();
	ArrayList<Double> pay = new ArrayList<Double>();
	ArrayList<Double> bal = new ArrayList<Double>();
	String columnName [] = {"MemberID","UserName","Password","Surname", "FirstName", "M.I.", "Address", "Contact No.", "Birthdate", "Age", "HealthCondition", "Gender", "Status", "Rank", "Shift", "Visit", "Room", "Duration","Payment Type", "Current Payment", "Total Cost", "Balance"};

	public static void btnMem() {
		try{
			cncDatabase = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("CONNECTED");
		}
		catch(SQLException objSqlException){
			objSqlException.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NeutronMembers window = new NeutronMembers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NeutronMembers() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Panel
		panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout());
		panelTitle = new JPanel();
		panelTitle.setLayout(new FlowLayout());
		panelTitle.setBackground(new Color(242,245,246));
		panelText = new JPanel();
		panelText.setLayout(new GridLayout(1,2));
		panelText.setBackground(new Color(21,173,228));
		//Label
		labelTitle = new JLabel("");
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		labelTitle.setIcon(new ImageIcon(imgTitle));
		labelMember = new JLabel("Neutron Members: ");
		labelMember.setFont(new Font("Century Gothic", Font.BOLD, 32));
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnName);
		tblSQL = new JTable();
		tblSQL.setModel(model);
		tblSQL.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblSQL.setFillsViewportHeight(true);
		scroll = new JScrollPane(tblSQL);
		
		//SQL
		String sql1 = "SELECT * FROM memberpersonalinfo";
		String sql2 = "SELECT * FROM memberaccountinfo";
		String sql3 = "SELECT * FROM membershipinfo";
		String sql4 = "SELECT * FROM sessioninfo";
		try{
			//Get Connection
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			//GET PREPARED STMT
			Statement s = con.prepareStatement(sql1);
			ResultSet rs = s.executeQuery(sql1);
			Statement s2 = con.prepareStatement(sql2);
			ResultSet rs2 = s2.executeQuery(sql2);
			Statement s3 = con.prepareStatement(sql3);
			ResultSet rs3 = s3.executeQuery(sql3);
			Statement s4 = con.prepareStatement(sql4);
			ResultSet rs4 = s4.executeQuery(sql4);
			
			while(rs.next()){
				strLname = rs.getString(1);
				last.add(strLname);
				strFname = rs.getString(2);
				first.add(strFname);
				strMI = rs.getString(3);
				mi.add(strMI);
				strAddress = rs.getString(4);
				add.add(strAddress);
				strContact = rs.getString(5);
				contact.add(strContact);
				strBdate = rs.getString(6);
				bday.add(strBdate);
				intAge = rs.getInt(7);
				age.add(intAge);
				strHealth = rs.getString(8);
				health.add(strHealth);
				strGender = rs.getString(9);
				gender.add(strGender);
			}
			while(rs2.next()){
				intId = rs2.getInt(1);
				id.add(intId);
				strUser = rs2.getString(2);
				user.add(strUser);
				strPass = rs2.getString(3);
				pass.add(strPass);
			}
			while(rs3.next()){
				strMem = rs3.getString(1);
				mem.add(strMem);
				strRank = rs3.getString(2);
				rank.add(strRank);
				strShift = rs3.getString(3);
				shift.add(strShift);
				intVisit = rs3.getInt(4);
				visit.add(intVisit);
			}
			while(rs4.next()){
				strRoom = rs4.getString(1);
				room.add(strRoom);
				strDuration = rs4.getString(2);
				duration.add(strDuration);
				strPayType = rs4.getString(3);
				paytype.add(strPayType);
				dblCpay = rs4.getDouble(5);
				cpay.add(dblCpay);
				dblPay = rs4.getDouble(6);
				pay.add(dblPay);
				dblBal = rs4.getDouble(7);
				bal.add(dblBal);
			}
			int intTemp = 0;
			intTemp=id.size()-1;
			while((intCtr-1)!=intTemp){	
				model.addRow(new Object[] {id.get(intCtr), user.get(intCtr), pass.get(intCtr), last.get(intCtr), first.get(intCtr), mi.get(intCtr), add.get(intCtr), contact.get(intCtr), bday.get(intCtr), age.get(intCtr),health.get(intCtr),gender.get(intCtr),mem.get(intCtr),rank.get(intCtr),shift.get(intCtr),visit.get(intCtr),room.get(intCtr),duration.get(intCtr),paytype.get(intCtr),cpay.get(intCtr), pay.get(intCtr), bal.get(intCtr)});	
				intCtr++;
			}
		}
		catch(SQLException b){
			b.printStackTrace();
		}
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
		panelTitle.add(labelTitle);
		panelText.add(labelMember);
		panelText.add(scroll);
		panelButton.add(btnBack);
		//add to frame
		frame.getContentPane().add(panelTitle,"North");
		frame.getContentPane().add(panelText);
		frame.getContentPane().add(panelButton, "South");
		frame.setSize(605, 570);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
}
