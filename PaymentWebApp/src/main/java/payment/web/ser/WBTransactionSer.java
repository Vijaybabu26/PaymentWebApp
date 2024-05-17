package payment.web.ser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import payment.web.dao.TransactionDao;
import payment.web.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public class WBTransactionSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public WBTransactionSer() {
        super();
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String AccountNo = request.getParameter("accountno");
    	String TxnAmount = request.getParameter("Wamount");
    	double txamount = Double.parseDouble(TxnAmount);
    	String AcctPin = request.getParameter("pin");
    	
    	HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userd");
		String Phno = sessionUser.getPhno();
		System.out.println(AccountNo + TxnAmount + txamount + Phno);
		try {
			TransactionDao txn = new TransactionDao();
			if(txn.VerifyAccountNo(AccountNo)!= null) {
				if(txn.GetUserAcctPasswordVerify(AcctPin, Phno).equals(AcctPin)) {
					txn.DoWBTransaction(AccountNo,txamount , Phno);
		    		double WalletBal = txn.getWalletBalance(Phno);
					sessionUser.setCurrWalletBalance(WalletBal);
					response.setContentType("text/html");
					response.getWriter().write("Transaction Successfull");
					RequestDispatcher rd = request.getRequestDispatcher("/BankAcctList");
					rd.include(request, response);
				}else {
					response.setContentType("text/html");
					response.getWriter().write("Please Verify Your Password");
					RequestDispatcher rd = request.getRequestDispatcher("SendMoneyToWB.jsp");
					rd.include(request, response);
				}
	    		
	    		
	    	}else {
				response.setContentType("text/html");
				response.getWriter().write("Transaction Failed");
				RequestDispatcher rd = request.getRequestDispatcher("SendMoneyToWB.jsp");
				rd.include(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
    	
	}

}
