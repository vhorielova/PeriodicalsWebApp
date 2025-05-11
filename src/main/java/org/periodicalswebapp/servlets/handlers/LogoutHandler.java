package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LogoutHandler implements Handler {
    Logger logger = LoggerFactory.getLogger(LogoutHandler.class);

    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("Handling GET request for logout");
        logger.debug("Invalidating session.");
        request.getSession().invalidate();
        logger.debug("Redirecting to main page after logout.");
        response.sendRedirect("/");
    }

    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("Handling POST request for logout");
    }
}
