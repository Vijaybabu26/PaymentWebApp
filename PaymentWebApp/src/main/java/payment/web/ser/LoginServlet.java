package payment.web.ser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionContext;
import payment.web.dao.UserDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
       
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Phno = request.getParameter("Phno");
		String PassWord = request.getParameter("PassWord");
		System.out.println(Phno+PassWord);
		int UserId = 0;
		String UserName =null;
		try {
			UserDao db = new UserDao();
			UserId = db.LoginDb(Phno, PassWord);
			 UserName = db.UserNameDb(UserId);
			if(UserId != 0) {
				request.setAttribute("name", UserName);
				request.setAttribute("UserId", UserId);
				RequestDispatcher rd = request.getRequestDispatcher("/Dashboard.jsp");
				rd.forward(request, response);
				
			}else {
//				HttpSession session = request.getSession();
				request.setAttribute("Errormsg",  "LOGIN FAILED PLEASE ENTER THE CREDENTIALS");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
