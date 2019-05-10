package GymInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Renew implements ItemListener{

	private JFrame frame;
	private JPanel panelLabel;
	private JPanel panelText;
	private JPanel panelBtn;
	private JPanel panelPayment;
	private JPanel panelRoom;
	private JPanel panelMembership;
	private JPanel panelCost;
	private JPanel panelShift;
	private JLabel labelTitle;
	private JLabel labelPayment;
	private JLabel labelRoom;
	private JLabel labelMembership;
	private JLabel labelCost;
	private JLabel labelShift;
	private JComboBox cbPayment;
	private JComboBox cbType;
	private JComboBox cbRoom;
	private JComboBox cbShift;
	private JTextField tfCost;
	private JButton btnBack;
	private JButton btnSubmit;
	String [] strPayment = {"-","Full payment", "Half payment"};
	String [] strType = {"-","3 months", "6 months", "12 months"};
	String [] strRoom = {"-","Regular", "VIP"};
	String [] strShift = {"-","Morning", "Night"};
	String strTypePay, strMonth, strClass, strTime, strUser;
	ArrayList<String> user = new ArrayList<String>();
	ArrayList<Integer> id = new ArrayList<Integer>();
	int intMem, intCtr;
	
	private static Connection cncDatabase;
	private final static String URL = new String("jdbc:mysql://localhost/AdvProg");
	private final static String USER = new String("root");
	private final static String PASS = new String("youdontknow");

	public static void renew(String strCUser) {
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
					Renew window = new Renew(strCUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Renew(String strCUser) {
		
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
		panelText.setLayout(new GridLayout(5,1));
		panelText.setBackground(new Color(21,173,228));
		panelMembership = new JPanel();
		panelMembership.setLayout(new GridLayout(1,2));
		panelMembership.setBackground(new Color(21,173,228));
		panelPayment = new JPanel();
		panelPayment.setLayout(new GridLayout(1,2));
		panelPayment.setBackground(new Color(21,173,228));
		panelRoom = new JPanel();
		panelRoom.setLayout(new GridLayout(1,2));
		panelRoom.setBackground(new Color(21,173,228));
		panelShift = new JPanel();
		panelShift.setLayout(new GridLayout(1,2));
		panelShift.setBackground(new Color(21,173,228));
		panelCost = new JPanel();
		panelCost.setLayout(new GridLayout(1,2));
		panelCost.setBackground(new Color(21,173,228));
		//Label
		labelTitle = new JLabel("");
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		labelTitle.setIcon(new ImageIcon(imgTitle));
		labelPayment = new JLabel("Payment: ");
		labelPayment.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		labelShift = new JLabel("Shift: ");
		labelShift.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		labelCost = new JLabel("Total Cost: ");
		labelCost.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		labelMembership = new JLabel("Membership: ");
		labelMembership.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		labelRoom = new JLabel("Room Type: ");
		labelRoom.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		//DropDown
		cbPayment = new JComboBox(strPayment);
		cbPayment.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbPayment.addItemListener(this);
		cbType = new JComboBox(strType);
		cbType.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbType.addItemListener(this);
		cbRoom = new JComboBox(strRoom);
		cbRoom.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbRoom.addItemListener(this);
		cbShift = new JComboBox(strShift);
		cbShift.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		cbShift.addItemListener(this);
		//TextField
		tfCost = new JTextField(12);
		tfCost.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		tfCost.setEditable(false);
		//Button
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		btnSubmit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String sql = "SELECT * FROM memberaccountinfo";
    
        		try{
        			Connection con = DriverManager.getConnection(URL, USER, PASS);
        			//GET PREPARED STMT
        			Statement s1 = con.prepareStatement(sql);
        			ResultSet rs1 = s1.executeQuery(sql);
        			
        			while(rs1.next()){
        				strUser = rs1.getString(2);
        				user.add(strUser);
        				intMem = rs1.getInt(1);
        				id.add(intMem);
        			}
        			for(intCtr = 0; intCtr < user.size(); intCtr++){
        				if(user.get(intCtr).equalsIgnoreCase(strCUser)){
            				String sql2 = "UPDATE sessioninfo SET Room = ?, Duration = ?, PaymentType = ? WHERE MemberID = " + id.get(intCtr);
            				PreparedStatement s3 = con.prepareStatement(sql2);
            				
            					s3.setString(1, strClass);
            					s3.setString(2, strMonth);
                    			s3.setString(3, strTypePay);
                    			s3.executeUpdate();

            				int rowsUpdated = s3.executeUpdate();
            				if (rowsUpdated > 0) {
            				    System.out.println("An existing user was updated successfully!");
            				}
            			}
        			}
        			
        		}
        		catch(SQLException g){
        			g.printStackTrace();
        		}
        		
        		
        		JOptionPane.showMessageDialog(null, "Renew Membership Successful");
        		Member mem = new Member(null);
        		mem.mainMem(null);
        		frame.dispose();
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
		panelRoom.add(labelRoom);
		panelRoom.add(cbRoom);
		panelMembership.add(labelMembership);
		panelMembership.add(cbType);
		panelPayment.add(labelPayment);
		panelPayment.add(cbPayment);
		panelShift.add(labelShift);
		panelShift.add(cbShift);
		panelCost.add(labelCost);
		panelCost.add(tfCost);
		panelText.add(panelRoom);
		panelText.add(panelMembership);
		panelText.add(panelPayment);
		panelText.add(panelCost);
		panelText.add(panelShift);
		panelBtn.add(btnSubmit);
		panelBtn.add(btnBack);
		//add to frame
		frame.getContentPane().add(panelLabel,"North");
		frame.getContentPane().add(panelText);
		frame.getContentPane().add(panelBtn, "South");
		frame.setSize(527, 368);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

	public void itemStateChanged(ItemEvent e) {
		strClass = (String) cbRoom.getSelectedItem();
		if(cbRoom.getSelectedIndex() == 1){
			tfCost.setText("100");
			
			if(cbType.getSelectedIndex() == 1){
				strMonth =(String) cbType.getSelectedItem();
				int intProd, intNum;
				intNum = Integer.parseInt(tfCost.getText());
				intProd = (intNum - 15) * 90;
				tfCost.setText(""+intProd);
			
				if(cbPayment.getSelectedIndex() == 1){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.2);
					tfCost.setText(""+dblFinal);
				}
				
				if(cbPayment.getSelectedIndex() == 2){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.1);
					tfCost.setText(""+dblFinal);
				}
			}
			if(cbType.getSelectedIndex() == 2){
				strMonth =(String) cbType.getSelectedItem();
				int intProd, intNum;
				intNum = Integer.parseInt(tfCost.getText());
				intProd = (intNum - 30) * 180;
				tfCost.setText(""+intProd);
				
				if(cbPayment.getSelectedIndex() == 1){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.2);
					tfCost.setText(""+dblFinal);
				}
				if(cbPayment.getSelectedIndex() == 2){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.1);
					tfCost.setText(""+dblFinal);
				}
			}
			if(cbType.getSelectedIndex() == 3){
				strMonth =(String) cbType.getSelectedItem();
				int intProd, intNum;
				intNum = Integer.parseInt(tfCost.getText());
				intProd = (intNum - 50) * 365;
				tfCost.setText(""+intProd);
				
				if(cbPayment.getSelectedIndex() == 1){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.2);
					tfCost.setText(""+dblFinal);
				}
				
				if(cbPayment.getSelectedIndex() == 2){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.1);
					tfCost.setText(""+dblFinal);
				}
			}
		}
		if(cbRoom.getSelectedIndex() == 2){
			tfCost.setText("200");
			
			if(cbType.getSelectedIndex() == 1){
				strMonth =(String) cbType.getSelectedItem();
				int intProd, intNum;
				intNum = Integer.parseInt(tfCost.getText());
				intProd = (intNum - 15) * 90;
				tfCost.setText(""+intProd);
				
				if(cbPayment.getSelectedIndex() == 1){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.2);
					tfCost.setText(""+dblFinal);
				}
				
				if(cbPayment.getSelectedIndex() == 2){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.1);
					tfCost.setText(""+dblFinal);
				}
			}
			
			if(cbType.getSelectedIndex() == 2){
				strMonth =(String) cbType.getSelectedItem();
				int intProd, intNum;
				intNum = Integer.parseInt(tfCost.getText());
				intProd = (intNum - 30) * 180;
				tfCost.setText(""+intProd);

				if(cbPayment.getSelectedIndex() == 1){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.2);
					tfCost.setText(""+dblFinal);
				}
				if(cbPayment.getSelectedIndex() == 2){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.1);
					tfCost.setText(""+dblFinal);
				}
			}
			if(cbType.getSelectedIndex() == 3){
				strMonth =(String) cbType.getSelectedItem();
				int intProd, intNum;
				intNum = Integer.parseInt(tfCost.getText());
				intProd = (intNum - 50) * 365;
				tfCost.setText(""+intProd);
				
				if(cbPayment.getSelectedIndex() == 1){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.2);
					tfCost.setText(""+dblFinal);
				}
				
				if(cbPayment.getSelectedIndex() == 2){
					strTypePay = (String) cbPayment.getSelectedItem();
					int intNum1;
					Double dblFinal;
					intNum1 = Integer.parseInt(tfCost.getText());
					dblFinal = intNum1 - (intNum1 * 0.1);
					tfCost.setText(""+dblFinal);
				}
			}
		}
	}
}