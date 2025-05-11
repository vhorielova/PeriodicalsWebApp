package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class IndexHandler implements Handler{
    Logger logger = LoggerFactory.getLogger(IndexHandler.class);
    public void handleGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("Handling GET request for main page.");
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null) {
            logger.debug("User is not logged in.");
            request.setAttribute("loggedIn", false);
        }
        else {
            logger.debug("User is logged in as: {}", session.getAttribute("user"));
            request.setAttribute("loggedIn", true);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(request, response);
    }

    public void handlePost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("Handling POST request for main page.");
    }
}
