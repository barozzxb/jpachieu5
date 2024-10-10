package pj.xuanbao.controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pj.xuanbao.entity.User;

@WebServlet(urlPatterns = {"/admin/home"})
public class AdminController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
//			String name = (String)session.getAttribute("name");
//			req.setAttribute("name", name);
			User u = (User) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
			req.setAttribute("password", u.getPassword());
			req.setAttribute("email", u.getEmail());
			req.setAttribute("fullname", u.getFullname());
			req.setAttribute("phone", u.getPhone());
		}
        
		req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
	}
}
