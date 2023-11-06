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
public class LogoutRequestHandler implements HttpRequestHandler {

    private final UserLoginService userLoginService;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AttributesHolder attributesHolder = new HttpSessionAttributesHolder(request.getSession());
        userLoginService.logout(attributesHolder);
        response.sendRedirect(String.format("%s/login",request.getContextPath()));
    }
}
