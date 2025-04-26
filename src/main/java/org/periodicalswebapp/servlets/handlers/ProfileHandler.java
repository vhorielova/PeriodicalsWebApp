package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProfileHandler implements Handler {
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
