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


public class WWTransactionSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public WWTransactionSer() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RMobile = request.getParameter("RMobileNo");

		String AcctPin = request.getParameter("AcctPin");
		String Amount = request.getParameter("TxnAmount");
		double TxnAmount = Double.parseDouble(Amount);
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userd");
		String SMobile = sessionUser.getPhno();

		try {
			TransactionDao txn = new TransactionDao();
			if(txn.GetUserAcctPasswordVerify(AcctPin, SMobile).equals(AcctPin)) {
				txn.DoWWTransaction(SMobile, RMobile,TxnAmount);
				double WalletBal = txn.getWalletBalance(SMobile);
				sessionUser.setCurrWalletBalance(WalletBal);
				response.setContentType("text/html");
				response.getWriter().write("Transaction Successfull");
				RequestDispatcher rd = request.getRequestDispatcher("/BankAcctList");
				rd.include(request, response);
			}else {
				response.setContentType("text/html");
				response.getWriter().write("Please Enter The Correct Pin");
				RequestDispatcher rd = request.getRequestDispatcher("SendMoneyToWW.jsp");
				rd.include(request, response);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
