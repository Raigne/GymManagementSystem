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
import javax.swing.JTextArea;

public class InboxUI {

	private JFrame frame;
	private JPanel panelText;
	private JPanel panelBtn;
	private JPanel panelLabel;
	private JLabel labelTitle;
	private JLabel labelInbox;
	private JScrollPane scroll;
	private JTextArea taInbox;
	private JButton btnOk;
	int intCtr;
	String strUser;
	String strInbox;
	ArrayList<String> user = new ArrayList<String>();
	ArrayList<String> inbox = new ArrayList<String>();
	
	private static Connection cncDatabase;
	private final static String URL = new String("jdbc:mysql://localhost/AdvProg");
	private final static String USER = new String("root");
	private final static String PASS = new String("youdontknow");

	public static void inbox(String strCUser) {
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
					InboxUI window = new InboxUI(strCUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InboxUI(String strCUser) {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//Panel
		panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout());
		panelLabel = new JPanel();
		panelLabel.setLayout(new FlowLayout());
		panelLabel.setBackground(new Color(242,245,246));
		panelText = new JPanel();
		panelText.setLayout(new FlowLayout());
		panelText.setBackground(new Color(21,173,228));
		//Label
		labelTitle = new JLabel("");
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		labelTitle.setIcon(new ImageIcon(imgTitle));
		labelInbox = new JLabel("Message: ");
		labelInbox.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		//TextArea and Scroll
		taInbox = new JTextArea(18,40);
		taInbox.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		scroll = new JScrollPane(taInbox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		String sql = "SELECT * FROM memberaccountinfo";
		try{
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			Statement s1 = con.prepareStatement(sql);
			ResultSet rs1 = s1.executeQuery(sql);
			while(rs1.next()){
				strUser = rs1.getString(2);
				user.add(strUser);
				strInbox = rs1.getString(4);
				inbox.add(strInbox);
			}
			for(intCtr = 0; intCtr < inbox.size(); intCtr++){
				if(strCUser.equalsIgnoreCase(user.get(intCtr))){
					taInbox.setText(inbox.get(intCtr));
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		//Button
		btnOk = new JButton("Ok");
		btnOk.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnOk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Member mem = new Member(strCUser);
        		mem.mainMem(strCUser);
        		frame.dispose();
        	}
        });
		//Add to panel
		panelLabel.add(labelTitle);
		panelText.add(labelInbox);
		panelText.add(scroll);
		panelBtn.add(btnOk);
		//add to frame
		frame.getContentPane().add(panelLabel,"North");
		frame.getContentPane().add(panelText);
		frame.getContentPane().add(panelBtn, "South");
		frame.setSize(700, 681);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}


}
