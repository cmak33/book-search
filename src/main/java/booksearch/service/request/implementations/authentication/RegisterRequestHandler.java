package booksearch.service.request.implementations.authentication;

import booksearch.model.attributesholder.implementation.HttpSessionAttributesHolder;
import booksearch.model.attributesholder.interfaces.AttributesHolder;
import booksearch.service.request.interfaces.HttpRequestHandler;
import booksearch.service.user.interfaces.UserLoginService;
import booksearch.service.user.interfaces.UserRegisterService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class RegisterRequestHandler implements HttpRequestHandler {

    private final UserRegisterService userRegisterService;
    private final UserLoginService userLoginService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/authentication/registration.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(userRegisterService.isUsernameUnique(request.getParameter("username"))){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            AttributesHolder attributesHolder = new HttpSessionAttributesHolder(request.getSession());
            userRegisterService.register(username, password);
            userLoginService.login(username, password,attributesHolder);
        } else{
            request.setAttribute("errorMessage", "Username is not unique");
        }
    }
}
