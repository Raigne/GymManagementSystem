package GymInterface;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QRCodeTry extends Encryption {
	
	private static Connection cncDatabase;
	private final static String URL = new String("jdbc:mysql://localhost/AdvProg");
	private final static String USER = new String("root");
	private final static String PASS = new String("youdontknow");
	static String strFilename;
	static int intId, intTemp;
	static ArrayList<Integer> id = new ArrayList<Integer>();
	
    private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        
    	QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    
    }
    
    public static void main(String strUser, String strPass) {
    	String sql2 = "SELECT * FROM memberaccountinfo";
    	
    	try{
			cncDatabase = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("CONNECTED");
		}
		
    	catch(SQLException objSqlException){
			objSqlException.printStackTrace();
		}
    	try{
    		Connection con = DriverManager.getConnection(URL, USER, PASS);
    		Statement s2 = con.prepareStatement(sql2);
			ResultSet rs2 = s2.executeQuery(sql2);
			while(rs2.next()){
				intId = rs2.getInt(1);
				id.add(intId);
			}
			intTemp = id.size() +1;
			strFilename = "MemberQR" + intTemp + ".png";
	    	String QR_CODE_IMAGE_PATH = "C:/Users/Derrick Gaban/Desktop/QR Code/" + strFilename;
	    	String strUsername=strUser,strPassword=strPass;
	    	strUsername=Encrypt(strUsername);strPassword=Encrypt(strPassword);
	  
	        try {
	            generateQRCodeImage("This is the username:"+strUsername +"\nThis is the password:"+strPassword,350, 350, QR_CODE_IMAGE_PATH);
	        } 
	        catch (WriterException e) {
	            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
	        } 
	        catch (IOException e) {
	            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
	        }
    	}
    	catch(SQLException b){
			b.printStackTrace();
		}

    }
}