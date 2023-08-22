package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MyDao;
import dto.Customer;

//mapping the url
@WebServlet("/login")
public class Login extends HttpServlet {
	@Override // when there is form and we want to data to be secured so dopost
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// logic to receive values from front end
		String email=req.getParameter("email");
		String password=req.getParameter("pass");
		
		// verify if email exists
		MyDao dao=new MyDao();
		//for admin
		if(email.equals("admin@jsp.com")&& password.equals("admin"))
		{
		   resp.getWriter().print("<h1 style='color:green'>Login success</h1>");
		   // this is logic to send to next page
		   req.getRequestDispatcher("AdminHome.html").include(req, resp);
		}
		else
		{
		Customer customer=dao.fetchByEmail(email);
		if(customer==null)
		{
			resp.getWriter().print("<h1 style='color:red'>Invalid email</h1>");
		}
		else {
			if(password.equals(customer.getPassword()))
			{
				resp.getWriter().print("<h1 style='color:green'>Login succesfully</h1>");
			}
			else {
				resp.getWriter().print("<h1 style='color:red'>Invalid password</h1>");
			}
		}

	}
	
	}
}
