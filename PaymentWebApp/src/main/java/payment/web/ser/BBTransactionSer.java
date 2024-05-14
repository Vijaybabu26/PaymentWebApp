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


public class BBTransactionSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BBTransactionSer() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String SAcctNo = request.getParameter("SAcctNo");
		String SBankpin = request.getParameter("Pin");
		String RAcctNo = request.getParameter("RAcctNo");
		String TxnAmount = request.getParameter("TxnAmount");
		double Amount = Double.parseDouble(TxnAmount);
		
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userd");
		String Phno = sessionUser.getPhno();

		try {
			TransactionDao txn = new TransactionDao();
			BankAcctUserDao db = new BankAcctUserDao();
			
			if(txn.VerifyAccountNo(RAcctNo)!= null) {
				if(txn.VerifyAccountNo(SAcctNo)!= null) {
					if(db.GetAcctVerified(SAcctNo).equals(SBankpin)) {
						txn.DoBBTransaction(SAcctNo, SBankpin, RAcctNo,Amount );
						double WalletBal = txn.getWalletBalance(Phno);
						sessionUser.setCurrWalletBalance(WalletBal);
						response.setContentType("text/html");
						response.getWriter().write("Transaction Successfull");
						RequestDispatcher rd = request.getRequestDispatcher("/BankAcctList");
						rd.include(request, response);
					}else {
						response.setContentType("text/html");
						response.getWriter().write("Please Enter The Correct Pin");
						RequestDispatcher rd = request.getRequestDispatcher("SendMoneyToBB.jsp");
						rd.include(request, response);
					}
				}else {
					response.setContentType("text/html");
					response.getWriter().write("Please Check Your Account No");
					RequestDispatcher rd = request.getRequestDispatcher("SendMoneyToBB.jsp");
					rd.include(request, response);
				}
			}else {
				response.setContentType("text/html");
				response.getWriter().write("Please Check Your Reciever Account No");
				RequestDispatcher rd = request.getRequestDispatcher("SendMoneyToBB.jsp");
				rd.include(request, response);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
