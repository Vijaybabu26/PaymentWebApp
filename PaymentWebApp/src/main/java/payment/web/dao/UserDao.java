package payment.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import payment.web.entity.User;

public class UserDao {
	Connection con;
	public UserDao() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PaymentWebApp", "root", "root");
	}
	
	public int RegisterDb(User u) throws SQLException {
		String DatePattern = "yyyy-mm-dd";
		
		SimpleDateFormat format = new SimpleDateFormat(DatePattern);
			Statement st = con.createStatement();
			
			String egQuery ="insert into User(FirstName,LastName,PhoneNo,EMailId,DateOfBirth,Address,PassWord,CurrWalletBalance)"
					+ "values('"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getPhno()+"','"+u.getEmailId()+"','"+format.format(u.getDateOfBirth())+"',"
							+ "'"+u.getAddress()+"','"+u.getPassWord()+"','"+0+"')";
			return st.executeUpdate(egQuery);			
	}
	
	public int LoginDb(String Phno,String Password) throws SQLException{
//		Statement st = con.createStatement();
//		
//		String lquery = "select PhoneNo, PassWord ,UserId from User where PhoneNo= '"+Phno+"' and PassWord='"+Password+"'";
//		
//		ResultSet res = st.executeQuery(lquery);
//		
//		res.getString("PhoneNo");
//		res.getString("PassWord");
//		
//		return res.getInt("UserId");

		String lquery = "SELECT PhoneNo, PassWord, UserId FROM User WHERE PhoneNo = ? AND PassWord = ?";
	    PreparedStatement pstmt = con.prepareStatement(lquery);
	    
	    pstmt.setString(1, Phno);
	    pstmt.setString(2, Password);
	    
	    ResultSet res = pstmt.executeQuery();
	    if(res.next()) {
	    
	    	return res.getInt("UserId");
	    	
	    }
	    
	        return 0;
	}
	
	public String UserNameDb(int userID)  throws SQLException {
		
			Statement st = con.createStatement();
			String userq = "Select FirstName,LastName from User where UserId = '"+userID+"'";
			ResultSet res =  st.executeQuery(userq);
			
			if(res.next()) {
		    	
			    	
			    	return res.getString("FirstName")+" " + res.getString("LastName");
		    	
		    }
			
			return null;
	}
	public User getUserDetails(String Phno)  throws SQLException {
		
		Statement st = con.createStatement();
		String userq = "Select * from User where PhoneNo = '"+Phno+"'";
		ResultSet res =  st.executeQuery(userq);
		
		while(res.next()) {
				User u = new User();
				int UserId = res.getInt("UserId");
		    	String FirstName = res.getString("FirstName");
		    	String LastName = res.getString("LastName");
		    	String PhNo	= res.getString("PhoneNo");
		    	String EMailId = res.getString("EMailId");
		    	Date Dob = res.getDate("DateOfBirth");
		    	String Address = res.getString("Address");
		    	String PassWord = res.getString("PassWord");
		    	double CurrWalletBalance = res.getDouble("CurrWalletBalance");
		    	u.setUserId(UserId);
		    	u.setFirstName(FirstName);
		    	u.setLastName(LastName);
		    	u.setPhno(PhNo);
		    	u.setEmailId(EMailId);
		    	u.setDateOfBirth(Dob);
		    	u.setAddress(Address);
		    	u.setPassWord(PassWord);
		    	u.setCurrWalletBalance(CurrWalletBalance);
		    	
		    	return u;
	    	
	    }
		return null;
		
	}
	
	public int getUserId(String Phno)  throws SQLException{
		Statement st = con.createStatement();
		String userq = "Select UserId from User where PhoneNo = '"+Phno+"'";
		ResultSet res =  st.executeQuery(userq);
		if(res.next()) {
			int UserId = res.getInt("UserId");
			System.out.println(UserId);
			return UserId;
		}
		return 0;
		
	}

	
}
