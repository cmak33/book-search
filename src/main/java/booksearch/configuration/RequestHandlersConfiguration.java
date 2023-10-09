package booksearch.configuration;

import booksearch.service.request.interfaces.HttpRequestHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RequestHandlersConfiguration {

    private final Map<Pattern, HttpRequestHandler> handlers;

    private RequestHandlersConfiguration() {
        handlers = createHandlers();
    }

    private static class InstanceHolder {
        public static final RequestHandlersConfiguration INSTANCE = new RequestHandlersConfiguration();
    }

    public static RequestHandlersConfiguration getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private Map<Pattern, HttpRequestHandler> createHandlers() {
        return new HashMap<>();
    }

    public Map<Pattern, HttpRequestHandler> getHandlers() {
        return handlers;
    }
}
