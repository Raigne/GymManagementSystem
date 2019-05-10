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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Member {

	private JFrame frame;
	private JPanel panelColor;
	private JPanel panelBtn;
	private JPanel panelLabel;
	private JPanel panelName;
	private JLabel labelTitle;
	private JLabel labelName;
	private JButton btnTimeIn;
	private JButton btnTimeOut;
	private JButton btnInbox;
	private JButton btnPayment;
	private JButton btnExit;
	
	String strTemp = null;

	String strHold = null;
	ArrayList<String> user = new ArrayList<String>();
	ArrayList<Integer> visit = new ArrayList<Integer>();
	ArrayList<Integer> id = new ArrayList<Integer>();
	
	private static Connection cncDatabase;
	private final static String URL = new String("jdbc:mysql://localhost/AdvProg");
	private final static String USER = new String("root");
	private final static String PASS = new String("youdontknow");
	
	public static void mainMem(String strCUser) {
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
					Member window = new Member(strCUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Member(String strCUser) {
		frame = new JFrame("Member");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Panel
		panelLabel = new JPanel();
		panelLabel.setBackground(new Color(242,245,246));
		panelName = new JPanel();
		panelName.setLayout(new GridLayout(1,2));
		panelName.setBackground(new Color(21,173,228));
		panelBtn = new JPanel();
		panelBtn.setLayout(new GridLayout(5,1));
		panelBtn.setBackground(new Color(21,173,228));
		//Label
		labelTitle = new JLabel("");
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		labelTitle.setIcon(new ImageIcon(imgTitle));
		labelTitle.setFont(new Font("Century Gothic", Font.PLAIN, 50));
		labelName = new JLabel("Welcome"+ " " +strCUser);
		labelName.setHorizontalAlignment(SwingConstants.LEFT);
		labelName.setFont(new Font("Century Gothic", Font.ITALIC, 25));
		//Button
		btnTimeIn = new JButton("Time-In");
		btnTimeIn.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		btnTimeIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int intCtr =0;
        		String strUser = null;
        		String strRank = null;
        		String strStats = "Valid";
        		String strShift = null;
        		int intMem;
        		int intVisit = 0;
        		int intNew = 0;
        		String sql = "SELECT * FROM memberaccountinfo";
        		String sql1 = "SELECT * FROM membershipinfo";
        		
        		try{
        			Connection con = DriverManager.getConnection(URL, USER, PASS);
        			//GET PREPARED STMT
        			Statement s1 = con.prepareStatement(sql);
        			ResultSet rs1 = s1.executeQuery(sql);
        			Statement s2 = con.prepareStatement(sql1);
        			ResultSet rs2 = s2.executeQuery(sql1);
        			Statement s4 = con.prepareStatement(sql1);
        			ResultSet rs4 = s4.executeQuery(sql1);
        			
        			while(rs1.next()){
        				strUser = rs1.getString(2);
        				user.add(strUser);
        				intMem = rs1.getInt(1);
        				id.add(intMem);
        			}
        			while(rs2.next()){
        				strShift = rs2.getString(3);
        			}
        			while(rs4.next()){
        				intVisit = rs4.getInt(4);
        				visit.add(intVisit);
        			}
        			for(intCtr = 0; intCtr < user.size(); intCtr++){
        				if(user.get(intCtr).equalsIgnoreCase(strCUser)){
            				String sql2 = "UPDATE membershipinfo SET memStats = ?, memRank = ?, Shift = ?,  Visits= ? WHERE MemberID = " + id.get(intCtr);
            				PreparedStatement s3 = con.prepareStatement(sql2);
            				intNew = visit.get(intCtr) + 1;
            				if(intNew < 10){
            					strRank = "Normal";
            					s3.setString(1, strStats);
            					s3.setString(2, strRank);
                    			s3.setString(3, strShift);
                				s3.setInt(4, intNew);
                    			s3.executeUpdate();
            				}
            				if(intNew > 10){
            					strRank = "Bronze";
            					s3.setString(1, strStats);
            					s3.setString(2, strRank);
            					s3.setString(3, strShift);
                				s3.setInt(4, intNew);
                    			s3.executeUpdate();
            				}
            				if(intNew >= 15){
            					strRank = "Silver";
                    			s3.setString(1, strStats);
                    			s3.setString(2, strRank);
                    			s3.setString(3, strShift);
                				s3.setInt(4, intNew);
                    			s3.executeUpdate();
            				}
            				if(intNew >= 20){
            					strRank = "Gold";
                    			s3.setString(1, strStats);
                    			s3.setString(2, strRank);
                    			s3.setString(3, strShift);
                				s3.setInt(4, intNew);
                    			s3.executeUpdate();
            				}
            				
            				if(intNew ==10){
            					JOptionPane.showMessageDialog(null, "Your membership has been upgraded to Bronze");
            				}
            				if(intNew ==15){
            					JOptionPane.showMessageDialog(null, "Your membership has been upgraded to Silver");
            				}
            				if(intNew ==20){
            					JOptionPane.showMessageDialog(null, "Your membership has been upgraded to Gold");
            				}
            				
            				int rowsUpdated = s3.executeUpdate();
            				if (rowsUpdated > 0) {
            				    System.out.println("An existing user was updated successfully!");
            				}
            				System.out.println(intNew);
            			}
        			}
        			
        		}
        		catch(SQLException g){
        			g.printStackTrace();
        		}
        		JOptionPane.showMessageDialog(null, "Time-In success");
        		frame.dispose();
        		UiLogIn log = new UiLogIn();
        		log.main(null);
        	}
        });
		btnTimeOut = new JButton("Time-Out");
		btnTimeOut.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		btnTimeOut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, "Time-Out success");
        		UiLogIn log = new UiLogIn();
        		log.main(null);
        		frame.dispose();
        	}
        });
		btnInbox = new JButton("Inbox");
		btnInbox.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		btnInbox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		InboxUI box = new InboxUI(strCUser);
        		box.inbox(strCUser);
        		frame.dispose();
        	}
        });
		btnPayment = new JButton("Renew Membership");
		btnPayment.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		btnPayment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Renew rnew = new Renew(strCUser);
        		rnew.renew(strCUser);
        		frame.dispose();
        	}
        });
		btnExit = new JButton("Log-Out");
		btnExit.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UiLogIn log = new UiLogIn();
        		log.main(null);
        		frame.dispose();
        	}
        });
		//add to panel
		panelLabel.add(labelTitle);
		panelName.add(labelName);
		panelBtn.add(btnTimeIn);
		panelBtn.add(btnTimeOut);
		panelBtn.add(btnInbox);
		panelBtn.add(btnPayment);
		panelBtn.add(btnExit);
		//add to frame
		frame.getContentPane().add(panelLabel, "North");
		frame.getContentPane().add(panelName);
		frame.getContentPane().add(panelBtn, "South");
		frame.setSize(750, 450);
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
