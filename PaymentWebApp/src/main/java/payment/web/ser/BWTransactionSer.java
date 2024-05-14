package payment.web.ser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import payment.web.dao.BankAcctUserDao;
import payment.web.dao.TransactionDao;
import payment.web.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public class BWTransactionSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public BWTransactionSer() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phno1 = request.getParameter("phno");
		String AccountNo = request.getParameter("accountno");
		String Amount = request.getParameter("txnamount");
		String BankPin = request.getParameter("bankpin");
		double TxnAmount = Double.parseDouble(Amount);
		
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userd");
		String Phno = sessionUser.getPhno();
		System.out.println(TxnAmount + AccountNo + phno1);
		
		try {
			TransactionDao txn = new TransactionDao();
			BankAcctUserDao db = new BankAcctUserDao();
			if(txn.VerifyAccountNo(AccountNo)!= null) {
				if(db.GetAcctVerified(AccountNo).equals(BankPin)) {
					txn.DoBWTransaction(TxnAmount, phno1, AccountNo,BankPin);
					double WalletBal = txn.getWalletBalance(Phno);
					sessionUser.setCurrWalletBalance(WalletBal);
					response.setContentType("text/html");
					response.getWriter().write("Transaction Successfull");
					RequestDispatcher rd = request.getRequestDispatcher("/BankAcctList");
					rd.include(request, response);
				}
				
	    	}else {
				response.setContentType("text/html");
				response.getWriter().write("Transaction Failed");
				RequestDispatcher rd = request.getRequestDispatcher("SendMoneyToBW.jsp");
				rd.include(request, response);
	    	}
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
