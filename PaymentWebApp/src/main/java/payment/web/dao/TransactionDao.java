package payment.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
	public boolean DoSelfBWTransaction(String DestPhno,double TxnAmount,String SrcAccNo) throws ClassNotFoundException,SQLException{
		Statement st = con.createStatement();
		UserDao db = new UserDao();
		int UserId = db.getUserId(DestPhno);
		
		String Bquery = "Update BankAccount Set CurrBankBalance = CurrBankBalance - '"+TxnAmount+"'  where BankAcctNo = '"+ SrcAccNo +"' and UserId = '"+UserId+"'";
		String Wquery = "Update user Set CurrWalletBalance = CurrWalletBalance + '"+TxnAmount+"' where PhoneNo ='"+DestPhno+"'";
		
		Timestamp datenow = new Timestamp(new Date().getTime());
		String dt = datenow.toString();
		dt.toUpperCase();
		dt =dt.replace("-", "M");
		dt =dt.replace(" ","D");
		dt =dt.replace(":", "T");
		dt =dt.replace(".", "S");
		String TransD = "TXNW" +dt;
		
		
		String dquery= "insert into Transaction(TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus,PhoneNo) "
				+ "values('"+TransD+"','"+datenow+"','"+TxnAmount+"','"+"BANKACCOUNT"+"','"+"WALLET"+"','"+"DEBIT"+"','"+DestPhno+"')";
		
		
		st.executeUpdate(dquery);
		
		
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
		
		public String GetPhnoByBankAcct(String BankAccountNo) {
			try {
				Statement Stm = con.createStatement();
				String Query = "Select PhoneNo from BankAccount where BankAcctNo ='"+BankAccountNo+"'";
				ResultSet rs =Stm.executeQuery(Query);
				if(rs.next()) {
					String Phno = rs.getString("PhoneNo");
					return Phno;
				}else {
					System.out.println("Account Not Found");
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return null;
			
				
		}
		
		public void DoWBTransaction(String DestB, double TxnAmount, String SenderPhNo) {
			try {
				Statement Stm = con.createStatement();
				
				String wquery = "Update user Set CurrWalletBalance = CurrWalletBalance - '"+TxnAmount+"' where PhoneNo = '"+SenderPhNo+"'";

				String bquery = "Update BankAccount Set CurrBankBalance = CurrBankBalance + '"+TxnAmount+"' where BankAcctNo = '"+DestB+"'";
				
				Timestamp datenow = new Timestamp(new Date().getTime());
				String dt = datenow.toString();
				dt.toUpperCase();
				dt =dt.replace("-", "M");
				dt =dt.replace(" ","D");
				dt =dt.replace(":", "T");
				dt =dt.replace(".", "S");
				String TransD = "TXND" +dt;
				String TransC = "TXNC"+dt;
				
				String ReciverPhno = GetPhnoByBankAcct(DestB);
				System.out.println(ReciverPhno);
				String dquery= "insert into Transaction(TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus,PhoneNo) "
						+ "values('"+TransD+"','"+datenow+"','"+TxnAmount+"','"+"WALLET"+"','"+"BANKACCOUNT"+"','"+"DEBIT"+"','"+SenderPhNo+"')";
				String cquery= "insert into Transaction(TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus,PhoneNo) "
						+ "values('"+TransC+"','"+datenow+"','"+TxnAmount+"','"+"BANKACCOUNT"+"','"+"WALLET"+"','"+"CREDIT"+"','"+ReciverPhno+"')";
				
				Stm.executeUpdate(dquery);
				
				
				Stm.executeUpdate(cquery);
				Stm.executeUpdate(wquery);
				Stm.executeUpdate(bquery);
				
				
		
				Stm.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
				
		}
		public void DoBWTransaction(double TxnAmount, String ReciverPhno, String SendAcctNo, String BankAcctPin) {
			try {
				Statement Stm = con.createStatement();
//				
			String Bquery = "Update BankAccount Set CurrBankBalance = CurrBankBalance - '"+TxnAmount+"'  where BankAcctNo = '"+ SendAcctNo +"' and BankAcctPin = '"+BankAcctPin +"'";
			String Wquery = "Update user Set CurrWalletBalance = CurrWalletBalance + '"+TxnAmount+"' where PhoneNo ='"+ReciverPhno+"'";
			
			

			Timestamp datenow = new Timestamp(new Date().getTime());
			String dt = datenow.toString();
			dt.toUpperCase();
			dt =dt.replace("-", "M");
			dt =dt.replace(" ","D");
			dt =dt.replace(":", "T");
			dt =dt.replace(".", "S");
			String TransD = "TXND" +dt;
			String TransC = "TXNC"+dt;
			
			String SenderPhNo = GetPhnoByBankAcct(SendAcctNo);
			
			String dquery= "insert into Transaction(TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus,PhoneNo) "
					+ "values('"+TransD+"','"+datenow+"','"+TxnAmount+"','"+"BANKACCOUNT"+"','"+"WALLET"+"','"+"DEBIT"+"','"+SenderPhNo+"')";
			String cquery= "insert into Transaction(TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus,PhoneNo) "
					+ "values('"+TransC+"','"+datenow+"','"+TxnAmount+"','"+"WALLET"+"','"+"BANKACCOUNT"+"','"+"CREDIT"+"','"+ReciverPhno+"')";
			
			Stm.executeUpdate(cquery);
			Stm.executeUpdate(dquery);
			
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
				
				Timestamp datenow = new Timestamp(new Date().getTime());
				String dt = datenow.toString();
				dt.toUpperCase();
				dt =dt.replace("-", "M");
				dt =dt.replace(" ","D");
				dt =dt.replace(":", "T");
				dt =dt.replace(".", "S");
				String TransD = "TXND" +dt;
				String TransC = "TXNC"+dt;
				
				String SenderPhNo = GetPhnoByBankAcct(SAcctNo);
				String ReciverPhNo = GetPhnoByBankAcct(RAcctNo);
				
				String dquery= "insert into Transaction(TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus,PhoneNo) "
						+ "values('"+TransD+"','"+datenow+"','"+TxnAmount+"','"+"BANKACCOUNT"+"','"+"BANKACCOUNT"+"','"+"DEBIT"+"','"+SenderPhNo+"')";
				String cquery= "insert into Transaction(TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus,PhoneNo) "
						+ "values('"+TransC+"','"+datenow+"','"+TxnAmount+"','"+"BANKACCOUNT"+"','"+"BANKACCOUNT"+"','"+"CREDIT"+"','"+ReciverPhNo+"')";
				
				Stm.executeUpdate(dquery);
				Stm.executeUpdate(cquery);
				
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
				
				Timestamp datenow = new Timestamp(new Date().getTime());
				String dt = datenow.toString();
				dt.toUpperCase();
				dt =dt.replace("-", "M");
				dt =dt.replace(" ","D");
				dt =dt.replace(":", "T");
				dt =dt.replace(".", "S");
				String TransD = "TXND" +dt;
				String TransC = "TXNC"+dt;
				
				String dquery= "insert into Transaction(TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus,PhoneNo) "
						+ "values('"+TransD+"','"+datenow+"','"+TxnAmount+"','"+"WALLET"+"','"+"WALLET"+"','"+"DEBIT"+"','"+SMobile+"')";
				String cquery= "insert into Transaction(TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus,PhoneNo) "
						+ "values('"+TransC+"','"+datenow+"','"+TxnAmount+"','"+"WALLET"+"','"+"WALLET"+"','"+"CREDIT"+"','"+RMobile+"')";
				
				Stm.executeUpdate(dquery);
				Stm.executeUpdate(cquery);
				
				
				
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
		public List<Transaction> MiniStatementList(String Phno)throws SQLException{
			List<Transaction> TxnList = new ArrayList<Transaction>();
			String Query = "Select TxnId,TxnDate,TxnAmount,SourceType,DestType,TxnStatus from transaction where PhoneNo =? ";
			PreparedStatement st = con.prepareStatement(Query);
			st.setString(1, Phno);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Transaction t = new Transaction();
				
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

