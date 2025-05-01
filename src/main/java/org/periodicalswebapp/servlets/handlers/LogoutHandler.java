package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutHandler implements Handler {
    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        response.sendRedirect("/");
    }

    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }
}
