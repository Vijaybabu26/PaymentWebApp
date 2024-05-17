package payment.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import payment.web.entity.BankAccount;

public class BankAcctUserDao {
	Connection con;
	public BankAcctUserDao() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PaymentWebApp", "root", "root");
	}
	
	public int AddBankAcct(BankAccount ba) throws SQLException {
			Statement st = con.createStatement();
			String bquery ="insert into BankAccount(UserId,BankAcctNo,BankAcctName,BankAcctTypeId,BankIFSCCode,BankAcctPin,CurrBankBalance,PhoneNo)"
					+ " values('"+ba.getUserId()+"','"+ba.getBankAcctNo()+"','"+ba.getBankAcctName()+"','"+ba.getBankAcctTypeId()+"','"+ba.getBankIFSCCode()+"','"+ba.getBankPin()+"','"+ba.getCurrBankBal()+"','"+ba.getPhno()+"')";
			return st.executeUpdate(bquery);		
	}
	public List<BankAccount> BankAcctList(int UserId) throws SQLException{
		
		List<BankAccount> balist = new ArrayList<BankAccount>();
		
		String Query = "Select BankAcctNo,BankAcctName,BankIFSCCode,CurrBankBalance,BankAcctPin from BankAccount Where UserId=?";
				
		PreparedStatement st = con.prepareStatement(Query);
		st.setInt(1, UserId);
		
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			BankAccount b = new BankAccount();
			b.setBankAcctNo(rs.getString("BankAcctNo"));
			b.setBankAcctName(rs.getString("BankAcctName"));
			b.setBankIFSCCode(rs.getString("BankIFSCCode"));
			b.setCurrBankBal(rs.getDouble("CurrBankBalance"));
			b.setBankPin(rs.getString("BankAcctPin"));
			balist.add(b);
		}
		return balist;
	}
	public String GetAcctVerified(String AccountNo) {
		try {
			Statement Stm = con.createStatement();
			
			String query ="Select BankAcctPin from BankAccount where BankAcctNo='"+AccountNo+"'";
			
			ResultSet rs = Stm.executeQuery(query);
			if(rs.next()) {
				String BankPin = rs.getString("BankAcctPin");
				return BankPin;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
