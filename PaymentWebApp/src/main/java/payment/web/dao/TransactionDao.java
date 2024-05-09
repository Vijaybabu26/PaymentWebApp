package payment.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.security.auth.message.callback.SecretKeyCallback.Request;
import jakarta.servlet.http.HttpSession;
import payment.web.entity.BankAccount;

public class TransactionDao {
	Connection con;
	public TransactionDao() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PaymentWebApp", "root", "root");
	}
	public boolean DoBWTransaction(String SrcPhno,double TxnAmount,String DestAccNo) throws ClassNotFoundException,SQLException{
			Statement st = con.createStatement();
			
//			String SenderType = "DEBIT";
//			String RecieverType ="CREDIT";
//			String Txn_AcctType_Source = "BANK ACCOUNT";
//			String Txn_AcctType_Dest = "WALLET";
			LocalDateTime now = LocalDateTime.now();
			UUID Uui = UUID.randomUUID();
//			UUID UuiD = UUID.randomUUID();
//			String TxnIdS = "TXNS" + Uui.toString(); 
//			String TxnIdD = "TXND" + Uui.toString();
			
			UserDao db;
		
			db = new UserDao();
				
			
			int UserID = db.getUserId(SrcPhno);
			System.out.println(UserID);
			System.out.println(TxnAmount);
			System.out.println(DestAccNo);
			
			String Bquery = "Update BankAccount Set CurrBankBalance = CurrBankBalance - '"+TxnAmount+"'  where BankAcctNo = '"+ DestAccNo +"' and UserId = '"+UserID+"'";
			String Wquery = "Update user Set CurrWalletBalance = CurrWalletBalance + '"+TxnAmount+"' where PhoneNo ='"+SrcPhno+"'";
			
			st.executeUpdate(Wquery);
			st.executeUpdate(Bquery);
//			
//			String Tquery = "INSERT INTO Transaction (Txn_Id, Txn_Date, Txn_Amount, Txn_Type, Txn_AcctType_Source,Txn_AcctType_Dest, Txn_User_Id) VALUES " + 
//		            "('" + TxnIdS + "', '" + now + "', " + TxnAmount + ", '" + SenderType + "', '" + Txn_AcctType_Source + "', '"+Txn_AcctType_Dest+"','" + Runpaymentappjdbc.CurrUserId + "')";
//			String TxnQ = "INSERT INTO Transaction (Txn_Id, Txn_Date, Txn_Amount, Txn_Type, Txn_AcctType_Source,Txn_AcctType_Dest, Txn_User_Id) VALUES " + 
//		            "('" + TxnIdD + "', '" + now + "', " + TxnAmount + ", '" + RecieverType + "', '" + Txn_AcctType_Source + "','"+Txn_AcctType_Dest+"', '" + Suser+ "')";

//			Stm.executeUpdate(TxnQ);
//			Stm.executeUpdate(Tquery);
//			
			
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
	}

