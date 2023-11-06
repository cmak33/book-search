package booksearch.service.request.implementations.authentication;

import booksearch.model.attributesholder.implementation.HttpSessionAttributesHolder;
import booksearch.model.attributesholder.interfaces.AttributesHolder;
import booksearch.service.request.interfaces.HttpRequestHandler;
import booksearch.service.user.interfaces.UserLoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class LoginRequestHandler implements HttpRequestHandler {

    private final UserLoginService userLoginService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.getRequestDispatcher("/WEB-INF/pages/authentication/login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(userLoginService.doesUserExist(request.getParameter("username"),request.getParameter("password"))){
            AttributesHolder attributesHolder = new HttpSessionAttributesHolder(request.getSession());
            userLoginService.login(request.getParameter("username"),request.getParameter("password"),attributesHolder);
        } else{
            request.setAttribute("errorMessage", "Wrong username or password");
            doGet(request, response);
        }
    }
}
