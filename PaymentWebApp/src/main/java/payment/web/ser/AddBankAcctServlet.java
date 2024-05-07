package payment.web.ser;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import payment.web.dao.BankAcctUserDao;
import payment.web.entity.BankAccount;

import java.io.IOException;
import java.sql.SQLException;

public class AddBankAcctServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AddBankAcctServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String BankAcctNo = request.getParameter("bankno");
		String BankName = request.getParameter("bankname");
		String BankAcctId = request.getParameter("accttypeid");
		String BankIFSCCode = request.getParameter("bankifsc");
		String BankAcctPin = request.getParameter("bankpin");
//		HttpSession hs = request.getSession();
		
		
		BankAccount ba = new BankAccount();
		ba.setBankAcctNo(BankAcctNo);
		ba.setBankAcctName(BankName);
		ba.setBankAcctTypeId(Integer.parseInt(BankAcctId));
		ba.setBankIFSCCode(BankIFSCCode);
		ba.setBankPin(Integer.parseInt(BankAcctPin));
		ba.setCurrBankBal(500);
		int UserId = (int) request.getAttribute("UserId");
		    ba.setUserId(UserId);

		try {
			BankAcctUserDao db = new BankAcctUserDao();
			if(db.AddBankAcct(ba)>0) {
				RequestDispatcher rd = request.getRequestDispatcher("/Dashboard.jsp");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
