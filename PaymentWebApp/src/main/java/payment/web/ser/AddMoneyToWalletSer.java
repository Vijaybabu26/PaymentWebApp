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


public class AddMoneyToWalletSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddMoneyToWalletSer() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Amount = request.getParameter("wamount");
		String AccountNo = request.getParameter("accountno");
		double txnamount = Double.parseDouble(Amount);
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userd");
		String Phno = sessionUser.getPhno();
		String AcctPin = request.getParameter("pin");
		System.out.println(txnamount+AccountNo+Phno);

		try {
			TransactionDao txn = new TransactionDao();
			if(txn.VerifyAccountNo(AccountNo)!= null){
				if(txn.GetUserAcctPasswordVerify(AcctPin, Phno).equals(AcctPin)) {
				txn.DoSelfBWTransaction(Phno, txnamount, AccountNo);
				System.out.println(Phno + " " +txnamount+ " " +AccountNo);
				response.setContentType("text/html");
				double WalletBal = txn.getWalletBalance(Phno);
				sessionUser.setCurrWalletBalance(WalletBal);
				response.getWriter().write("Transaction Successfull");
				RequestDispatcher rd = request.getRequestDispatcher("/BankAcctList");
				rd.include(request, response);
				}else {
					response.setContentType("text/html");
					response.getWriter().write("Please Verify Your Password");
					RequestDispatcher rd = request.getRequestDispatcher("AddMoneyToWallet.jsp");
					rd.include(request, response);
				}
				
				
			}else {
				response.setContentType("text/html");
				response.getWriter().write("Transaction Failed");
				RequestDispatcher rd = request.getRequestDispatcher("AddMoneyToWallet.jsp");
				rd.include(request, response);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	}


