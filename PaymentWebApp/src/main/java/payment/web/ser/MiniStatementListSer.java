package payment.web.ser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import payment.web.dao.TransactionDao;
import payment.web.entity.Transaction;
import payment.web.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class MiniStatementListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public MiniStatementListSer() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			TransactionDao t = new TransactionDao();
			User SessionUser = (User) session.getAttribute("userd");
			String Phno = SessionUser.getPhno();
			
			List<Transaction> txnlist = t.MiniStatementList(Phno);
			System.out.println(Phno);
			session.setAttribute("txnlist", txnlist);
			RequestDispatcher rd = request.getRequestDispatcher("MiniStatement.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
