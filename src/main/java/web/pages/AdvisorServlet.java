package web.pages;

import web.ejb.StudentStateless;
import web.pages.ServiceLocater;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdvisorServlet", value = "/advisor-servlet")
public class AdvisorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("studentBean", ServiceLocater.getStudentStatelessService());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentStateless bean = (StudentStateless) request.getSession().getAttribute("studentBean");
        HttpSession session = request.getSession();
        session.setAttribute("studentId", request.getParameter("matriculation"));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
