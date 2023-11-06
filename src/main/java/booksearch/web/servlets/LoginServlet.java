package booksearch.web.servlets;

import booksearch.model.attributesholder.implementation.HttpSessionAttributesHolder;
import booksearch.model.attributesholder.interfaces.AttributesHolder;
import booksearch.service.factory.service.ServiceFactory;
import booksearch.service.user.interfaces.UserLoginService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private  UserLoginService userLoginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userLoginService = ServiceFactory.getUserLoginService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.getRequestDispatcher("/WEB-INF/pages/authentication/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(userLoginService.doesUserExist(request.getParameter("username"),request.getParameter("password"))){
            AttributesHolder attributesHolder = new HttpSessionAttributesHolder(request.getSession());
            userLoginService.login(request.getParameter("username"),request.getParameter("password"),attributesHolder);
            response.sendRedirect("/movie");
        } else{
            request.setAttribute("errorMessage", "Wrong username or password");
            doGet(request, response);
        }
    }
}
