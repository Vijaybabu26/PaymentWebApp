package payment.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.security.auth.message.callback.SecretKeyCallback.Request;
import jakarta.servlet.http.HttpSession;
import payment.web.entity.BankAccount;
import payment.web.entity.Transaction;
import payment.web.entity.User;

public class TransactionDao {
	Connection con;
	public TransactionDao() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PaymentWebApp", "root", "root");
	}
	public boolean DoSelfBWTransaction(String SrcPhno,double TxnAmount,String DestAccNo) throws ClassNotFoundException,SQLException{
		Statement st = con.createStatement();
		UserDao db = new UserDao();
		int UserId = db.getUserId(SrcPhno);
		
		String Bquery = "Update BankAccount Set CurrBankBalance = CurrBankBalance - '"+TxnAmount+"'  where BankAcctNo = '"+ DestAccNo +"' and UserId = '"+UserId+"'";
		String Wquery = "Update user Set CurrWalletBalance = CurrWalletBalance + '"+TxnAmount+"' where PhoneNo ='"+SrcPhno+"'";
		
		st.executeUpdate(Wquery);
		st.executeUpdate(Bquery);
		st.close();
		return true;
		
	}
	
	
	
		public String VerifyAccountNo(String AccNo) {
		try {
			
			Statement Stm = con.createStatement();
			String Uquery = "Select BankAcctNo from BankAccount where BankAcctNo ='"+AccNo+"'" ;
			
			ResultSet res = Stm.executeQuery(Uquery);
			if(res.next()) {
				System.out.println("Account Found!");
				return res.getString("BankAcctNo");
			}else{
				
				System.out.println("Account Not Found!");
			
			}
			Stm.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
		}
		public double getWalletBalance(String phno) {
			try {
				
				Statement Stm = con.createStatement();
				String Uquery = "Select CurrWalletBalance from user where PhoneNo ='"+phno+"'" ;
				
				ResultSet res = Stm.executeQuery(Uquery);
				if(res.next()) {
					System.out.println("User Found!");
					return res.getDouble("CurrWalletBalance");
				}else{
					
					System.out.println("Account Not Found!");
				
				}
				Stm.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return 0;
			
		}
		
		public void DoWBTransaction(String DestB, double TxnAmount, String SenderMobileNo) {
			try {
				Statement Stm = con.createStatement();
//				
				String wquery = "Update user Set CurrWalletBalance = CurrWalletBalance - '"+TxnAmount+"' where PhoneNo = '"+SenderMobileNo+"'";

				String bquery = "Update BankAccount Set CurrBankBalance = CurrBankBalance + '"+TxnAmount+"' where BankAcctNo = '"+DestB+"'";
				
				Stm.executeUpdate(wquery);
				Stm.executeUpdate(bquery);
				
		
				Stm.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
				
		}
		public void DoBWTransaction(double TxnAmount, String ReciverMobileNo, String SendAcctNo, String BankAcctPin) {
			try {
				Statement Stm = con.createStatement();
//				
			String Bquery = "Update BankAccount Set CurrBankBalance = CurrBankBalance - '"+TxnAmount+"'  where BankAcctNo = '"+ SendAcctNo +"' and BankAcctPin = '"+BankAcctPin +"'";
			String Wquery = "Update user Set CurrWalletBalance = CurrWalletBalance + '"+TxnAmount+"' where PhoneNo ='"+ReciverMobileNo+"'";
			
			Stm.executeUpdate(Wquery);
			Stm.executeUpdate(Bquery);
			
	
			Stm.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
			
		
		public void DoBBTransaction(String SAcctNo,String Spin, String RAcctNo,double TxnAmount) {
			
			try {
				Statement Stm = con.createStatement();
				String Squery = "Update BankAccount Set CurrBankBalance = CurrBankBalance - '"+TxnAmount+"' where BankAcctNo = '"+SAcctNo+"' and BankAcctPin = '"+Spin+"'";
				
				String Rquery = "Update BankAccount Set CurrBankBalance = CurrBankBalance + '"+TxnAmount+"' where BankAcctNo = '"+RAcctNo+"'";
				Stm.executeUpdate(Squery);
				Stm.executeUpdate(Rquery);
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void DoWWTransaction(String SMobile,String RMobile,double TxnAmount) {
			try {
				Statement Stm = con.createStatement();
				String Squery = "Update user Set CurrWalletBalance = CurrWalletBalance - '"+TxnAmount+"' where PhoneNo = '"+SMobile+"'";
				
				String Rquery = "Update user Set CurrWalletBalance = CurrWalletBalance + '"+TxnAmount+"' where PhoneNo = '"+RMobile+"'";
				
				Stm.executeUpdate(Squery);
				Stm.executeUpdate(Rquery);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		public String GetUserAcctPasswordVerify(String UserAcctPin,String PhoneNo) {
			try {
				Statement Stm = con.createStatement();
				
				String query ="Select PhoneNo,PassWord from user where PhoneNo='"+PhoneNo+"'";
				
				ResultSet rs = Stm.executeQuery(query);
				if(rs.next()) {
					String PAcctPin = rs.getString("PassWord");
					return PAcctPin;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		public List<Transaction> MiniStatementList(int UserId)throws SQLException{
			List<Transaction> TxnList = new ArrayList<Transaction>();
			String Query = "Select TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus,UserId from transaction where UserId =? ";
			PreparedStatement st = con.prepareStatement(Query);
			st.setInt(1, UserId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Transaction t = new Transaction();
				t.setUserId(rs.getInt("UserId"));
				t.setTxnId(rs.getString("TxnId"));
				t.setTxnDate(rs.getDate("TxnDate"));
				t.setSourceType(rs.getString("SourceType"));
				t.setDestType(rs.getString("DestType"));
				t.setTxnStatus(rs.getString("TxnStatus"));
				t.setTxnAmount(rs.getDouble("TxnAmount"));
				TxnList.add(t);
				
			}
			return TxnList;
			
		}
	}

