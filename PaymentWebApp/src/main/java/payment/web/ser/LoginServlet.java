package payment.web.ser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;
import payment.web.dao.BankAcctUserDao;
import payment.web.dao.UserDao;
import payment.web.entity.BankAccount;
import payment.web.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import com.mysql.cj.Session;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
       
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Phno = request.getParameter("Phno");
		String PassWord = request.getParameter("PassWord");
		int  UserId = 0;
		String UserName =null;
		HttpSession session = request.getSession();
		try {
			UserDao db = new UserDao();
//			BankAcctUserDao Bdb = new BankAcctUserDao();
			UserId = db.LoginDb(Phno, PassWord);
			 UserName = db.UserNameDb(UserId);
			System.out.println(UserId);
			User SessionUser = db.getUserDetails(Phno);
			
			User sessionUser = (User)session.getAttribute("userd");
			
			if(UserId != 0) {
				
				
//				request.setAttribute("name", UserName);
				request.setAttribute("UserId", UserId);
				
				session.setAttribute("user", UserId);
				session.setAttribute("name", UserName.toUpperCase());
				session.setAttribute("userd", SessionUser);
				
				
//				
//				Cookie ck = new Cookie("UserName",UserName);
//				response.addCookie(ck);
//				if(sessionUser != null) {
					RequestDispatcher rd = request.getRequestDispatcher("/BankAcctList");
					rd.forward(request, response);	
//				}else {
//					response.setContentType("text/html");
//					response.getWriter().write("Please Log out the Current User");
//					RequestDispatcher rd1 = request.getRequestDispatcher("Welcome.jsp");
//					rd1.include(request, response);
//				}
				
				
				
			}else {
//				HttpSession session = request.getSession();
				response.setContentType("text/html");
				response.getWriter().write("LOGIN FAILED PLEASE ENTER THE CREDENTIALS");
				RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
				rd.include(request, response);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
