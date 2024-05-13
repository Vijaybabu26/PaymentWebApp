package payment.web.ser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import payment.web.dao.BankAcctUserDao;
import payment.web.entity.BankAccount;
import payment.web.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class BankAcctList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
        public BankAcctList() {
        super();
        // TODO Auto-generated constructor stub
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	doPost(request, response);
        }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
		
		
			 
			try {
				BankAcctUserDao Bdb = new BankAcctUserDao();
				User SessionUser = (User) session.getAttribute("userd");
				int UserId = SessionUser.getUserId();
				
				session.setAttribute("Wamount", SessionUser.getCurrWalletBalance());
				List<BankAccount> balist = Bdb.BankAcctList(UserId);
				session.setAttribute("Balist", balist);
				
				response.setContentType("text/html");
				response.getWriter().write("LOGIN SUCCESSFULL");
				RequestDispatcher rd = request.getRequestDispatcher("Dashboard.jsp");
				rd.include(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
