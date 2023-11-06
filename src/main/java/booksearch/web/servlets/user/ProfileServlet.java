package booksearch.web.servlets.user;

import booksearch.model.attributesholder.implementation.HttpSessionAttributesHolder;
import booksearch.model.entity.user.User;
import booksearch.service.factory.service.ServiceFactory;
import booksearch.service.user.interfaces.UserLoginService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProfileServlet extends HttpServlet {

    private UserLoginService userLoginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userLoginService = ServiceFactory.getUserLoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = userLoginService.receiveLoggedUser(new HttpSessionAttributesHolder(req.getSession()));
        req.setAttribute("user",currentUser);
        req.getRequestDispatcher("/WEB-INF/pages/user/profile.jsp").forward(req,resp);
    }
}
