package booksearch.web.servlets.dispatcher;

import booksearch.configuration.RequestHandlersConfiguration;
import booksearch.service.request.interfaces.HttpRequestHandler;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class DispatcherServlet extends HttpServlet {

    private Map<Pattern, HttpRequestHandler> handlers;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        handlers = RequestHandlersConfiguration.getInstance().getHandlers();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        Optional<HttpRequestHandler> handler = findHandler(request);
        if(handler.isPresent()){
            handler.get().doGet(request,response);
        } else{
            sendNotFoundError(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<HttpRequestHandler> handler = findHandler(request);
        if(handler.isPresent()){
            handler.get().doPost(request,response);
        } else{
            sendNotFoundError(response);
        }
    }

    private Optional<HttpRequestHandler> findHandler(HttpServletRequest request){
        String path = request.getServletPath();
        return handlers.keySet()
                .stream()
                .filter(pattern->path.matches(pattern.pattern()))
                .findFirst()
                .map(x->handlers.get(x));
    }

    private void sendNotFoundError(HttpServletResponse response) throws IOException{
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
