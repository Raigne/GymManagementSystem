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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class CheckUp implements ActionListener{

	static JFrame frame = new JFrame("");
	Container contentPane;
	
	JLabel lblTitle = new JLabel("");
	JLabel lblSelect = new JLabel("Select the health condition/s that you have: ");
	static JRadioButton rdCVD = new JRadioButton("CardioVascular Disease(Heart Disease)");
	static JRadioButton rdAsthma = new JRadioButton("Asthma");
	static JRadioButton rdHighBlood = new JRadioButton("High Blood");
	static JRadioButton rdLowBlood = new JRadioButton("Low Blood");
	static JRadioButton rdDiabetes = new JRadioButton("Diabetes");
	static JRadioButton rdArthritis = new JRadioButton("Arthritis");
	static JRadioButton rdAnxiety = new JRadioButton("Anxiety");
	static JRadioButton rdNone = new JRadioButton("None");
	ButtonGroup group = new ButtonGroup();
	public String strButton;
	
	JButton btnWalkIn = new JButton("Walk-In");
	JButton btnSignUp = new JButton("Sign-Up");
	JButton btnCancel = new JButton("Cancel");
	
	JPanel panelTitle = new JPanel();
	JPanel panelrd = new JPanel(new GridLayout(9,1));
	JPanel panelButton = new JPanel();
	
	static String strCondition = null;
	
	public static void CheckUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckUp window = new CheckUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CheckUp() {
		initialize();
	}
	
	public CheckUp(String strCond){
		initialize();
		getRadio(strCond, strButton);
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		group.add(rdCVD);
		group.add(rdHighBlood);
		group.add(rdLowBlood);
		group.add(rdAsthma);
		group.add(rdDiabetes);
		group.add(rdArthritis);
		group.add(rdAnxiety);
		group.add(rdNone);
		
		panelrd.setBackground(new Color(21,173,228));
		rdCVD.setBackground(new Color(21,173,228));
		rdAsthma.setBackground(new Color(21,173,228));
		rdHighBlood.setBackground(new Color(21,173,228));
		rdLowBlood.setBackground(new Color(21,173,228));
		rdDiabetes.setBackground(new Color(21,173,228));
		rdArthritis.setBackground(new Color(21,173,228));
		rdAnxiety.setBackground(new Color(21,173,228));
		rdNone.setBackground(new Color(21,173,228));
		
		Image imgTitle = new ImageIcon(this.getClass().getResource("/grey6.png")).getImage();
		lblTitle.setIcon(new ImageIcon(imgTitle));
		
		lblSelect.setFont(new Font("Century Gothic", Font.BOLD, 30));
		rdCVD.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		rdAsthma.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		rdHighBlood.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		rdLowBlood.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		rdDiabetes.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		btnSignUp.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		btnWalkIn.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		rdArthritis.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		rdAnxiety.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		btnCancel.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		rdNone.setFont(new Font("Century Gothic", Font.PLAIN, 23));
		
		contentPane = frame.getContentPane();
		
		panelTitle.add(lblTitle);
		panelTitle.setBackground(new Color(242,245,246));
		
		panelrd.add(lblSelect);
		panelrd.add(rdCVD);
		panelrd.add(rdHighBlood);
		panelrd.add(rdLowBlood);
		panelrd.add(rdAsthma);
		panelrd.add(rdDiabetes);
		panelrd.add(rdArthritis);
		panelrd.add(rdAnxiety);
		panelrd.add(rdNone);
		
		panelButton.add(btnSignUp);
		panelButton.add(btnWalkIn);
		panelButton.add(btnCancel);
		
		contentPane.add(panelTitle, BorderLayout.NORTH);
		contentPane.add(panelrd, BorderLayout.CENTER);
		contentPane.add(panelButton, BorderLayout.SOUTH);
		
		rdCVD.addActionListener(this);
		rdAsthma.addActionListener(this);
		rdHighBlood.addActionListener(this);
		rdLowBlood.addActionListener(this);
		rdDiabetes.addActionListener(this);
		rdArthritis.addActionListener(this);
		rdAnxiety.addActionListener(this);
		rdNone.addActionListener(this);
		
		btnSignUp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		strButton = "Sign-Up";
        		getRadio(strCondition, strButton);
        	}
        });
		
		btnWalkIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		strButton = "Walk-In";
       			getRadio(strCondition, strButton);
        	}
        });
		
		btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
		
		frame.setSize(697,586);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void getRadio(String strCond, String strButton){
		if(rdCVD.isSelected()){
			strCondition = "Cardiovascular Disease";
			Rejected rej = new Rejected();
			rej.mainRej();
		}
		if(rdHighBlood.isSelected()){
			strCondition = "HighBlood" + " " + "(Qualified)";
			if(strButton == "Sign-Up"){
        		SignUpUI su = new SignUpUI(strCondition, strButton);
        		su.signUp(strCondition, strButton);
        		frame.dispose();
			}
			else if(strButton == "Walk-In"){
				WalkInUI suu = new WalkInUI(strCondition, strButton);
       			suu.walkIn(strCondition, strButton);
       			frame.dispose();
			}
			Light ligh = new Light();
			ligh.mainLigh();
		}
		if(rdLowBlood.isSelected()){
			strCondition = "Low Blood"+ " " + "(Qualified)";
			if(strButton == "Sign-Up"){
        		SignUpUI su = new SignUpUI(strCondition, strButton);
        		su.signUp(strCondition, strButton);
        		frame.dispose();
			}
			else if(strButton == "Walk-In"){
				WalkInUI suu = new WalkInUI(strCondition, strButton);
       			suu.walkIn(strCondition, strButton);
       			frame.dispose();
			}
			Balanced bal = new Balanced();
			bal.mainBal();
		}
		if(rdAsthma.isSelected()){
			strCondition = "Asthma"+ " " + "(Qualified)";
			if(strButton == "Sign-Up"){
        		SignUpUI su = new SignUpUI(strCondition, strButton);
        		su.signUp(strCondition, strButton);
        		frame.dispose();
			}
			else if(strButton == "Walk-In"){
				WalkInUI suu = new WalkInUI(strCondition, strButton);
       			suu.walkIn(strCondition, strButton);
       			frame.dispose();
			}
			Balanced bal = new Balanced();
			bal.mainBal();
		}
		if(rdDiabetes.isSelected()){
			strCondition = "Diabetes"+ " " + "(Qualified)";
			if(strButton == "Sign-Up"){
        		SignUpUI su = new SignUpUI(strCondition, strButton);
        		su.signUp(strCondition, strButton);
        		frame.dispose();
			}
			else if(strButton == "Walk-In"){
				WalkInUI suu = new WalkInUI(strCondition, strButton);
       			suu.walkIn(strCondition, strButton);
       			frame.dispose();
			}
			Weighted weig = new Weighted();
			weig.mainWeigh();
		}
		if(rdArthritis.isSelected()){
			strCondition = "Arthritis" + " " + "(Qualified)";
			if(strButton == "Sign-Up"){
        		SignUpUI su = new SignUpUI(strCondition, strButton);
        		su.signUp(strCondition, strButton);
        		frame.dispose();
			}
			else if(strButton == "Walk-In"){
				WalkInUI suu = new WalkInUI(strCondition, strButton);
       			suu.walkIn(strCondition, strButton);
       			frame.dispose();
			}
			Weighted weig = new Weighted();
			weig.mainWeigh();
		}
		if(rdAnxiety.isSelected()){
			strCondition = "Anxiety" + " " + "(Qualified)";
			if(strButton == "Sign-Up"){
        		SignUpUI su = new SignUpUI(strCondition, strButton);
        		su.signUp(strCondition, strButton);
        		frame.dispose();
			}
			else if(strButton == "Walk-In"){
				WalkInUI suu = new WalkInUI(strCondition, strButton);
       			suu.walkIn(strCondition, strButton);
       			frame.dispose();
			}
			Weighted weig = new Weighted();
			weig.mainWeigh();
		}
		if(rdNone.isSelected()){
			strCondition = "None";
			if(strButton == "Sign-Up"){
        		SignUpUI su = new SignUpUI(strCondition, strButton);
        		su.signUp(strCondition, strButton);
        		frame.dispose();
			}
			else if(strButton == "Walk-In"){
				WalkInUI suu = new WalkInUI(strCondition, strButton);
       			suu.walkIn(strCondition, strButton);
       			frame.dispose();
			}
			Weighted weig = new Weighted();
			weig.mainWeigh();
		}
	}
}