package payment.web.ser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import payment.web.dao.TransactionDao;

import java.io.IOException;
import java.sql.SQLException;


public class AddWalletToBankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddWalletToBankServlet() {
        super();
        
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String DestAccNo = request.getParameter("accountno");
		String SrcPhno = request.getParameter("phno");
		String SrcAmount = request.getParameter("Wamount");
		double TxnAmount = Double.parseDouble(SrcAmount);
		try {
//			if(TransactionDao.VerifyAccountNo(DestAccNo)!= null){
		
				TransactionDao.DoBWTransaction(SrcPhno, TxnAmount, DestAccNo);
				response.setContentType("text/html");
				response.getWriter().write("Transaction Successfull");
				RequestDispatcher rd = request.getRequestDispatcher("/SendMoneyToBW.jsp");
				rd.include(request, response);
//			}else {
//				System.out.println("Transaction Failed");
//				System.out.println("Transaction Successful");
//				response.setContentType("text/html");
//				response.getWriter().write("Transaction Failed");
//				RequestDispatcher rd = request.getRequestDispatcher("/SendMoneyToBW.jsp");
//				rd.include(request, response);
//			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	

}
