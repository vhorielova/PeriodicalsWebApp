package org.periodicalswebapp.servlets.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HelloHandler implements Handler{
    public void handleGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getRequestURI()+" Get");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "hello handler" + "</h1>");
        out.println("</body></html>");
    }

    public void handlePost (HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getRequestURI()+" Post");
    }
}
