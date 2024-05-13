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


public class AddMoneyToWallet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddMoneyToWallet() {
        super();
       
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double TxnAmount = Double.parseDouble(request.getParameter("wamount"));
		String AccountNo = request.getParameter("accountno");
		System.out.println(TxnAmount);
		System.out.println(AccountNo);
		HttpSession session = request.getSession();
		User sessionUser = (User)session.getAttribute("userd");
		String Phno = sessionUser.getPhno();
		System.out.println(TxnAmount+AccountNo+Phno);
	try {
		TransactionDao txn = new TransactionDao();
		txn.DoSelfBWTransaction(Phno, TxnAmount, Phno);
		RequestDispatcher rd = request.getRequestDispatcher("/BankAcctList");
		rd.include(request, response);
//		try {
//			TransactionDao txn = new TransactionDao();
//			if(txn.VerifyAccountNo(AccountNo)!= null){
//				txn.DoSelfBWTransaction(Phno, TxnAmount, Phno);
//				System.out.println(Phno + " " +TxnAmount+ " " +AccountNo);
//				response.setContentType("text/html");
//				response.getWriter().write("Transaction Successfull");
//				RequestDispatcher rd = request.getRequestDispatcher("/BankAcctList");
//				rd.include(request, response);
//			}else {
//				response.setContentType("text/html");
//				response.getWriter().write("Transaction Failed");
//				RequestDispatcher rd = request.getRequestDispatcher("AddMoneyToWallet.jsp");
//				rd.include(request, response);
//			}
//			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
